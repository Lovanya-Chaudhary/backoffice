package com.rediron.platform.domain.rnet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.entities.InvbysitEntity;
import com.rediron.platform.domain.entities.InvtoryEntity;
import com.rediron.platform.domain.entities.ItemupcEntity;
import com.rediron.platform.domain.entities.VendorItemEntity;
import com.rediron.platform.domain.repositories.InvbysitRepository;
import com.rediron.platform.domain.repositories.InvtoryRepository;
import com.rediron.platform.domain.repositories.ItemupcRepository;
import com.rediron.platform.domain.repositories.VendorItemRepository;
import com.rediron.platform.domain.services.LookUpService;
import com.rediron.platform.domain.services.TmxGblService;
import com.rediron.platform.domain.services.VendorService;
import com.rediron.security.common.domain.ConfigrecEntity;
import com.rediron.security.common.repository.ConfigrecRepository;
import com.tomax.api.UserIdentity;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.InvtoryDTO;
import com.tomax.inventory.dto.ItemupcDTO;
import com.tomax.inventory.dto.custom.CreateItemInfoDTO;
import com.tomax.vendor.dto.VendorItemDTO;

/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class BOCreateItem {
	private static Logger LOGGER = LoggerFactory.getLogger(BOCreateItem.class);

	@Autowired
	private LookUpService lookUpService;

	@Autowired
	private InvtoryRepository invtoryRepository;

	@Autowired
	private InvbysitRepository invbysitRepository;

	@Autowired
	private ItemupcRepository itemupcRepository;

	@Autowired
	private VendorItemRepository vendorItemRepository;

	@Autowired
	private Errors errors;
	
	@Autowired
	private VendorValidator vendorValidator;

	@Autowired
	private TmxGblService tmxGblService;

	@Autowired
	private VendorService bVendorService;

	@Autowired
	private ConfigrecRepository configrecRepository;

	public Integer createNewItem(UserIdentity domainUser, CreateItemInfoDTO createItemInfoDTO) {

		LOGGER.info("=== createNewItem ===");
		List<ConfigrecEntity> configrecEntityList = new ArrayList<ConfigrecEntity>();
		InvbysitEntity savedInvbysit = null;
		String siteGroupId = createItemInfoDTO.getSiteGroup();
		List<Integer> siteNos = lookUpService.getSitesFromSiteGroupId(siteGroupId);
		int currentSite = tmxGblService.getCurrentSite();
		InvtoryEntity invtoryEntity = mapToInvtoryEntity(createItemInfoDTO.getInvtoryDTO());
		invtoryEntity.setOwnerId(bVendorService.getOwnerIdFromVendor(currentSite).intValue());

		LOGGER.info("=== Creating invtory ===");
		int skuNo = tmxGblService.getNum("NEXT_SKU").intValue();
		invtoryEntity.setSkuNo(skuNo);
		InventoryValidator.validateInvtory(invtoryEntity);
		invtoryEntity = invtoryRepository.save(invtoryEntity);

		if (invtoryEntity == null || invtoryEntity.getSkuNo() == null)
			throw new ServiceException(errors.getErrors().get("ITEM_NOT_CREATED"));

		InvbysitEntity invbysitEntity = mapToInvBySitEntity(createItemInfoDTO.getInvBySitDTO());
		if(!CollectionUtils.containsInstance(siteNos, currentSite)) {
			invbysitEntity = new InvbysitEntity(invbysitEntity);
			invbysitEntity.setSkuNo(skuNo);
			invbysitEntity.setSiteNo(currentSite);
			LOGGER.info("=== Creating inbysit on current site also ===");
			InventoryValidator.validateInvbysit(invbysitEntity);
			savedInvbysit = invbysitRepository.save(invbysitEntity);

			if (savedInvbysit == null)
				throw new ServiceException(errors.getErrors().get("ITEM_NOT_CREATED"));
			
		}
		for (int siteNo : siteNos) {
			invbysitEntity = new InvbysitEntity(invbysitEntity);
			invbysitEntity.setSkuNo(skuNo);
			invbysitEntity.setSiteNo(siteNo);
			LOGGER.info("=== Creating inbysit ===");
			InventoryValidator.validateInvbysit(invbysitEntity);
			savedInvbysit = invbysitRepository.save(invbysitEntity);

			if (savedInvbysit == null)
				throw new ServiceException(errors.getErrors().get("ITEM_NOT_CREATED"));
		}
		
		

		ItemupcEntity itemupcEntity = mapToItemUpcEntity(createItemInfoDTO.getItemUpcDTO());

		LOGGER.info("=== Creating item upc ===");
		List<ItemupcEntity> dup = itemupcRepository.findByIdentifierUpcId(itemupcEntity.getUpcId());
		List<Integer> modifiers = new ArrayList<>();
		if(!CollectionUtils.isEmpty(dup)) {
			for(ItemupcEntity entity : dup) {
				modifiers.add(entity.getUpcModifier());
			}
			itemupcEntity.setUpcModifier(Collections.max(modifiers)+1);
		}else
			itemupcEntity.setUpcModifier(itemupcEntity.getUpcModifier());
			
		itemupcEntity.setSkuNo(skuNo);

		configrecRepository.findByConfigrecEntityIdConfigName("INVENTOR_FMB_RCPT_DESC_LEN");
		configrecEntityList.add(configrecRepository.findByConfigrecEntityIdConfigName("UPC_STORED_LEN"));
		configrecEntityList.add(configrecRepository.findByConfigrecEntityIdConfigName("NUMERIC_UPC"));

		InventoryValidator.validateUPCID(itemupcEntity, configrecEntityList);
		itemupcEntity = itemupcRepository.save(itemupcEntity);
		if (itemupcEntity == null)
			throw new ServiceException(errors.getErrors().get("ITEM_NOT_CREATED"));

		VendorItemEntity vendorItemEntity = mapToVendorEntity(createItemInfoDTO.getVendorItemDTO());
		LOGGER.info("=== Creating vendor ===");

		vendorItemEntity.setSkuNo(skuNo);
		vendorItemEntity.setSiteNo(currentSite);
		vendorValidator.validateVendorItemCurrencyPackCost(domainUser, vendorItemEntity);
		vendorValidator.validateVendorItem(domainUser, vendorItemEntity);
		vendorItemEntity.setSeqNo(bVendorService.getNextVendorItemSeqNo(domainUser).toBigInteger());
		vendorItemEntity = vendorItemRepository.save(vendorItemEntity);
		if (vendorItemEntity == null)
			throw new ServiceException(errors.getErrors().get("ITEM_NOT_CREATED"));

		return skuNo;
	}

	private VendorItemEntity mapToVendorEntity(VendorItemDTO vendorItemDTO) {

		VendorItemEntity venEntity = new VendorItemEntity();
		venEntity.setVendorId(vendorItemDTO.getVendorId());
		venEntity.setVendorItem(vendorItemDTO.getVendorItem());
		venEntity.setPackCost(vendorItemDTO.getPackCost());
		venEntity.setPackQty(vendorItemDTO.getPackQty());
		venEntity.setPackUm(vendorItemDTO.getPackUm());
		venEntity.setMasterPackQty(vendorItemDTO.getMasterPackQty());
		venEntity.setLeadTime(vendorItemDTO.getLeadTime());
		venEntity.setEoq(vendorItemDTO.getEoq());
		venEntity.setOrderAvailabilityStatus(vendorItemDTO.getOrderAvailabilityStatus());
		venEntity.setPackWeight(vendorItemDTO.getPackWeight());
		venEntity.setPackCube(vendorItemDTO.getPackCube());
		venEntity.setVenType("P");
		venEntity.setMainVendorItemFl("Y");
		venEntity.setVendorCurrencyPackCost(vendorItemDTO.getPackCost());
		venEntity.setOperationType("I");
		venEntity.setCostFactor(new BigDecimal(1));
		return venEntity;
	}

	private ItemupcEntity mapToItemUpcEntity(ItemupcDTO itemUpcDTO) {

		ItemupcEntity itemUpc = new ItemupcEntity();
		itemUpc.setUpcId(itemUpcDTO.getUpcId());
		itemUpc.setTypeCd(itemUpcDTO.getTypeCd());
		itemUpc.setUpcModifier(itemUpcDTO.getUpcModifier());
		itemUpc.setUpcSeq(itemUpcDTO.getUpcSeq());
		itemUpc.setPriceAdjAmt(itemUpcDTO.getPriceAdjAmt());

		return itemUpc;
	}

	private InvbysitEntity mapToInvBySitEntity(InvbysitDTO invBySitDTO) {
		InvbysitEntity invbysitEntity = new InvbysitEntity();
		// prices 1 - 10
		invbysitEntity.setPrice1(invBySitDTO.getPrice1());
		invbysitEntity.setPrice2(invBySitDTO.getPrice2());
		invbysitEntity.setPrice3(invBySitDTO.getPrice3());
		invbysitEntity.setPrice4(invBySitDTO.getPrice4());
		invbysitEntity.setPrice5(invBySitDTO.getPrice5());

		invbysitEntity.setPrice6(invBySitDTO.getPrice6());
		invbysitEntity.setPrice7(invBySitDTO.getPrice7());
		invbysitEntity.setPrice8(invBySitDTO.getPrice8());
		invbysitEntity.setPrice9(invBySitDTO.getPrice9());
		invbysitEntity.setPrice10(invBySitDTO.getPrice10());

		invbysitEntity.setPriceQty1(invBySitDTO.getPriceQty1());
		invbysitEntity.setPriceQty2(invBySitDTO.getPriceQty2());
		invbysitEntity.setPriceQty3(invBySitDTO.getPriceQty3());
		invbysitEntity.setPriceQty4(invBySitDTO.getPriceQty4());
		invbysitEntity.setPriceQty5(invBySitDTO.getPriceQty5());

		invbysitEntity.setPriceQty6(invBySitDTO.getPriceQty6());
		invbysitEntity.setPriceQty7(invBySitDTO.getPriceQty7());
		invbysitEntity.setPriceQty8(invBySitDTO.getPriceQty8());
		invbysitEntity.setPriceQty9(invBySitDTO.getPriceQty9());
		invbysitEntity.setPriceQty10(invBySitDTO.getPriceQty10());

		invbysitEntity.setLaloGm(invBySitDTO.getLaloGm());
		invbysitEntity.setHaloGm(invBySitDTO.getHaloGm());
		invbysitEntity.setPurchaseVolumeId(invBySitDTO.getPurchaseVolumeId());
		invbysitEntity.setAllowPchgFl(invBySitDTO.getAllowPchgFl());
		invbysitEntity.setDownloadFl(invBySitDTO.getDownloadFl());
		invbysitEntity.setDiscontinuedStFl(invBySitDTO.getDiscontinuedStFl());
		invbysitEntity.setNonDiscountFl(invBySitDTO.getNonDiscountFl());

		invbysitEntity.setPkgPriceQty1(invBySitDTO.getPkgPriceQty1());
		invbysitEntity.setPkgPriceQty2(invBySitDTO.getPkgPriceQty2());
		invbysitEntity.setPkgPriceQty3(invBySitDTO.getPkgPriceQty3());
		invbysitEntity.setPkgPrice1(invBySitDTO.getPkgPrice1());
		invbysitEntity.setPkgPrice2(invBySitDTO.getPkgPrice2());
		invbysitEntity.setPkgPrice3(invBySitDTO.getPkgPrice3());

		invbysitEntity.setMsrpQty(invBySitDTO.getMsrpQty());
		invbysitEntity.setMsrpPrice(invBySitDTO.getMsrpPrice());
		invbysitEntity.setEpoPct(invBySitDTO.getEpoPct());

		invbysitEntity.setSkuStatusId(invBySitDTO.getSkuStatusId());
		invbysitEntity.setRestrictSaleId(invBySitDTO.getRestrictSaleId());
		invbysitEntity.setPosSkuStatusId(invBySitDTO.getPosSkuStatusId());
		invbysitEntity.setItemizerStatusId(invBySitDTO.getItemizerStatusId());

		invbysitEntity.setReorderPt(invBySitDTO.getReorderPt());
		invbysitEntity.setReorderTo(invBySitDTO.getReorderTo());
		invbysitEntity.setReorderLockedFl(invBySitDTO.getReorderLockedFl());
		invbysitEntity.setReplenishmentFl(invBySitDTO.getReplenishmentFl());
		invbysitEntity.setVelocityLockedFl(invBySitDTO.getVelocityLockedFl());
		invbysitEntity.setVelocityCd(invBySitDTO.getVelocityCd());
		invbysitEntity.setClassCode(invBySitDTO.getClassCode());
		invbysitEntity.setMaxOrderQty(invBySitDTO.getMaxOrderQty());
		invbysitEntity.setWarehouseInnerPack(invBySitDTO.getWarehouseInnerPack());
		invbysitEntity.setProductStartDt(invBySitDTO.getProductStartDt());
		invbysitEntity.setProductEndDt(invBySitDTO.getProductEndDt());
		invbysitEntity.setLastReplenishmentDt(invBySitDTO.getLastReplenishmentDt());
		invbysitEntity.setDefaultDcSkuLevel(invBySitDTO.getDefaultDcSkuLevel());

		invbysitEntity.setCommissionCd(invBySitDTO.getCommissionCd());
		invbysitEntity.setTareTableNo(invBySitDTO.getTareTableNo());
		invbysitEntity.setBottleLinkNo(invBySitDTO.getBottleLinkNo());
		invbysitEntity.setAdditionalTax(invBySitDTO.getAdditionalTax());
		return invbysitEntity;
	}

	private InvtoryEntity mapToInvtoryEntity(InvtoryDTO invtoryDTO) {

		InvtoryEntity invEntity = new InvtoryEntity();
		invEntity.setPackupActionId(invtoryDTO.getPackupActionId());
//		invEntity.setOwnerId(ownerId);

		invEntity.setDescription(invtoryDTO.getDescription());
		invEntity.setReceiptDesc(invtoryDTO.getReceiptDesc());
		invEntity.setLookupDesc(invtoryDTO.getLookupDesc());

		invEntity.setDeptNo(invtoryDTO.getDeptNo());
		invEntity.setClassNo(invtoryDTO.getClassNo());
		invEntity.setLineNo(invtoryDTO.getLineNo());

		invEntity.setReportFactor(invtoryDTO.getReportFactor());
		invEntity.setReportUm(invtoryDTO.getReportUm());
		invEntity.setSellUm(invtoryDTO.getSellUm());

		invEntity.setCatchWeightFl(invtoryDTO.getCatchWeightFl());
		invEntity.setWeighedItemFl(invtoryDTO.getWeighedItemFl());
		invEntity.setUnitCube(invtoryDTO.getUnitCube());
		invEntity.setUnitWeight(invtoryDTO.getUnitWeight());
		invEntity.setTareTableNo(invtoryDTO.getTareTableNo());

		invEntity.setMfgCd(invtoryDTO.getMfgCd());

		invEntity.setRegionalSupersedeFl(invtoryDTO.getRegionalSupersedeFl());
		invEntity.setReplacementSkuNo(invtoryDTO.getReplacementSkuNo());
		invEntity.setLcuQty(invtoryDTO.getLcuQty());
		invEntity.setInvType(invtoryDTO.getInvType());
		invEntity.setEmployeeId(invtoryDTO.getEmployeeId());
		invEntity.setReportCd(invtoryDTO.getReportCd());
		invEntity.setLinkSkuNo(invtoryDTO.getLinkSkuNo());

		invEntity.setWarehouseInnerPack(invtoryDTO.getWarehouseInnerPack());
		invEntity.setPriceListSeq(invtoryDTO.getPriceListSeq());
		invEntity.setWeeksHistory(invtoryDTO.getWeeksHistory());
		invEntity.setPeriodsHistory(invtoryDTO.getPeriodsHistory());
		invEntity.setGlCatId(invtoryDTO.getGlCatId());
		invEntity.setXrefNo(invtoryDTO.getXrefNo());

		invEntity.setEquivUm(invtoryDTO.getEquivUm());
		invEntity.setEquivFactor(invtoryDTO.getEquivFactor());
		invEntity.setPrivateBrandFl(invtoryDTO.getPrivateBrandFl());
		invEntity.setLabelDesc1(invtoryDTO.getLabelDesc1());
		invEntity.setLabelDesc2(invtoryDTO.getLabelDesc2());

		invEntity.setTenderProgramId(invtoryDTO.getTenderProgramId());

		invEntity.setUseSerialFl(invtoryDTO.getUseSerialFl());
		invEntity.setAutoGenSerialsFl(invtoryDTO.getAutoGenSerialsFl());

		// non rev item
		invEntity.setSpecialItemFl(invtoryDTO.getSpecialItemFl());
		invEntity.setStoreCpnFl(invtoryDTO.getStoreCpnFl());
		invEntity.setNonInventoryFl(invtoryDTO.getNonInventoryFl());
		invEntity.setSubInvFl(invtoryDTO.getSubInvFl());
		invEntity.setSupplyItemFl(invtoryDTO.getSupplyItemFl());
		invEntity.setCoreFl(invtoryDTO.getCoreFl());
		invEntity.setFlexibleItemFl(invtoryDTO.getFlexibleItemFl());

		invEntity.setReorderFl(invtoryDTO.getReorderFl());
		invEntity.setDiscontinuedFl(invtoryDTO.getDiscontinuedFl());
		invEntity.setObsoleteFl(invtoryDTO.getObsoleteFl());

		invEntity.setCaptureSerialNoFl(invtoryDTO.getCaptureSerialNoFl());
		invEntity.setCaptureOrderNoFl(invtoryDTO.getCaptureOrderNoFl());
		invEntity.setDownloadFl(invtoryDTO.getDownloadFl());
		invEntity.setNegativePriceFl(invtoryDTO.getNegativePriceFl());

		invEntity.setCreateDt(invtoryDTO.getCreateDt());
		invEntity.setChangeDt(invtoryDTO.getChangeDt());
		invEntity.setDateModified(invtoryDTO.getDateModified());

		return invEntity;
	}
}
