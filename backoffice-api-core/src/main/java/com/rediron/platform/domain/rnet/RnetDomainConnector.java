package com.rediron.platform.domain.rnet;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rediron.platform.domain.entities.InvbysitEntity;
import com.rediron.platform.domain.entities.InvtoryEntity;
import com.rediron.platform.domain.entities.InvtoryEntityId;
import com.rediron.platform.domain.entities.ItemupcEntity;
import com.rediron.platform.domain.entities.SitgrpAllEntity;
import com.rediron.platform.domain.entities.VenSiteEntity;
import com.rediron.platform.domain.entities.VendorItemEntity;
import com.rediron.platform.domain.repositories.InvbysitRepository;
import com.rediron.platform.domain.repositories.InvtoryRepository;
import com.rediron.platform.domain.repositories.ItemupcRepository;
import com.rediron.platform.domain.repositories.SitgrpAllRepository;
import com.rediron.platform.domain.repositories.VenSiteRepository;
import com.rediron.platform.domain.repositories.VendorItemRepository;
import com.rediron.security.common.domain.ConfigrecEntity;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.RNetIllegalArgumentException;
import com.tomax.api.RNetUnexpectedException;
import com.tomax.api.UserIdentity;
import com.tomax.config.api.ConfigAPI;
import com.tomax.config.dto.ConfigrecDTO;
import com.tomax.inventory.api.InventoryAPI;
import com.tomax.site.api.SiteAPI;
import com.tomax.site.dto.SiteDTO;
import com.tomax.vendor.api.VendorAPI;
import com.tomax.vendor.dto.VendorDTO;
import com.tomax.vendor.dto.VendorItemDTO;

@Component
public class RnetDomainConnector {
	
	@Autowired
	private VendorItemRepository vendorItemRepository;
	
	@Autowired
	private InvtoryRepository invtoryRepository;
	
	@Autowired
	private ItemupcRepository itemupcRepository;
	
	@Autowired
	private InvbysitRepository invbysitRepository;
	
	@Autowired
	private SitgrpAllRepository sitgrpAllRepository;
	
	@Autowired
	private VenSiteRepository venSiteRepository;
	
	private static InventoryAPI inventoryAPI = RNetApiContexts.hq.getStateless(InventoryAPI.class);
	private static VendorAPI vendorAPI = RNetApiContexts.hq.getStateless(VendorAPI.class);
	private static SiteAPI siteAPI = RNetApiContexts.hq.getStateless(SiteAPI.class);
	private static ConfigAPI configAPI = RNetApiContexts.hq.getStateless(ConfigAPI.class);	

	public static VendorItemDTO getPrimaryVendorItem(UserIdentity domainUser, Integer siteNo, Integer skuNo) {
		return vendorAPI.getPrimaryVendorItem(domainUser, siteNo, skuNo);
	}

	public static VendorItemDTO getMainVendorItem(UserIdentity domainUser, Integer siteNo, Integer skuNo,
			String vendorId) {
		return vendorAPI.getMainVendorItem(domainUser, siteNo, skuNo, vendorId);
	}

	public static VendorItemDTO getVendorItem(UserIdentity domainUser, BigInteger seqNo) {
		return vendorAPI.getVendorItem(domainUser, seqNo); 
	}
	
	public static List<VendorItemDTO> getVendorItems(UserIdentity domainUser, Integer siteNo, Integer skuNo, String vendorId) {
		return vendorAPI.getVendorItems(domainUser, siteNo, skuNo, vendorId);
	}

	public static SiteDTO getSite(UserIdentity domainUser, Integer siteNo) {
		return siteAPI.getSite(domainUser, siteNo);
	}

	public VendorDTO getVendor(UserIdentity domainUser, String vendorId) {
		return vendorAPI.getVendor(domainUser, vendorId);
	}

	public int getHQSite(UserIdentity domainUser) {
		return siteAPI.getHQSite(domainUser);
	}

	public static ConfigrecDTO getConfigrecByName(UserIdentity domainUser, String configName) {
		return configAPI.getConfigrecByName(domainUser, configName);
	}

	public List<VendorItemEntity> getVendorItemsForSkuAndSite(UserIdentity domainUser, Integer skuNo, Integer siteNo) {
		return vendorItemRepository.getVendorItemsForSkuAndSite(skuNo, siteNo);
	}

