package com.rediron.platform.domain.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.models.ItemLookUpModel;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.config.domainRef.api.DomainRefAPI;
import com.tomax.config.domainRef.dto.DomainRefCodeDTO;
import com.tomax.invVendorHelper.InventoryVendorDomainHelperAPI;
import com.tomax.inventory.api.InventoryAPI;
import com.tomax.inventory.dto.ClassDTO;
import com.tomax.inventory.dto.ClassDTOId;
import com.tomax.inventory.dto.DeptDTOId;
import com.tomax.inventory.dto.DeptgrpDTO;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTO;
import com.tomax.inventory.dto.LineDTO;
import com.tomax.inventory.dto.StatusClassHdrDTO;
import com.tomax.inventory.dto.custom.InventoryGlobalInfoDTO;
import com.tomax.inventory.dto.custom.InventorySearchDTO;
import com.tomax.inventory.dto.custom.InventorySearchResultsDTO;
import com.tomax.inventory.dto.custom.ItemSearchContextEnum;
import com.tomax.site.api.SiteAPI;
import com.tomax.site.dto.SiteDTO;
import com.tomax.vendor.api.VendorAPI;
import com.tomax.vendor.dto.custom.VenItemInfoSearchDTO;
import com.tomax.vendor.dto.custom.VendorItemInfoDTO;


@Service
public class CoreApiService {
	
	Logger logger = LoggerFactory.getLogger(InventoryService.class);

	// TODO - Later these rnet-dpmain api call needs to replaced with their
	// respective JPA repository.
	// Each microservice should be independent

	// Backbone API for rnet-domain
	private static InventoryAPI inventoryAPI = RNetApiContexts.hq.getStateless(InventoryAPI.class);
	private static InventoryVendorDomainHelperAPI inventoryVendorDomainHelperAPI = RNetApiContexts.hq
			.getStateless(InventoryVendorDomainHelperAPI.class);
	private static DomainRefAPI domainRefAPI = RNetApiContexts.hq.getStateless(DomainRefAPI.class);
	private static SiteAPI siteAPI = RNetApiContexts.hq.getStateless(SiteAPI.class);
	private static VendorAPI vendorAPI = RNetApiContexts.hq.getStateless(VendorAPI.class);
	
	@Autowired
	private Errors errors;
	
