package com.rediron.platform.domain.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.constants.IErrorConstants;
import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.constants.UserDefinedTypeCode;
import com.rediron.platform.domain.model.request.AttributeModel;
import com.rediron.platform.domain.model.request.ChangeSiteInfoRequest;
import com.rediron.platform.domain.model.request.InvBySiteModel;
import com.rediron.platform.domain.model.request.ItemCreationDefaultsInfoRequest;
import com.rediron.platform.domain.model.request.ItemNotesModel;
import com.rediron.platform.domain.model.request.ItemUpcModel;
import com.rediron.platform.domain.model.request.StatusInfoModel;
import com.rediron.platform.domain.model.request.SummaryInfoRequest;
import com.rediron.platform.domain.model.request.UpdateInvbysitRequest;
import com.rediron.platform.domain.model.response.AttributeNameResponse;
import com.rediron.platform.domain.model.response.ClassDetails;
import com.rediron.platform.domain.model.response.CodeDetailsResponse;
import com.rediron.platform.domain.model.response.Department;
import com.rediron.platform.domain.model.response.DepartmentGroupResponse;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.model.response.GLCategoryInfoResponse;
import com.rediron.platform.domain.model.response.Invbysit;
import com.rediron.platform.domain.model.response.InvbysitAndPromoDetails;
import com.rediron.platform.domain.model.response.Inventory;
import com.rediron.platform.domain.model.response.ItemAttributeResponse;
import com.rediron.platform.domain.model.response.ItemAttributeValueResponse;
import com.rediron.platform.domain.model.response.ItemCodeResponse;
import com.rediron.platform.domain.model.response.ItemCreationDefaultsHdr;
import com.rediron.platform.domain.model.response.ItemNote;
import com.rediron.platform.domain.model.response.ItemUpcResponse;
import com.rediron.platform.domain.model.response.Line;
import com.rediron.platform.domain.model.response.PromoInfo;
import com.rediron.platform.domain.model.response.SkuInfoResponse;
import com.rediron.platform.domain.model.response.StatusClassHdr;
import com.rediron.platform.domain.model.response.SummaryInfoResponse;
import com.rediron.platform.domain.model.response.TenderCertificateInfoResponse;
import com.rediron.platform.domain.model.response.UpdatedAttributeResponse;
import com.rediron.platform.domain.model.response.VendorImportInfoResponse;
import com.rediron.platform.domain.models.ItemCodesModel;
import com.rediron.platform.domain.models.ItemLookUpModel;
import com.rediron.platform.domain.models.ItemNotesRequest;
import com.rediron.platform.domain.repositories.InventoryRepository;
import com.rediron.platform.domain.response.AttributeListValuesInfoResponse;
import com.rediron.platform.domain.response.DealsInfoResponse;
import com.rediron.platform.domain.response.DuplicateItemInfoResponse;
import com.rediron.platform.domain.response.GlobalInfoResponse;
import com.rediron.platform.domain.response.InventorySearchInfoResponse;
import com.rediron.platform.domain.response.ItemTypeCodeResponse;
import com.rediron.platform.domain.response.SiteGroupInfoResponse;
import com.rediron.platform.domain.response.SiteInfoResponse;
import com.rediron.platform.domain.response.StatesProvinceInfoResponse;
import com.rediron.platform.domain.response.SuccessResponse;
import com.rediron.platform.domain.response.VendorDetailsInfoResponse;
import com.rediron.platform.domain.util.CustomUtils;
import com.rediron.platform.domain.util.InventoryUtil;
import com.rediron.platform.domain.util.JwtUtils;
import com.rediron.platform.domain.util.MapToResponseUtil;
import com.rediron.platform.util.validator.Validator;
import com.rediron.security.common.domain.ConfigrecEntity;
import com.rediron.security.common.repository.ConfigrecRepository;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.ar.api.AccountsReceivableAPI;
import com.tomax.ar.dto.GlcatDTO;
import com.tomax.config.api.ConfigAPI;
import com.tomax.config.api.ItemIdentifierType;
import com.tomax.config.attribute.api.AttributeAPI;
import com.tomax.config.attribute.dto.AttributeListValueAllDTO;
import com.tomax.config.attribute.dto.AttributeNameDTO;
import com.tomax.config.attribute.dto.ItemAttributeValueDTO;
import com.tomax.config.attribute.dto.custom.AttributeSearchDTO;
import com.tomax.config.domainRef.api.DomainRefAPI;
import com.tomax.config.domainRef.dto.DomainRefCodeDTO;
import com.tomax.config.dto.ConfigrecDTO;
import com.tomax.deal.api.DealAPI;
import com.tomax.deal.dto.DealdtlDTOId;
import com.tomax.deal.dto.DealdtlLiteDTO;
import com.tomax.invVendorHelper.InventoryVendorDomainHelperAPI;
import com.tomax.invVendorHelper.custom.dto.InventoryBySiteAndVendorItemListsDTO;
import com.tomax.invVendorHelper.custom.dto.ItemPrimaryDataDTO;
import com.tomax.inventory.api.InventoryAPI;
import com.tomax.inventory.dto.ClassDTO;
import com.tomax.inventory.dto.ClassDTOId;
import com.tomax.inventory.dto.DeptDTO;
import com.tomax.inventory.dto.DeptDTOId;
import com.tomax.inventory.dto.DeptgrpDTO;
import com.tomax.inventory.dto.DeptgrpDTOId;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.InvtoryDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTO;
import com.tomax.inventory.dto.ItemNoteDTO;
import com.tomax.inventory.dto.ItemupcDTO;
import com.tomax.inventory.dto.LineDTO;
import com.tomax.inventory.dto.StatusClassHdrDTO;
import com.tomax.inventory.dto.TenderCertificateProgramDTO;
import com.tomax.inventory.dto.custom.CostDTO;
import com.tomax.inventory.dto.custom.CreateItemInfoDTO;
import com.tomax.inventory.dto.custom.ExpectedQtyFullDTO;
import com.tomax.inventory.dto.custom.InventoryGeneralSearchDTO;
import com.tomax.inventory.dto.custom.InventoryGlobalInfoDTO;
import com.tomax.inventory.dto.custom.InventorySearchDTO;
import com.tomax.inventory.dto.custom.InventorySearchResultsDTO;
import com.tomax.inventory.dto.custom.ItemAttributeResultDTO;
import com.tomax.inventory.dto.custom.ItemCreationDefaultsInfoDTO;
import com.tomax.inventory.dto.custom.ItemMovementDetailDTO;
import com.tomax.inventory.dto.custom.ItemNoteResultWithDomainRefDTO;
import com.tomax.inventory.dto.custom.ItemReorderPointsDTO;
import com.tomax.inventory.dto.custom.ItemSearchContextEnum;
import com.tomax.inventory.dto.custom.ReservedQtyFullDTO;
import com.tomax.locale.api.LocaleAPI;
import com.tomax.locale.dto.GeographicRegionDTO;
import com.tomax.posapi.ItemNotFoundException;
import com.tomax.site.api.SiteAPI;
import com.tomax.site.dto.GroupsAllDTO;
import com.tomax.site.dto.SiteDTO;
import com.tomax.util.domain.ErrorDTO;
import com.tomax.vendor.api.VendorAPI;
import com.tomax.vendor.dto.VendorDTO;
import com.tomax.vendor.dto.VendorItemDTO;
import com.tomax.vendor.dto.custom.VenItemInfoSearchDTO;
import com.tomax.vendor.dto.custom.VendorItemInfoDTO;

@Service
public class InventoryService {
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
	private static DealAPI dealAPI = RNetApiContexts.hq.getStateless(DealAPI.class);
	private static AttributeAPI attributeAPI = RNetApiContexts.hq.getStateless(AttributeAPI.class);
	private static AccountsReceivableAPI accountsReceivableAPI = RNetApiContexts.hq
			.getStateless(AccountsReceivableAPI.class);

	private static ConfigAPI configAPI = RNetApiContexts.hq.getStateless(ConfigAPI.class);

	private static LocaleAPI localeAPI = RNetApiContexts.hq.getStateless(LocaleAPI.class);

	private static String cRnetApiHost = null;
	private static long cRnetApiPort = 0;

	@Autowired
	private ConfigrecRepository configrecRepository;

	@Autowired
	private InventoryUtil inventoryUtil;

	@Autowired
	private Validator validator;

	@Autowired
	private Errors errors;

	@Autowired
	private MapToResponseUtil mapToResponseUtil;

	@Autowired
	private InventoryRepository inventoryRepository;