	public boolean isRunningHQ(UserIdentity domainUser) {
		return siteAPI.isRunningHQ();
	}

	public Integer findNewSkuNumber(UserIdentity domainUser) {
		if (domainUser == null) {
			throw new RNetUnexpectedException("UserIdentity is required.");
		}
		// If you get a mutating trigger error when running this, then you need
		// to uncomment this next block to get around it...
//		 StringBuffer plsqlBlock = new StringBuffer();
//		 plsqlBlock.append(" BEGIN \n");
//		 plsqlBlock.append(" tmxgbl.init;");
//		 plsqlBlock.append(" END; \n");
//
//		 em.getCrudService().executeNativeQueryUpdate(plsqlBlock.toString());

		// first get the NEXT_SKU config (and lock it!)

		/*
		 * Revomed lock because it does not allow updates, which is blocking the creation of new items. William is
		 * following up with Keith Jepson as to why we use configrecs and not a sequence.
		 */
		// ConfigrecEntity nextSkuConfigrecEntity = ConfigHelper.getConfigrecByNameWithLock(em,
		// ConfigNames.InvNextSku.getConfigName());
		ConfigrecDTO nextSkuConfigrecEntity = getConfigrecByName(domainUser,	"NEXT_SKU");
		Integer nextSku = nextSkuConfigrecEntity.getConfigNo().intValue();

		// double check that this sku isn't already in the system
		if (invtoryRepository.findById(new InvtoryEntityId(nextSku)) != null) {
			// NEXT_SKU is messed up! Keep trying the next integer until you
			// reach 999,999,999.
			for (Integer i = nextSku++; i < 999999999; i++) {
				Optional<InvtoryEntity> invtoryEntity = invtoryRepository.findById(new InvtoryEntityId(i));
				if (!invtoryEntity.isPresent()) {
					nextSkuConfigrecEntity.setConfigNo(new BigDecimal(i + 1).setScale(2, BigDecimal.ROUND_HALF_UP));
					configAPI.updateConfigrec(domainUser, nextSkuConfigrecEntity);
					return i;
				}
			}
			// if we get to this point, things are really bad, and there are
			// either just under 1 billion inventory
			// items, or someone has really messed up the NEXT_SKU value. So
			// let's start over and fill in the gaps
			for (Integer i = getConfigrecByName(domainUser, "MIN_SKU").getConfigNo()
					.intValue(); i < 999999999; i++) {
				InvtoryEntityId id = new InvtoryEntityId(i);
				Optional<InvtoryEntity> invtoryEntity = invtoryRepository.findById(id);
				if (!invtoryEntity.isPresent()) {
					nextSkuConfigrecEntity.setConfigNo(new BigDecimal(i + 1).setScale(2, BigDecimal.ROUND_HALF_UP));
					configAPI.updateConfigrec(domainUser, nextSkuConfigrecEntity);
					return i;
				}
			}
			// If you get here, then there really are 1 billion inventory
			// records, throw a really cool exception
			throw new RNetIllegalArgumentException(
					"Congratulations, you have officially maxed out the number of skus that Retail.net allows.  Have a nice day.");
		}
		nextSkuConfigrecEntity.setConfigNo(new BigDecimal(nextSku + 1).setScale(2, BigDecimal.ROUND_HALF_UP));
		configAPI.updateConfigrec(domainUser, nextSkuConfigrecEntity);
		return nextSku;
	}

	public InvtoryEntity createInventory(InvtoryEntity invtoryEntity) {
		return invtoryRepository.save(invtoryEntity);
	}

	public ItemupcEntity createItemUpc(ItemupcEntity itemupcEntity) {
		return itemupcRepository.save(itemupcEntity);
	}

	public InvbysitEntity createInvbysit(InvbysitEntity invbysitEntity) {
		return invbysitRepository.save(invbysitEntity);
	}

	public VendorItemEntity createVendorItem(VendorItemEntity vendorItemEntity) {
		return vendorItemRepository.save(vendorItemEntity);
	}

	public List<SitgrpAllEntity> getSiteGroupAll(UserIdentity domainUser, String siteGroupId) {
		return sitgrpAllRepository.getSiteGroupAll(siteGroupId);
	}

	public List<VenSiteEntity> getVendorSitesForVendor(UserIdentity domainUser, String vendorId) {
		return venSiteRepository.getVendorSitesForVendor(vendorId);
	}


}