	/**
	 * Find Inventory Item
	 *
	 * @param domainUser
	 * @param siteNo
	 * @param itemLookup
	 * @return invItems
	 * @throws ItemNotFoundException
	 */
	public List<InventorySearchResultsDTO> findInventoryItem(UserIdentity domainUser, Integer siteNo,
			ItemLookUpModel itemLookup) throws ServiceException {
		List<InventorySearchResultsDTO> invItems = null;
		try {
			InventorySearchDTO invSearchDTO = buildInventorySearchDTO(itemLookup);
			invItems = inventoryVendorDomainHelperAPI.findItem(domainUser, invSearchDTO, siteNo, 0, 100);
			if (invItems == null || invItems.size() == 0) {
				throw new ServiceException(errors.getErrors().get("ITEM_NOT_FOUND").formatMessage(itemLookup.getSiteNo()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
		return invItems;
	}
	
	private InventorySearchDTO buildInventorySearchDTO(ItemLookUpModel itemLookup) {
		InventorySearchDTO invSearchDTO = new InventorySearchDTO();
		invSearchDTO.setDoLocalSearch(false);
		invSearchDTO.setItemSearchContext(ItemSearchContextEnum.ALL);
		invSearchDTO.setSkuNo(itemLookup.getSkuNo());
		invSearchDTO.setMfgCd(StringUtils.trim(itemLookup.getMfgCd()));
		invSearchDTO.setUpcId(StringUtils.trim(itemLookup.getUPCId()));
		invSearchDTO.setVendorId(StringUtils.trim(itemLookup.getVendorId()));
		invSearchDTO.setVendorItem(StringUtils.trim(itemLookup.getVendorItem()));
		invSearchDTO.setDeptgrpNo(itemLookup.getDeptGroup());
		invSearchDTO.setDeptNo(itemLookup.getDeptNo());
		invSearchDTO.setClassNo(itemLookup.getClassNo());
		invSearchDTO.setLineNo(itemLookup.getLineNo());
		invSearchDTO.setLookupDesc(StringUtils.trim(itemLookup.getLookUpDescription()));
		invSearchDTO.setItemAttribute1(StringUtils.trim(itemLookup.getCustomAttributeValue1()));
		invSearchDTO.setItemAttribute2(StringUtils.trim(itemLookup.getCustomAttributeValue2()));
		invSearchDTO.setItemAttribute3(StringUtils.trim(itemLookup.getCustomAttributeValue3()));
		return invSearchDTO;
	}
	
	/**
	 * Get InventoryItemGlobalInfo
	 *
	 * @param domainUser
	 * @param skuNumber
	 * @param siteNumber
	 * @return InventoryGlobalInfoDTO
	 */
	public InventoryGlobalInfoDTO getInventoryItemGlobalInfo(UserIdentity domainUser, int skuNumber, int siteNumber) {
		return inventoryAPI.getInventoryItemGlobalInfo(domainUser, skuNumber, siteNumber);
	}
	
	/**
	 * Get KitInfoBySkuNo
	 *
	 * @param domainUser
	 * @param skuNumber
	 * @return InventoryGlobalInfoDTO
	 */
	public InventoryGlobalInfoDTO getKitInfoBySkuNo(UserIdentity domainUser, int skuNumber) {
		return inventoryAPI.getKitInfoBySkuNo(domainUser, skuNumber);
	}
	
	/**
	 * Get ItemRestrictDescription
	 * 
	 * @param domainUser
	 * @param skuNumber
	 * @param siteNumber
	 * @return StatusClassHdrDTO
	 */
	public StatusClassHdrDTO getItemRestrictDescription(UserIdentity domainUser, int skuNumber, int siteNumber) {
		return inventoryAPI.getCurrentItemRestrictDescription(domainUser, skuNumber, siteNumber);
	}
	
	/**
	 * Get CurrentItemPosStatus
	 * 
	 * @param domainUser
	 * @param skuNumber
	 * @param siteNumber
	 * @return StatusClassHdrDTO
	 */
	public StatusClassHdrDTO getItemPosStatus(UserIdentity domainUser, int skuNumber, int siteNumber) {
		return inventoryAPI.getCurrentItemPosStatus(domainUser, skuNumber, siteNumber);
	}
	
	/**
	 * Get DeptGroups
	 *
	 * @param domainUser
	 * @return listDeptGrp
	 */
	public List<DeptgrpDTO> getDeptGroups(UserIdentity domainUser) {
		return inventoryAPI.getDeptGroups(domainUser);
	}
	
	/**
	 * Get Classes
	 * 
	 * @param domainUser
	 * @param deptNo
	 * @return
	 */
	public List<ClassDTO> getClasses(UserIdentity domainUser, int deptNo) {
		return inventoryAPI.getClasses(domainUser, new DeptDTOId(deptNo));
	}

	/**
	 * Get Lines
	 * 
	 * @param domainUser
	 * @param deptNo
	 * @param classNo
	 * @return
	 */
	public List<LineDTO> getLines(UserIdentity domainUser, int deptNo, int classNo) {
		return inventoryAPI.getLines(domainUser, new ClassDTOId(deptNo, classNo));

	}
	
	/**
	 * Find list of duplicate items
	 *
	 * @param domainUser
	 * @param isUPC
	 * @param lookUpCd
	 * @param siteNo
	 * @return duplicates
	 */
	public List<InventorySearchResultsDTO> findDuplicates(UserIdentity domainUser, Boolean isUPC, String lookUpCd,
			Integer siteNo) {
		InventorySearchDTO searchPojo = new InventorySearchDTO();
		searchPojo.setDoLocalSearch(false);
		searchPojo.setItemSearchContext(ItemSearchContextEnum.ALL);
		searchPojo.setMfgCd(StringUtils.trim(lookUpCd));
		if (isUPC) {
			logger.info("Setting upcId...");
			searchPojo.setUpcId(StringUtils.trim(lookUpCd));
			searchPojo.setMfgCd(null);
		}

		return inventoryVendorDomainHelperAPI.findItem(domainUser, searchPojo, siteNo, 0, 100);
	}
	
	public List<VendorItemInfoDTO> getItemVendorInformation(UserIdentity domainUser, int skuNumber, String vendorId,
			String siteGrp) {
		VenItemInfoSearchDTO venItemInfoSearchDTO = new VenItemInfoSearchDTO(skuNumber, siteGrp, false, vendorId);
		return vendorAPI.getVendorItemInfo(domainUser, venItemInfoSearchDTO);
	}
	
	/**
	 * Get all Item Type
	 *
	 * @param domainUser
	 * @return
	 */
	public List<ItemCreationDefaultsHdrDTO> getItemType(UserIdentity domainUser) {
		List<ItemCreationDefaultsHdrDTO> sortedItemTypes = new ArrayList<>();
		List<ItemCreationDefaultsHdrDTO> dtos = inventoryAPI.getItemCreationItemTypes(domainUser);
		for (ItemCreationDefaultsHdrDTO dto : dtos) {
			if (dto != null) {
				sortedItemTypes.add(new ItemCreationDefaultsHdrDTO(dto));
			}
		}
		Collections.sort(sortedItemTypes);
		return sortedItemTypes;
	}
	
	/**
	 * Get UM Codes
	 * 
	 * @param domainUser
	 * @param packUmCodeValues
	 * @return
	 */
	public List<DomainRefCodeDTO> getUMCodes(UserIdentity domainUser, String packUmCodeValues) {
		return domainRefAPI.getDomainRefCodes(domainUser, packUmCodeValues);
	}

	public List<InvbysitDTO> getInvtoryBySitList(UserIdentity domainUser, int sku, String site) {
		// TODO Auto-generated method stub
		

		List<SiteDTO> siteDtos = null;
		List<InvbysitDTO> listInvBySit = null;
		try {
			siteDtos = siteAPI.getSitesInfoForGroup(domainUser, site, 0, 0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in siteAPI.getSitesInfoForGroup() == >> "+e.getLocalizedMessage());
		}
		
		List<Integer> listOfSites = new ArrayList<>();
		
		for(SiteDTO siteDto : siteDtos) {
			
			if(siteDto.getSiteNo() != null && siteDto.getSiteNo() > 0)
				listOfSites.add(siteDto.getSiteNo());
		}
		
		try {
			listInvBySit = inventoryAPI.getInvbysit(domainUser, sku, listOfSites);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in inventoryAPI.getInvbysit() == >> "+e.getLocalizedMessage());
		}
		
		return listInvBySit;
	}

}