	/**
	 * Get ItemType By Code
	 *
	 * @param domainUser
	 * @param itemTypeCode
	 * @return
	 */
	public ItemCreationDefaultsHdr getCustomItemCreateDefaultHdr(UserIdentity domainUser, String itemTypeCode) {
		ItemCreationDefaultsHdrDTO itemType = null;
		List<ItemCreationDefaultsHdrDTO> dtos = inventoryAPI.getItemCreationItemTypes(domainUser);
		for (ItemCreationDefaultsHdrDTO type : dtos) {
			if (type.getItemTypeCd().equals(itemTypeCode)) {
				itemType = type;
				break;
			}
		}
		ItemCreationDefaultsHdr itemCreationDefaultsHdr = null;
		if (itemType != null)
			itemCreationDefaultsHdr = mapToResponseUtil.getItemCreationDefaultsHdr(itemType);
		return itemCreationDefaultsHdr;
	}

	/**
	 * Get ItemType By Code
	 *
	 * @param domainUser
	 * @param itemTypeCode
	 * @return
	 */
	public ItemCreationDefaultsHdrDTO getItemCreateDefaultHdr(UserIdentity domainUser, String itemTypeCode) {
		ItemCreationDefaultsHdrDTO itemType = null;
		List<ItemCreationDefaultsHdrDTO> dtos = inventoryAPI.getItemCreationItemTypes(domainUser);
		for (ItemCreationDefaultsHdrDTO type : dtos) {
			if (type.getItemTypeCd().equals(itemTypeCode)) {
				itemType = type;
				break;
			}
		}
		return itemType;
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
//		Check for null before mapping
//		List<InventorySearchResult> list = mapToResponseUtil.getInventorySearchResult(inventoryVendorDomainHelperAPI.findItem(domainUser, searchPojo, siteNo, 0, 100));
		return inventoryVendorDomainHelperAPI.findItem(domainUser, searchPojo, siteNo, 0, 100);
	}

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
		InventorySearchDTO invSearchDTO = buildInventorySearchDTO(itemLookup);
		List<InventorySearchResultsDTO> invItems = inventoryVendorDomainHelperAPI.findItem(domainUser, invSearchDTO,
				siteNo, 0, 100);
//		Check for null before mapping
//		List<InventorySearchResult> list = mapToResponseUtil.getInventorySearchResultList(invItems);
		if (invItems == null || invItems.size() == 0) {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.ITEM_NOT_FOUND).formatMessage(siteNo));
		}
		return invItems;
	}

	private void initialize() {

		ConfigrecEntity rnetApiHost = configrecRepository.findByConfigrecEntityIdConfigName("API_SERVICE_HOST");
		ConfigrecEntity rnetApiPort = configrecRepository.findByConfigrecEntityIdConfigName("API_SERVICE_PORT");

		if (logger.isDebugEnabled()) {
			logger.debug(rnetApiHost.getConfigTxt());
			logger.debug(Long.toString(rnetApiPort.getConfigNo().longValue()));
		}

		cRnetApiHost = rnetApiHost.getConfigTxt();
		cRnetApiPort = rnetApiPort.getConfigNo().longValue();

		if (logger.isDebugEnabled()) {
			logger.debug(cRnetApiHost);
			logger.debug(Long.toString(cRnetApiPort));
		}

	}

	/**
	 * GetItemCreationDefaults
	 *
	 * @param domainUser
	 * @return
	 */
	public List<ItemCreationDefaultsHdrDTO> getItemCreationDefaults(UserIdentity domainUser) {

//		Check for null before mapping
//		List<ItemCreationDefaultsHdr> list = mapToResponseUtil.getItemCreationDefaultsHdrList(inventoryAPI.getItemCreationItemTypes(domainUser));
		return inventoryAPI.getItemCreationItemTypes(domainUser);
	}

	// req res changes starts here

	/**
	 * Create New Item From Default value
	 *
	 * @param domainUser
	 * @param createItemDefaultInfoDTO
	 * @param itemCreateDefaultInfoModel
	 * @return createdSkuNo
	 * @throws ItemTypeNotFoundException
	 * @throws ItemNotCreatedException
	 * @throws InventoryException
	 */
	public Integer createItemFromDefaults(UserIdentity domainUser, ItemCreationDefaultsInfoDTO createItemDefaultInfoDTO,
			ItemCreationDefaultsInfoRequest itemCreateDefaultInfoModel) throws ServiceException {
		if (createItemDefaultInfoDTO == null) {
			throw new InvalidParameterException("Create Item Info required.");
		}
		CreateItemInfoDTO createItemInfoDTO = new CreateItemInfoDTO(createItemDefaultInfoDTO);
		Integer createdSkuNo = 0;
		Integer skuNo = itemCreateDefaultInfoModel.getSkuNo();
		if (inventoryUtil.canUserCreateNewItem(domainUser, skuNo)) {
			createItemInfoDTO = inventoryUtil.validateAndMapToDTO(domainUser, createItemInfoDTO,
					itemCreateDefaultInfoModel);
			logger.debug(
					"Creating Item for... " + createItemDefaultInfoDTO.getItemCreationDefaultsHdrDTO().getItemTypeCd());
			createdSkuNo = inventoryAPI.createNewItem(domainUser, createItemInfoDTO);
			logger.info("Created Item for... " + createItemInfoDTO.getInvtoryDTO().getDescription() + "  skuNo: "
					+ createdSkuNo);
		} else {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.USER_NOT_ALLOWED));
		}
		return createdSkuNo;
	}
	// req res changes ends here

	public ItemCreationDefaultsInfoDTO getItemCreationDefaultsInfo(UserIdentity domainUser, String itemTypeCd)
			throws InvalidParameterException {
		if (itemTypeCd == null) {
			throw new InvalidParameterException("Item Type Code required");
		}
		ItemCreationDefaultsHdrDTO itemType = getItemCreateDefaultHdr(domainUser, itemTypeCd);

		if (itemType == null) {
			throw new ServiceException(errors.getErrors().get(IErrorConstants.ITEM_TYPE_NOT_FOUND));
		}
		return inventoryAPI.getItemCreationDefaults(domainUser, itemType.getItemCreationId());
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

	public List<InventorySearchResultsDTO> findInventory(UserIdentity domainUser, Integer siteNo,
			ItemLookUpModel itemLookup) throws ServiceException {

		InventorySearchDTO invSearchDTO = buildInventorySearchDTO(itemLookup);

		// Response response = null;
		String reponseJson = null;
		String jwtToken = null;

		List<InventorySearchResultsDTO> invItems = null;

		try {
			if (cRnetApiHost == null) {
				this.initialize();
			}

			jwtToken = JwtUtils.generateToken(domainUser);
			if (logger.isDebugEnabled()) {
				logger.debug("findInventory jwt -> " + jwtToken);
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + jwtToken);
			headers.set("siteNo", siteNo.toString());

			HttpEntity<InventorySearchDTO> requestEntity = new HttpEntity<InventorySearchDTO>(invSearchDTO, headers);

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

			ResponseEntity<String> invSearchResponse = restTemplate.exchange(
					"http://" + cRnetApiHost + ":" + cRnetApiPort + "/rnet/api/rest/item/findInventory",
					HttpMethod.POST, requestEntity, String.class);

			reponseJson = invSearchResponse.getBody();
			if (logger.isDebugEnabled()) {
				logger.debug(reponseJson);
			}

			invItems = new ArrayList<InventorySearchResultsDTO>();

			ObjectMapper objectMapper = new ObjectMapper();
			try {
				invItems = objectMapper.readValue(invSearchResponse.getBody(),
						new TypeReference<List<InventorySearchResultsDTO>>() {
						});
				// check errorLovanya
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (invItems == null || invItems.size() == 0) {
				throw new ServiceException(
						errors.getErrors().get(IErrorConstants.ITEM_NOT_FOUND).formatMessage(itemLookup.getSiteNo()));
			}

			// response = Response.status(Status.OK).entity(reponseJson).build();
		} catch (HttpClientErrorException hcee) {

			// check errorLovanya
//			if (hcee.getRawStatusCode() == Status.NOT_FOUND.getStatusCode()) {
//				throw new ItemNotFoundException(hcee.getMessage());
//			}
		}
//		Check for null before mapping
//		List<InventorySearchResult> list = mapToResponseUtil.getInventorySearchResultList(invItems);
		return invItems;
	}

	/**
	 * Get Item CurrentStatusDescription
	 * 
	 * @param domainUser
	 * @param skuNumber
	 * @param siteNumber
	 * @return
	 */
	public StatusClassHdr getItemCurrentStatusDescription(UserIdentity domainUser, int skuNumber, int siteNumber) {
//		Check for null before mapping
		StatusClassHdrDTO currentStatusDescription = inventoryAPI.getCurrentItemStatusDescription(domainUser, skuNumber,
				siteNumber);
		StatusClassHdr statusClassHdr = null;
		if (currentStatusDescription != null)
			statusClassHdr = mapToResponseUtil.getStatusClassHdr(currentStatusDescription);
		return statusClassHdr;
	}

	/**
	 * Get UM Codes
	 * 
	 * @param domainUser
	 * @param packUmCodeValues
	 * @return
	 */
	public List<DomainRefCodeDTO> getUMCodes(UserIdentity domainUser, String packUmCodeValues) {

//		Check for null before mapping
//		List<DomainRefCode> domainRefCode = mapToResponseUtil.getDomainRefCodes(domainRefAPI.getDomainRefCodes(domainUser, packUmCodeValues));
		return domainRefAPI.getDomainRefCodes(domainUser, packUmCodeValues);
	}

	public List<VendorItemInfoDTO> getItemVendorInformation(UserIdentity domainUser, int skuNumber, String vendorId,
			String siteGrp) {
		VenItemInfoSearchDTO venItemInfoSearchDTO = new VenItemInfoSearchDTO(skuNumber, siteGrp, false, vendorId);
		return vendorAPI.getVendorItemInfo(domainUser, venItemInfoSearchDTO);
	}

	// changes start here

	public SkuInfoResponse getDetailSKUInfo(UserIdentity domainUser, int skuNumber, int siteNumber)
			throws ServiceException {
		// TODO Auto-generated method stub

		// 1. details from global and search apis
		InventoryGlobalInfoDTO globalInfo = inventoryAPI.getInventoryItemGlobalInfo(domainUser, skuNumber, siteNumber);
		InventorySearchResultsDTO inventorySearchResultsDTO = null;
		SkuInfoResponse detailSKU = new SkuInfoResponse();
		ItemupcDTO itemPlu = null;

		ItemLookUpModel itemLookup = new ItemLookUpModel();
		itemLookup.setSkuNo(skuNumber);
		itemLookup.setSiteNo(siteNumber);

		List<InventorySearchResultsDTO> searchResult = findInventoryItem(domainUser, skuNumber, itemLookup);
		if (CollectionUtils.isNotEmpty(searchResult)) {
			inventorySearchResultsDTO = searchResult.get(0);
		}

		if (globalInfo != null) {
			detailSKU = mapToResponseUtil.getSkuInfoResponse(globalInfo);
		}

		if (inventorySearchResultsDTO != null) {

			if (StringUtils.isNotBlank(inventorySearchResultsDTO.getSellUm()))
				detailSKU.setSellUm(inventorySearchResultsDTO.getSellUm());

			if (StringUtils.isNotBlank(inventorySearchResultsDTO.getReportUm()))
				detailSKU.setReportUm(inventorySearchResultsDTO.getReportUm());
		}

		// 2. details from item upc api
		List<ItemupcDTO> list = inventoryAPI.getItemUPCBySku(domainUser, skuNumber);
		if (!list.isEmpty()) {

			for (ItemupcDTO itemUPC : list) {

				if (itemUPC.getUpcSeq() != null && itemUPC.getUpcSeq() == 1) {

					if (StringUtils.isNotBlank(itemUPC.getIdentifier().getUpcId()))
						detailSKU.setPrimaryUPC(itemUPC.getIdentifier().getUpcId());

					if (itemUPC.getIdentifier().getUpcModifier() != null
							&& itemUPC.getIdentifier().getUpcModifier() > 0)
						detailSKU.setUpcModifier(itemUPC.getIdentifier().getUpcModifier());

//					if (StringUtils.isNotBlank(itemUPC.getTypeCd()))
//						detailSKU.setPlu(itemUPC.getTypeCd());

				}
			}
		}

		List<ItemupcDTO> itemupcForPlu = inventoryAPI.getItemupcBySkuAndTypeCd(domainUser, skuNumber, "P");
		if (CollectionUtils.isNotEmpty(itemupcForPlu)) {
			itemPlu = itemupcForPlu.get(0);
		}

		if (itemPlu != null) {

			if (StringUtils.isNotBlank(itemPlu.getUpcId()))
				detailSKU.setPlu(itemPlu.getUpcId());
		}
		// 3. details from vendor api
		VendorItemDTO venItem = vendorAPI.getPrimaryVendorItem(domainUser, siteNumber, skuNumber);

		if (venItem != null) {

			if (StringUtils.isNotBlank(venItem.getVendorId()))
				detailSKU.setId(venItem.getVendorId());

			if (StringUtils.isNotBlank(venItem.getVendorItem()))
				detailSKU.setPrimaryVendorItem(venItem.getVendorItem());
		}

		BigDecimal countSheetCount = inventoryUtil.getCNTSheetCount(domainUser, skuNumber);
		BigDecimal kitHdrCount = inventoryUtil.getKitHdrCount(domainUser, skuNumber);

		if (countSheetCount != null && countSheetCount.doubleValue() > 1)
			detailSKU.setCountSheetItem(true);

		if (kitHdrCount != null && kitHdrCount.doubleValue() > 1)
			detailSKU.setKitItem(true);

		return detailSKU;
	}

	public List<ItemNote> getItemNotesList(UserIdentity domainUser, int skuNumber, int siteNumber) {

		List<ItemNoteDTO> notesList = inventoryAPI.getItemNotes(domainUser, skuNumber, siteNumber);
		List<ItemNote> list = new ArrayList<>();
		if (notesList != null && !notesList.isEmpty()) {
			for (ItemNoteDTO itemNoteDto : notesList) {
				list.add(getItemNoteWithDomainCode(itemNoteDto));
			}
		}
		return list;
	}

	public String getItemAttributeValue(UserIdentity domainUser, int skuNumber, String attrName) {
		// TODO Auto-generated method stub
		return inventoryAPI.getItemAttributeValue(domainUser, skuNumber, attrName);
	}

	public List<ItemAttributeResponse> getItemAttributeBySku(UserIdentity domainUser, int skuNumber, int siteNumber) {
		InventoryGeneralSearchDTO inventoryGeneralSearchDTO = new InventoryGeneralSearchDTO();
		inventoryGeneralSearchDTO.setSkuNum(skuNumber);
		inventoryGeneralSearchDTO.setSite(siteNumber);
		List<ItemAttributeResultDTO> listOfItemAttributes = inventoryAPI.getItemAttributeBySku(domainUser,
				inventoryGeneralSearchDTO);
		List<ItemAttributeResponse> list = new ArrayList<>();
		if (listOfItemAttributes != null && !listOfItemAttributes.isEmpty()) {
			list = mapToResponseUtil.getItemAttributes(listOfItemAttributes);
			list = setFunctionalAreaCode(list, IRnetDomain.FUNC_AREA_CODE);
		}
		return list;
	}

	public Integer getCurrentSite(UserIdentity domainUser) {
		return siteAPI.getCurrentSite(domainUser);
	}

	public List<ItemUpcResponse> getItemupcBySkuAndTypeCd(UserIdentity domainUser, int skuNumber, String typeCode) {
		// TODO Auto-generated method stub

		List<ItemupcDTO> searchResult = inventoryAPI.getItemupcBySkuAndTypeCd(domainUser, skuNumber, typeCode);
		List<ItemUpcResponse> response = new ArrayList<>();

		if (!searchResult.isEmpty())
			response = mapToResponseUtil.getItemUpcList(searchResult);

//		Check for null before mapping
//		List<ItemUpc> itemUpc = mapToResponseUtil.getItemUpcList(inventoryAPI.getItemupcBySkuAndTypeCd(domainUser, skuNumber, typeCode));
		return response;
	}

	public List<ItemUpcResponse> getItemUPCBySku(UserIdentity domainUser, int skuNumber) {
		// TODO Auto-generated method stub

		List<ItemupcDTO> searchResult = inventoryAPI.getItemUPCBySku(domainUser, skuNumber);
		List<ItemUpcResponse> response = new ArrayList<>();

		if (!searchResult.isEmpty())
			response = mapToResponseUtil.getItemUpcList(searchResult);
//		Check for null before mapping
//		List<ItemUpc> itemUpc = mapToResponseUtil.getItemUpcList(inventoryAPI.getItemUPCBySku(domainUser, skuNumber));

		return response;
	}

	public List<ItemPrimaryDataDTO> getItemsPrimaryData(UserIdentity domainUser, int siteNumber, String itemId) {
		// TODO Auto-generated method stub
		return inventoryVendorDomainHelperAPI.getItemsPrimaryData(domainUser, siteNumber, itemId);
	}

	public VendorItemDTO getMainVendorItem(UserIdentity domainUser, int siteNumber, int skuNumber, String vendorId) {
		// TODO Auto-generated method stub
		return inventoryVendorDomainHelperAPI.getMainVendorItem(domainUser, siteNumber, skuNumber, vendorId);
	}

	public VendorItemDTO getPrimaryVendorItem(UserIdentity domainUser, int siteNumber, int skuNumber) {
		// TODO Auto-generated method stub
		return vendorAPI.getPrimaryVendorItem(domainUser, siteNumber, skuNumber);
	}

	public List<ErrorDTO> getPrimaryVendorItemForSiteAndSku(UserIdentity domainUser, int siteNumber, int skuNumber) {
		return vendorAPI.getPrimaryVendorItemForSiteAndSku(domainUser, siteNumber, skuNumber);
	}

	public String getItemIdentifier(UserIdentity domainUser, ItemIdentifierType identifierType, int siteNumber,
			int skuNumber, String vendorId) {
		// TODO Auto-generated method stub
		return inventoryVendorDomainHelperAPI.getItemIdentifier(domainUser, identifierType, siteNumber, skuNumber,
				vendorId);
	}

	public List<VendorItemInfoDTO> getVendorItemInfo(UserIdentity domainUser, int skuNumber, String siteGroup,
			boolean isPrimary, String vendorId) {
		// TODO Auto-generated method stub
		VenItemInfoSearchDTO venItemInfoSearchDTO = new VenItemInfoSearchDTO(skuNumber, siteGroup, isPrimary, vendorId);
		return vendorAPI.getVendorItemInfo(domainUser, venItemInfoSearchDTO);
	}

	public SiteGroupInfoResponse getGroup(UserIdentity domainUser, String groupId) {
		// TODO Auto-generated method stub
		SiteGroupInfoResponse response = new SiteGroupInfoResponse();
		GroupsAllDTO groupsAllDTO = siteAPI.getGroup(domainUser, groupId);

		response = CustomUtils.mapToSiteGroupInfoResponse(groupsAllDTO);

		return response;
	}

	public List<VendorDTO> getVendorsForItem(UserIdentity domainUser, int siteNumber, int skuNumber) {
		// TODO Auto-generated method stub
		return vendorAPI.getVendorsForItem(domainUser, siteNumber, skuNumber);
	}

	public List<VendorDTO> getVendorsForItem(UserIdentity domainUser, int siteNumber, int skuNumber,
			boolean showActiveVendors) {
		// TODO Auto-generated method stub
		return vendorAPI.getVendorsForItem(domainUser, siteNumber, skuNumber, showActiveVendors);
	}

	public DealsInfoResponse getDealdtl(UserIdentity domainUser, DealdtlDTOId id) {
		// TODO Auto-generated method stub

		DealsInfoResponse response = new DealsInfoResponse();
		DealdtlLiteDTO dealDTO = dealAPI.getDealdtl(domainUser, id);

		response = CustomUtils.mapToDealsInfoResponse(dealDTO);

		return response;
	}

	public CostDTO getCost(UserIdentity domainUser, int skuNumber, int siteNumber) {
		// TODO Auto-generated method stub
		return inventoryVendorDomainHelperAPI.getCost(domainUser, skuNumber, siteNumber);
	}

	public List<DuplicateItemInfoResponse> findDuplicateItems(UserIdentity domainUser, Boolean isUPC, String lookUpCd,
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
		List<InventorySearchResultsDTO> duplicates = inventoryVendorDomainHelperAPI.findItem(domainUser, searchPojo,
				siteNo, 0, 100);

		List<DuplicateItemInfoResponse> listOfDupItems = new ArrayList<>();
		listOfDupItems = CustomUtils.mapToDuplicateItemsResponse(duplicates);

		return listOfDupItems;
	}

	// cloned api services

	public List<InventorySearchInfoResponse> findInventoryItems(UserIdentity domainUser, Integer siteNo,
			ItemLookUpModel itemLookup) throws ServiceException {
		List<InventorySearchResultsDTO> invItems = null;
		List<InventorySearchInfoResponse> list = null;
		InventorySearchDTO invSearchDTO = buildInventorySearchDTO(itemLookup);
		invItems = inventoryVendorDomainHelperAPI.findItem(domainUser, invSearchDTO, siteNo, 0, 100);
		if (invItems == null || invItems.size() == 0) {
			throw new ServiceException(
					errors.getErrors().get(IErrorConstants.ITEM_NOT_FOUND).formatMessage(itemLookup.getSiteNo()));
		}
		if (!invItems.isEmpty())
			list = CustomUtils.mapToInventorySearchList(invItems);
		return list;
	}

	/**
	 * Get InventoryItemGlobalInfo
	 *
	 * @param domainUser
	 * @param skuNumber
	 * @param siteNumber
	 * @return InventoryGlobalInfoDTO
	 */
	public GlobalInfoResponse getInventoryItemGlobalInfo(UserIdentity domainUser, int skuNumber, int siteNumber) {
		GlobalInfoResponse response = new GlobalInfoResponse();
		InventoryGlobalInfoDTO globalDTO = inventoryAPI.getInventoryItemGlobalInfo(domainUser, skuNumber, siteNumber);

//		InventoryGlobal inventoryGlobal = mapToResponseUtil.getInventoryGlobalInfo(globalDTO);

		response = CustomUtils.mapToGlobalInfoResponse(globalDTO);
		return response;
	}

	/**
	 * Get KitInfoBySkuNo
	 *
	 * @param domainUser
	 * @param skuNumber
	 * @return InventoryGlobalInfoDTO
	 */
	public InventoryGlobalInfoDTO getKitInfoBySkuNo(UserIdentity domainUser, int skuNumber) {

//		InventoryGlobal inventoryGlobal = mapToResponseUtil.getInventoryGlobalInfo(inventoryAPI.getKitInfoBySkuNo(domainUser, skuNumber));
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

//		StatusClassHdr statusClassHdr = mapToResponseUtil.getStatusClassHdr(inventoryAPI.getCurrentItemRestrictDescription(domainUser, skuNumber, siteNumber));
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
//		StatusClassHdr statusClassHdr = mapToResponseUtil.getStatusClassHdr(inventoryAPI.getCurrentItemPosStatus(domainUser, skuNumber, siteNumber));
		return inventoryAPI.getCurrentItemPosStatus(domainUser, skuNumber, siteNumber);
	}

	/**
	 * Get all Item Type
	 *
	 * @param domainUser
	 * @return
	 */
	public List<ItemTypeCodeResponse> getItemType(UserIdentity domainUser) {
		List<ItemCreationDefaultsHdrDTO> sortedItemTypes = new ArrayList<>();
		List<ItemCreationDefaultsHdrDTO> dtos = inventoryAPI.getItemCreationItemTypes(domainUser);
		for (ItemCreationDefaultsHdrDTO dto : dtos) {
			if (dto != null) {
				sortedItemTypes.add(new ItemCreationDefaultsHdrDTO(dto));
			}
		}
		Collections.sort(sortedItemTypes);

		List<ItemTypeCodeResponse> listOfCodes = new ArrayList<>();
		listOfCodes = CustomUtils.mapToItemTypeCodeResponse(sortedItemTypes);

		return listOfCodes;
	}

	/**
	 * Get DeptGroups
	 *
	 * @param domainUser
	 * @return listDeptGrp
	 */
	public List<DepartmentGroupResponse> getDeptGroups(UserIdentity domainUser) {

		List<DepartmentGroupResponse> list = new ArrayList<>();
		List<DeptgrpDTO> listOfDept = inventoryAPI.getDeptGroups(domainUser);

		if (listOfDept != null)
			list = mapToResponseUtil.getDepartmentGroups(listOfDept);
		return list;
	}

	/**
	 * Get Classes
	 * 
	 * @param domainUser
	 * @param deptNo
	 * @return
	 */
	public List<ClassDetails> getClasses(UserIdentity domainUser, int deptNo) {
		List<ClassDetails> classDetails = new ArrayList<>();
		List<ClassDTO> listOfClassDTO = inventoryAPI.getClasses(domainUser, new DeptDTOId(deptNo));

		if (listOfClassDTO != null)
			classDetails = mapToResponseUtil.getClassDetailsList(listOfClassDTO);
		return classDetails;
	}

	/**
	 * Get Lines
	 * 
	 * @param domainUser
	 * @param deptNo
	 * @param classNo
	 * @return
	 */
	public List<Line> getLines(UserIdentity domainUser, int deptNo, int classNo) {

		List<Line> lines = new ArrayList<>();
		List<LineDTO> listOfDTO = inventoryAPI.getLines(domainUser, new ClassDTOId(deptNo, classNo));

		if (listOfDTO != null)
			lines = mapToResponseUtil.getLines(listOfDTO);

		return lines;

	}

	public List<DomainRefCode> getUMCodesInfo(UserIdentity domainUser, String packUmCodeValues) {

		List<DomainRefCodeDTO> listOfCodes = domainRefAPI.getDomainRefCodes(domainUser, packUmCodeValues);
		List<DomainRefCode> list = new ArrayList<>();

		if (listOfCodes != null && !listOfCodes.isEmpty())
			list = mapToResponseUtil.getDomainRefCodes(listOfCodes);

		return list;
	}

	// cloned api services end here

	public SiteInfoResponse getSite(UserIdentity domainUser, int siteNumber) {
		// TODO Auto-generated method stub
		SiteDTO siteDTO = siteAPI.getSite(domainUser, siteNumber);
		SiteInfoResponse response = new SiteInfoResponse();

		if (siteDTO.getIdentifier().getSiteNo() > 0)
			response = CustomUtils.mapToSiteInfoResponse(siteDTO);

		return response;
	}

	public List<SiteDTO> getSitesVisibility(UserIdentity domainUser, int siteNumber) {
		// TODO Auto-generated method stub
		return siteAPI.getSitesVisibility(domainUser, siteNumber);
	}

	public List<Department> getDepts(UserIdentity domainUser, int deptGrpNo) {

		DeptgrpDTOId id = new DeptgrpDTOId(deptGrpNo);
		List<DeptDTO> depts = inventoryAPI.getDepts(domainUser, id);
		List<Department> list = new ArrayList<>();

		if (!depts.isEmpty())
			list = mapToResponseUtil.getDepartments(inventoryAPI.getDepts(domainUser, id));

		return list;
	}

	public List<ItemAttributeValueResponse> getAttributeListValuesByAttrIdOwnerId(UserIdentity domainUser,
			Integer attributeId, Integer ownerId) {
		// TODO Auto-generated method stub

		List<AttributeListValueAllDTO> attrList = attributeAPI.getAttributeListValuesByAttrIdOwnerId(domainUser,
				attributeId, ownerId);
		List<ItemAttributeValueResponse> itemAttributeValues = new ArrayList<>();

		if (attrList != null && !attrList.isEmpty()) {
			itemAttributeValues = mapToResponseUtil.getItemAttributeValues(attrList);
		}
		return itemAttributeValues;
	}

	public VendorDetailsInfoResponse getVendor(UserIdentity domainUser, String vendorId) {
		// TODO Auto-generated method stub
		VendorDTO vendorDto = vendorAPI.getVendor(domainUser, vendorId);
		VendorDetailsInfoResponse response = new VendorDetailsInfoResponse();

		if (vendorDto.getVendorId() != null)
			response = CustomUtils.mapToVendorDetails(vendorDto);

		return response;
	}

	public List<TenderCertificateInfoResponse> getTenderCertificatePrograms(UserIdentity domainUser) {
		// TODO Auto-generated method stub
		List<TenderCertificateProgramDTO> list = inventoryAPI.getTenderCertificatePrograms(domainUser);
		List<TenderCertificateInfoResponse> certList = new ArrayList<>();

		if (!list.isEmpty())
			certList = CustomUtils.mapToCertificateResponse(list);

		return certList;
	}

	public List<GLCategoryInfoResponse> getGLCategories(UserIdentity domainUser) {
		// TODO Auto-generated method stub

		// later can be externalized through props file
		Integer firstResult = null;
		Integer pageSize = null;
		List<GlcatDTO> list = accountsReceivableAPI.getGLCategories(domainUser, firstResult, pageSize);
		List<GLCategoryInfoResponse> listResponse = new ArrayList<>();

		if (!list.isEmpty())
			listResponse = CustomUtils.mapToGLCatResponse(list);

		return listResponse;
	}

	public List<String> getCountryCodes(UserIdentity domainUser) {
		// TODO Auto-generated method stub
		return localeAPI.getCountryCodes(domainUser);
	}

	public List<StatesProvinceInfoResponse> getGeographicRegionsForCountry(UserIdentity domainUser, String countryCd) {
		// TODO Auto-generated method stub
		List<GeographicRegionDTO> states = localeAPI.getGeographicRegionsForCountry(domainUser, countryCd);
		List<StatesProvinceInfoResponse> response = new ArrayList<>();

		if (!states.isEmpty())
			response = CustomUtils.mapToStates(states);

		return response;
	}

	public List<AttributeListValuesInfoResponse> getAttributeListValuesById(UserIdentity domainUser, int attributeId) {
		// TODO Auto-generated method stub

		List<AttributeListValueAllDTO> attrList = attributeAPI.getAttributeListValuesById(domainUser, attributeId);
		List<AttributeListValuesInfoResponse> list = new ArrayList<>();

		if (!attrList.isEmpty()) {
			list = CustomUtils.mapToAttributeListValues(attrList);
		}

		return list;
	}

	public ItemCreationDefaultsInfoDTO getItemCreateDefaults(UserIdentity domainUser, long itemCreationId) {
		// TODO Auto-generated method stub

		ItemCreationDefaultsInfoDTO defaultValue = inventoryAPI.getItemCreationDefaults(domainUser, itemCreationId);
		return defaultValue;
	}

	public ConfigrecDTO getConfigrecByName(UserIdentity domainUser, String configName) {
		// TODO Auto-generated method stub
		return configAPI.getConfigrecByName(domainUser, configName);
	}

	public VendorImportInfoResponse getVendorImport(UserIdentity domainUser, int skuNumber, int siteNumber) {
		// TODO Auto-generated method stub
		return inventoryRepository.getVendorImport(domainUser, skuNumber);
	}

	public List<ItemNote> getItemNoteBySku(UserIdentity domainUser, int skuNumber) {

		List<ItemNoteResultWithDomainRefDTO> notesList = inventoryAPI.getItemNoteBySku(domainUser, skuNumber);
		List<ItemNote> itemNotes = new ArrayList<>();
		List<ItemNote> itemNotesRequired = new ArrayList<>();

		if (notesList != null && !notesList.isEmpty()) {
			itemNotes = mapToResponseUtil.getItemNotes(notesList);
			for (ItemNote itemNote : itemNotes) {
//				itemNote.setOwnerId(inventoryRepository.getOwnerId(itemNote.getSiteNo()));
				itemNotesRequired.add(itemNote);
			}
		}
		return itemNotes;
	}

	public ItemNote createItemNoteBySku(UserIdentity domainUser, ItemNotesModel itemNotesModel) {
		// TODO Auto-generated method stub
		ItemNoteDTO itemNoteDTO = buildItemNoteDTO(itemNotesModel);// new ItemNoteDTO();
		itemNoteDTO.setUserId(domainUser.getUserName());
		// don't confuse owner and owner id are different
		itemNoteDTO.setOwner(domainUser.getUserName());
		itemNoteDTO.setSiteNo(inventoryUtil.getCurrentSite());
		ItemNoteDTO noteDto = inventoryAPI.createItemNoteBySku(domainUser, itemNoteDTO);
		return getItemNoteWithDomainCode(noteDto);
	}

	private ItemNoteDTO buildItemNoteDTO(ItemNotesModel itemNotesModel) {
		// TODO Auto-generated method stub
		ItemNoteDTO itemNoteDTO = new ItemNoteDTO();

//		if (itemNotesModel.getSiteNo() != null && itemNotesModel.getSiteNo() > 0)
//			itemNoteDTO.setSiteNo(itemNotesModel.getSiteNo());

		if (itemNotesModel.getSkuNo() != null && itemNotesModel.getSkuNo() > 0)
			itemNoteDTO.setSkuNo(itemNotesModel.getSkuNo());

//		if (itemNotesModel.getOwnerId() != null && itemNotesModel.getOwnerId() > 0) {
//			itemNoteDTO.setOwner(itemNotesModel.getOwnerId().toString().trim());
//			System.out.println("Owner id == " + itemNotesModel.getOwnerId().toString().trim());
//		}

		if (StringUtils.isNotBlank(itemNotesModel.getGroupId()))
			itemNoteDTO.setGroupId(itemNotesModel.getGroupId());
		else
			itemNoteDTO.setGroupId("ALL");

		if (StringUtils.isNotBlank(itemNotesModel.getTypeCd()))
			itemNoteDTO.setTypeCd(itemNotesModel.getTypeCd());

		if (StringUtils.isNotBlank(itemNotesModel.getPosHandling()))
			itemNoteDTO.setPosHandling(itemNotesModel.getPosHandling());

		if (StringUtils.isNotBlank(itemNotesModel.getNoteText()))
			itemNoteDTO.setNoteText(itemNotesModel.getNoteText());

		if (StringUtils.isNotBlank(itemNotesModel.getBackOfficeHandling()))
			itemNoteDTO.setBackOfficeHandling(itemNotesModel.getBackOfficeHandling());

		return itemNoteDTO;
	}

	// need to fetch the domain codes on the basis of the type and code
	private ItemNote getItemNoteWithDomainCode(ItemNoteDTO noteDto) {
		ItemNote itemNote = new ItemNote();
		if (noteDto != null) {
			itemNote = mapToResponseUtil.getItemNoteResponse(noteDto);

			if (StringUtils.isNotBlank(itemNote.getBackOfficeHandlingCodeValue()))
				itemNote.setBackOfficeHandlingCodeMeaning(inventoryRepository.getDomainCodeMeaning(
						itemNote.getBackOfficeHandlingCodeValue(), "ITEM NOTE BACK OFFICE HANDLING VALUES"));

			if (StringUtils.isNotBlank(itemNote.getPosHandlingCodeValue()))
				itemNote.setPosHandlingCodeMeaning(inventoryRepository
						.getDomainCodeMeaning(itemNote.getPosHandlingCodeValue(), "ITEM NOTE POS HANDLING VALUES"));

			if (StringUtils.isNotBlank(itemNote.getTypeCodeValue()))
				itemNote.setTypeCodeMeaning(inventoryRepository.getDomainCodeMeaning(itemNote.getTypeCodeValue(),
						"ITEM NOTE TYPE CODE VALUES"));
		}
		return itemNote;
	}

	// custom validation
	public ItemNoteDTO createItemNoteBySkuTests(UserIdentity domainUser, ItemNotesRequest itemNotesModel) {
		// TODO Auto-generated method stub

		// 1. create validator and call validate
		// 2. Strategy and interface in core lib
		// 3. Implement the custom validator and call with the request wrapper
		validator.validate(itemNotesModel);
//		valo.validate(itemNotesModel);
		ItemNoteDTO itemNoteDTO = new ItemNoteDTO();

		if (StringUtils.isNotBlank(domainUser.getUserName()))
			itemNoteDTO.setUserId(domainUser.getUserName());

		if (itemNotesModel.getSiteNo() != null && itemNotesModel.getSiteNo() > 0)
			itemNoteDTO.setSiteNo(itemNotesModel.getSiteNo());

		if (itemNotesModel.getSkuNo() != null && itemNotesModel.getSkuNo() > 0)
			itemNoteDTO.setSkuNo(itemNotesModel.getSkuNo());

		if (itemNotesModel.getOwnerId() != null && itemNotesModel.getOwnerId() > 0) {
			itemNoteDTO.setOwner(itemNotesModel.getOwnerId().toString().trim());
			System.out.println("Owner id == " + itemNotesModel.getOwnerId().toString().trim());
		}

		if (StringUtils.isNotBlank(itemNotesModel.getGroupId()))
			itemNoteDTO.setGroupId(itemNotesModel.getGroupId());

		if (StringUtils.isNotBlank(itemNotesModel.getTypeCd()))
			itemNoteDTO.setTypeCd(itemNotesModel.getTypeCd());

		if (StringUtils.isNotBlank(itemNotesModel.getPosHandling()))
			itemNoteDTO.setPosHandling(itemNotesModel.getPosHandling());

		if (StringUtils.isNotBlank(itemNotesModel.getNoteText()))
			itemNoteDTO.setNoteText(itemNotesModel.getNoteText());

		if (StringUtils.isNotBlank(itemNotesModel.getBackOfficeHandling()))
			itemNoteDTO.setBackOfficeHandling(itemNotesModel.getBackOfficeHandling());

		return inventoryAPI.createItemNoteBySku(domainUser, itemNoteDTO);
	}

	public SuccessResponse deleteItemNoteBySku(UserIdentity domainUser, int noteId, ItemNotesModel itemNotesModel) {
		// TODO Auto-generated method stub

		ItemNoteDTO itemNoteDTO = buildItemNoteDTO(itemNotesModel);// new ItemNoteDTO();
		SuccessResponse response = new SuccessResponse();

		if (noteId > 0)
			itemNoteDTO.setNoteId(noteId);

		if (StringUtils.isNotBlank(domainUser.getUserName()))
			itemNoteDTO.setUserId(domainUser.getUserName());
		itemNoteDTO.setOwner(domainUser.getUserName());
		itemNoteDTO.setSiteNo(inventoryUtil.getCurrentSite());
		// errorLovanya
		inventoryAPI.deleteItemNoteBySku(domainUser, itemNoteDTO);
		response.setMessage("Successfully deleted item note with id == " + noteId);

		return response;
	}

	public ItemNote updateItemNoteBySku(UserIdentity domainUser, int noteId, ItemNotesModel itemNotesModel) {
		// TODO Auto-generated method stub
		ItemNoteDTO itemNoteDTO = buildItemNoteDTO(itemNotesModel);// new ItemNoteDTO();

		if (noteId > 0)
			itemNoteDTO.setNoteId(noteId);

			itemNoteDTO.setUserId(domainUser.getUserName());
			itemNoteDTO.setOwner(domainUser.getUserName());
			itemNoteDTO.setSiteNo(inventoryUtil.getCurrentSite());

		inventoryAPI.updateItemNoteBySku(domainUser, itemNoteDTO);

		List<ItemNoteDTO> list = inventoryAPI.getItemNotes(domainUser, itemNotesModel.getSkuNo(),
				inventoryUtil.getCurrentSite());

		if (list != null) {
			if (!list.isEmpty()) {
				for (ItemNoteDTO itemDto : list) {
					if (noteId == itemDto.getNoteId()) {
						itemNoteDTO = new ItemNoteDTO(itemDto);
					}
				}
			}
		}
		return getItemNoteWithDomainCode(itemNoteDTO);
	}

	public List<ItemAttributeResponse> getAttributeNameListByFunctionalUsageAndActiveFl(UserIdentity domainUser) {
		AttributeSearchDTO attributeSearchDTO = new AttributeSearchDTO();
		attributeSearchDTO.setFunctionalAreaCd(IRnetDomain.FUNC_AREA_CODE);
		attributeSearchDTO.setActiveFlag(true);

		List<AttributeNameDTO> attributesList = attributeAPI
				.getAttributeNameListByFunctionalUsageAndActiveFl(domainUser, attributeSearchDTO);
		List<ItemAttributeResponse> attributes = new ArrayList<>();
		if (attributesList != null && !attributesList.isEmpty()) {
			attributes = mapToResponseUtil.getItemAttributeList(attributesList);
			attributes = setFunctionalAreaCode(attributes, IRnetDomain.FUNC_AREA_CODE);
		}
		return attributes;
	}

	private List<ItemAttributeResponse> setFunctionalAreaCode(List<ItemAttributeResponse> attributes,
			String funcAreaCode) {
		List<ItemAttributeResponse> listOfAttributes = new ArrayList<>();
		if (attributes != null && !attributes.isEmpty()) {
			for (ItemAttributeResponse itemAttribute : attributes) {
				itemAttribute.setFunctionalAreaCd(funcAreaCode);
				listOfAttributes.add(itemAttribute);
			}
		}
		return listOfAttributes;
	}

	public List<ItemAttributeResponse> getItemAttributesBySku(UserIdentity domainUser, int skuNumber) {
		// TODO Auto-generated method stub
		InventoryGeneralSearchDTO inventoryGeneralSearchDTO = new InventoryGeneralSearchDTO();
		if (skuNumber > 0)
			inventoryGeneralSearchDTO.setSkuNum(skuNumber);
		List<ItemAttributeResultDTO> resultDtos = inventoryAPI.getItemAttributeBySku(domainUser,
				inventoryGeneralSearchDTO);
		List<ItemAttributeResponse> reqList = new ArrayList<>();
		if (resultDtos != null && !resultDtos.isEmpty()) {
			reqList = mapToResponseUtil.getItemAttributes(resultDtos);
			reqList = setFunctionalAreaCode(reqList, IRnetDomain.FUNC_AREA_CODE);
		}
		return reqList;
	}

	public AttributeNameResponse createItemAttributeBySku(UserIdentity domainUser, AttributeModel attributeModel) {
		// TODO Auto-generated method stub
		ItemAttributeValueDTO itemAttributeValueDTO = buildItemAttributeValueDTO(attributeModel);// new
																									// ItemAttributeValueDTO();
		AttributeNameDTO attrDto = new AttributeNameDTO();

		if (attributeModel.getAttributeId() != null && attributeModel.getAttributeId() > 0)
			attrDto = attributeAPI.getAttributeById(domainUser, attributeModel.getAttributeId());

		if (attrDto != null)
			itemAttributeValueDTO = inventoryAPI.createItemAttributeBySku(domainUser, itemAttributeValueDTO);
		else
			throw new ServiceException(
					errors.getErrors().get("ATTRIBUTE_NOT_FOUND").formatMessage(attributeModel.getAttributeId()));

		AttributeNameResponse itemAttriute = new AttributeNameResponse();
		if (itemAttributeValueDTO != null)
			itemAttriute = mapToResponseUtil.getItemAttribute(itemAttributeValueDTO);
		return itemAttriute;
	}

	public UpdatedAttributeResponse updateItemAttributeBySku(UserIdentity domainUser, AttributeModel attributeModel) {
		// TODO Auto-generated method stub

		ItemAttributeValueDTO itemAttributeValueDTO = buildItemAttributeValueDTO(attributeModel);// new
																									// ItemAttributeValueDTO();
		AttributeNameDTO attrDto = new AttributeNameDTO();

		if (attributeModel.getAttributeId() != null && attributeModel.getAttributeId() > 0)
			attrDto = attributeAPI.getAttributeById(domainUser, attributeModel.getAttributeId());

		if (attrDto != null)
			inventoryAPI.updateItemAttributeBySku(domainUser, itemAttributeValueDTO);
		else
			throw new ServiceException(
					errors.getErrors().get("ATTRIBUTE_NOT_FOUND").formatMessage(attributeModel.getAttributeId()));

		UpdatedAttributeResponse response = mapToResponseUtil.getAttributeName(attrDto);
		return response;
	}

	public SuccessResponse deleteItemAttributeBySku(UserIdentity domainUser, AttributeModel attributeModel) {
		// TODO Auto-generated method stub

		ItemAttributeValueDTO itemAttributeValueDTO = buildItemAttributeValueDTO(attributeModel);// new
																									// ItemAttributeValueDTO();
		AttributeNameDTO attrDto = new AttributeNameDTO();
		SuccessResponse response = new SuccessResponse();

		if (attributeModel.getAttributeId() != null && attributeModel.getAttributeId() > 0)
			attrDto = attributeAPI.getAttributeById(domainUser, attributeModel.getAttributeId());

		if (attrDto != null) {
			inventoryAPI.deleteItemAttributeBySku(domainUser, itemAttributeValueDTO);
			response.setMessage("Successfully deleted " + attrDto.getNameText());
		} else
			throw new ServiceException(
					errors.getErrors().get("ATTRIBUTE_NOT_FOUND").formatMessage(attributeModel.getAttributeId()));

		return response;
	}

	private ItemAttributeValueDTO buildItemAttributeValueDTO(AttributeModel attributeModel) {
		// TODO Auto-generated method stub
		ItemAttributeValueDTO itemAttributeValueDTO = new ItemAttributeValueDTO();

		if (attributeModel.getAttributeId() != null && attributeModel.getAttributeId() > 0)
			itemAttributeValueDTO.setAttributeId(attributeModel.getAttributeId());

		if (attributeModel.isLovFl()) {

			if (StringUtils.isNotBlank(attributeModel.getAttributeValue()))
				itemAttributeValueDTO.setAttributeValue(attributeModel.getAttributeValue());
		} else {
			if (StringUtils.isNotBlank(attributeModel.getAttributeValueText()))
				itemAttributeValueDTO.setAttributeValue(attributeModel.getAttributeValueText());
		}

		if (StringUtils.isNotBlank(attributeModel.getFunctionalAreaCd()))
			itemAttributeValueDTO.setFunctionalAreaCd(attributeModel.getFunctionalAreaCd());

		if (attributeModel.getSkuNo() != null && attributeModel.getSkuNo() > 0)
			itemAttributeValueDTO.setSkuNo(attributeModel.getSkuNo());

		return itemAttributeValueDTO;
	}

	public List<GroupsAllDTO> getAllSiteGroups(UserIdentity domainUser) {
		// TODO Auto-generated method stub
		return siteAPI.getAllGroups(domainUser);
	}

	public ItemUpcResponse createUPCForSku(UserIdentity domainUser, ItemUpcModel upcModel, int skuNo) {
		// TODO Auto-generated method stub
		List<ConfigrecDTO> configrecDTOList = new ArrayList<ConfigrecDTO>();
		configrecDTOList.add(configAPI.getConfigrecByName(domainUser, IRnetDomain.UPC));
		configrecDTOList.add(configAPI.getConfigrecByName(domainUser, IRnetDomain.UPC_NUMERIC));
		configrecDTOList.add(configAPI.getConfigrecByName(domainUser, IRnetDomain.PLU));

		ItemupcDTO itemUpcDto = buildItemupcDTO(upcModel); // new ItemupcDTO();
		itemUpcDto.setSkuNo(skuNo);

		ItemupcDTO itemUPC = inventoryAPI.createUPCForSku(domainUser, itemUpcDto, configrecDTOList);
		ItemUpcResponse itemUpcResponse = mapToResponseUtil.getItemUpc(itemUPC);
		return itemUpcResponse;
	}

	public ItemUpcResponse updateUPCForSku(UserIdentity domainUser, ItemUpcModel upcModel, int skuNo) {
		// TODO Auto-generated method stub
		ItemupcDTO itemUpcDto = buildItemupcDTO(upcModel); // new ItemupcDTO();
		itemUpcDto.setSkuNo(skuNo);
		ItemupcDTO itemUPC = inventoryAPI.updateUPCForSku(domainUser, itemUpcDto);
		ItemUpcResponse itemUpcResponse = mapToResponseUtil.getItemUpc(itemUPC);
		return itemUpcResponse;
	}

	public String deleteUPCForSku(UserIdentity domainUser, int skuNo, String upcId, int modifier) {
		// TODO Auto-generated method stub
		ItemupcDTO itemUpcDto = new ItemupcDTO();

		if (StringUtils.isNotBlank(upcId))
			itemUpcDto.setUpcId(upcId);

		if (skuNo > 0)
			itemUpcDto.setSkuNo(skuNo);

		itemUpcDto.setUpcModifier(modifier);
		itemUpcDto.setUpcSeq(2);

		inventoryAPI.deleteUPCForSku(domainUser, itemUpcDto);
		return "SUCCESS";
	}

	private ItemupcDTO buildItemupcDTO(ItemUpcModel upcModel) {
		ItemupcDTO itemUpcDto = new ItemupcDTO();

		if (upcModel.getIsPrimaryUPC())
			itemUpcDto.setUpcSeq(new Integer(1));
		else
			itemUpcDto.setUpcSeq(new Integer(2));

		if (upcModel.getAdjAmtUPC() != null && upcModel.getAdjAmtUPC().doubleValue() >= 0)
			itemUpcDto.setPriceAdjAmt(upcModel.getAdjAmtUPC());

		if (StringUtils.isNotBlank(upcModel.getUpcId()))
			itemUpcDto.setUpcId(upcModel.getUpcId());

		if (upcModel.getModifierUPC() != null && upcModel.getModifierUPC() > 1 && upcModel.getModifierUPC() < 100)
			itemUpcDto.setUpcModifier(upcModel.getModifierUPC());
		else
			itemUpcDto.setUpcModifier(1);

		if (StringUtils.isNotBlank(upcModel.getTypeCd()))
			itemUpcDto.setTypeCd(upcModel.getTypeCd());

//		if("C".equalsIgnoreCase(upcModel.getTypeCd())) {
//			
//			if (StringUtils.isNotBlank(upcModel.getUcc14UmUPS()))
//				itemUpcDto.setUcc14Uom(upcModel.getUcc14UmUPS());
//				
//			if(upcModel.getUcc14QtyUPS() != null && upcModel.getUcc14QtyUPS().doubleValue() > 0)
//				itemUpcDto.setUcc14Qty(upcModel.getUcc14QtyUPS());
//		}
		return itemUpcDto;
	}

	public Inventory updateInvtory(UserIdentity domainUser,
			com.rediron.platform.domain.model.request.InventoryModel inventoryModel, int skuNo, int siteNo) {
		// TODO Auto-generated method stub
		InvtoryDTO invDTO = inventoryUtil.validateAndMapUpdateInvModel(domainUser, inventoryModel, skuNo, siteNo);
		if (invDTO == null)
			throw new ServiceException(errors.getErrors().get(IErrorConstants.NULL_OBJECT).formatMessage("INVTORY"));

		Inventory inventory = mapToResponseUtil.getInventory(inventoryAPI.updateInvtory(domainUser, invDTO));
		return inventory;
	}

	public InvbysitAndPromoDetails getInvbysit(UserIdentity domainUser, int skuNumber, int siteNumber,
			String pricingType) {
		// TODO Auto-generated method stub
		InvbysitDTO invbysitDTO = inventoryAPI.getInvbysit(domainUser, skuNumber, siteNumber);
		Invbysit invbysit = null;
		InvbysitAndPromoDetails details = new InvbysitAndPromoDetails();
		BigDecimal promoKeyNo = null;
		if ("Current".equalsIgnoreCase(pricingType)) {
			promoKeyNo = inventoryUtil.getPromoKeyNo(domainUser, skuNumber, siteNumber);

			if (promoKeyNo != null && promoKeyNo.doubleValue() > 0) {
				invbysitDTO = inventoryUtil.getPromoPricing(domainUser, promoKeyNo, invbysitDTO, siteNumber);

				if (invbysitDTO != null)
					invbysit = mapToResponseUtil.getInvBySit(invbysitDTO);
				details.setInvbysit(invbysit);
			}
		} else {
			promoKeyNo = inventoryUtil.getPromoKeyNo(domainUser, skuNumber, siteNumber);
			PromoInfo promoInfo = null;
			if (promoKeyNo != null && promoKeyNo.doubleValue() > 0)
				promoInfo = inventoryUtil.getPromoInfo(domainUser, promoKeyNo);

			if (promoInfo != null) {
				details.setPromoInfo(promoInfo);
			}
			if (promoKeyNo != null && promoKeyNo.doubleValue() > 0) {
				invbysitDTO = inventoryUtil.getRegularPricing(domainUser, promoKeyNo, invbysitDTO, siteNumber,
						skuNumber);
				if (invbysitDTO != null)
					invbysit = mapToResponseUtil.getInvBySit(invbysitDTO);
				details.setInvbysit(invbysit);
			}
		}
		return details;
	}

	// should be in their respective modules but inv also uses hence find a way to
	// reuse from independent micro-service
	public List<InventoryBySiteAndVendorItemListsDTO> authoriseItemAtShipSite(UserIdentity domainUser,
			Integer shipSiteNo, Integer skuNo, String vendorId) {
		if (StringUtils.isNotBlank(vendorId))
			return inventoryVendorDomainHelperAPI.authorizeItemForVendorAtHQ(domainUser, shipSiteNo, skuNo, vendorId);
		else
			return inventoryVendorDomainHelperAPI.authorizeItemAtHQ(domainUser, shipSiteNo, skuNo);
	}

	public Boolean isItemAlreadyAuthorized(UserIdentity domainUser, int shipSiteNo, int skuNo) {
		// TODO Auto-generated method stub
		return inventoryVendorDomainHelperAPI.isItemAlreadyAuthorized(domainUser, shipSiteNo, skuNo);
	}

	public ItemCodeResponse getItemCodes(UserIdentity domainUser, int skuNo) {
		return inventoryRepository.getItemCodes(domainUser, skuNo);
	}

	public int saveOrUpdateItemCodes(UserIdentity domainUser, ItemCodesModel itemCodes, int skuNo) {
		String code1Id = null;
		String code2Id = null;
		String code3Id = null;

		List<CodeDetailsResponse> gallonCodes = inventoryUtil.getCodeDetails(UserDefinedTypeCode.GALLON);
		List<CodeDetailsResponse> stockCodes = inventoryUtil.getCodeDetails(UserDefinedTypeCode.STOCK);
		List<CodeDetailsResponse> shelfCodes = inventoryUtil.getCodeDetails(UserDefinedTypeCode.SHELF);

		if (StringUtils.isNotBlank(itemCodes.getCode1Id())) {
			if (gallonCodes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
					.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(itemCodes.getCode1Id())))
				code1Id = itemCodes.getCode1Id();
			else
				throw new ServiceException(errors.getErrors().get("ITEM_CODE_NOT_FOUND"));
		}

		if (StringUtils.isNotBlank(itemCodes.getCode2Id())) {
			if (stockCodes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
					.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(itemCodes.getCode2Id())))
				code2Id = itemCodes.getCode2Id();
			else
				throw new ServiceException(errors.getErrors().get("ITEM_CODE_NOT_FOUND"));
		}

		if (StringUtils.isNotBlank(itemCodes.getCode3Id())) {
			if (shelfCodes.stream().filter(obj -> StringUtils.isNotBlank(obj.getCodeValue()))
					.anyMatch(o -> o.getCodeValue().equalsIgnoreCase(itemCodes.getCode3Id())))
				code3Id = itemCodes.getCode3Id();
			else
				throw new ServiceException(errors.getErrors().get("ITEM_CODE_NOT_FOUND"));
		}
		return inventoryUtil.saveOrUpdateItemCodes(domainUser, code1Id, code2Id, code3Id, skuNo);
	}

	public ReservedQtyFullDTO getReservedQtyDetails(UserIdentity domainUser, int siteNo, int skuNo,
			String siteGroupId) {
		// TODO Auto-generated method stub
		InventoryGeneralSearchDTO invGeneralSearchDTO = new InventoryGeneralSearchDTO();
		invGeneralSearchDTO.setSite(siteNo);
		invGeneralSearchDTO.setSkuNum(skuNo);
		invGeneralSearchDTO.setSiteGroup(siteGroupId);
		return inventoryAPI.getReservedQtyFullBySiteGroupAndSku(domainUser, invGeneralSearchDTO);
	}

	public List<ItemMovementDetailDTO> getMovementDetails(UserIdentity domainUser, int siteNo, int skuNo) {
		// TODO Auto-generated method stub
		return inventoryAPI.getMovementDetails(domainUser, skuNo, siteNo);
	}

	public ExpectedQtyFullDTO getExpectedQtyFullBySiteGroupAndSku(UserIdentity domainUser, int skuNo,
			String siteGroupId) {
		// TODO Auto-generated method stub
		InventoryGeneralSearchDTO invGeneralSearchDTO = new InventoryGeneralSearchDTO();
		invGeneralSearchDTO.setSkuNum(skuNo);
		invGeneralSearchDTO.setSiteGroup(siteGroupId);
		return inventoryAPI.getExpectedQtyFullBySiteGroupAndSku(domainUser, invGeneralSearchDTO);
	}

	public List<SummaryInfoResponse> getSummaryInfo(UserIdentity domainUser, int skuNo, String siteGroupId) {
		// TODO Auto-generated method stub
		return inventoryRepository.getSummaryInfo(domainUser, skuNo, siteGroupId);
	}

	// List<ErrorDTO> updatedReorderPoints =
	// inventoryAPI.updateReorderPoints(domainUser, reorderPointsList);
	public List<SummaryInfoResponse> updateSummaryInfo(UserIdentity domainUser, int skuNo, String siteGroupId,
			SummaryInfoRequest summaryInfoRequest) {
		return inventoryRepository.updateSummaryInfo(domainUser, skuNo, siteGroupId, summaryInfoRequest);
	}

	public List<Invbysit> updateChangeSiteInfo(UserIdentity domainUser, int skuNo, String siteGroupId,
			ChangeSiteInfoRequest changeSiteInfoRequest) {
		List<Integer> siteNos = inventoryUtil.getSitesFromSiteGroupId(siteGroupId);
		UpdateInvbysitRequest updateInvbysitRequest = inventoryUtil.getUpdateInvbysitRequest(changeSiteInfoRequest);
		List<Invbysit> listOfInvbysit = new ArrayList<>();
		Invbysit invbysit = null;
		for (Integer siteNo : siteNos) {
			invbysit = updateInvbysit(domainUser, skuNo, siteNo, updateInvbysitRequest);
			listOfInvbysit.add(invbysit);
		}
		return listOfInvbysit;
	}

	public Invbysit updateInvbysit(UserIdentity domainUser, int skuNo, int siteNo,
			UpdateInvbysitRequest updateInvbysitRequest) {
		int count = 0;
		updateInvbysitRequest = inventoryUtil.validateAndMapUpdateInvbysit(domainUser, skuNo, siteNo,
				updateInvbysitRequest);

		String flexibleFl = inventoryRepository.getFlexibleFlag(skuNo);
		StatusInfoModel statuses = inventoryUtil.validateStatus(updateInvbysitRequest.getStatusInfoModel(), flexibleFl);
		updateInvbysitRequest.setStatusInfoModel(statuses);
		count = inventoryRepository.updateInvbysit(domainUser, skuNo, siteNo, updateInvbysitRequest);
		logger.info("Updated records : " + count);

		return mapToResponseUtil.getInvBySit(inventoryAPI.getInvbysit(domainUser, skuNo, siteNo));
	}

	// changes end here
}
