package com.rediron.platform.domain.api;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.model.request.AttributeModel;
import com.rediron.platform.domain.model.request.ChangeSiteInfoRequest;
import com.rediron.platform.domain.model.request.InvBySiteModel;
import com.rediron.platform.domain.model.request.ItemCreationDefaultsInfoRequest;
import com.rediron.platform.domain.model.request.ItemNotesModel;
import com.rediron.platform.domain.model.request.ItemUpcModel;
import com.rediron.platform.domain.model.request.SummaryInfoRequest;
import com.rediron.platform.domain.model.request.UpdateInvbysitRequest;
import com.rediron.platform.domain.model.response.AttributeNameResponse;
import com.rediron.platform.domain.model.response.ClassDetails;
import com.rediron.platform.domain.model.response.Department;
import com.rediron.platform.domain.model.response.DepartmentDetail;
import com.rediron.platform.domain.model.response.DepartmentGroupResponse;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.model.response.GLCategoryInfoResponse;
import com.rediron.platform.domain.model.response.Invbysit;
import com.rediron.platform.domain.model.response.InvbysitAndPromoDetails;
import com.rediron.platform.domain.model.response.Inventory;
import com.rediron.platform.domain.model.response.ItemAttributeResponse;
import com.rediron.platform.domain.model.response.ItemAttributeValueResponse;
import com.rediron.platform.domain.model.response.ItemCodeResponse;
import com.rediron.platform.domain.model.response.ItemCreationDefaultModel;
import com.rediron.platform.domain.model.response.ItemCreationDefaultsHdr;
import com.rediron.platform.domain.model.response.ItemNote;
import com.rediron.platform.domain.model.response.ItemUpcResponse;
import com.rediron.platform.domain.model.response.Line;
import com.rediron.platform.domain.model.response.SkuInfoResponse;
import com.rediron.platform.domain.model.response.SkuNumberResponse;
import com.rediron.platform.domain.model.response.StatusClassHdr;
import com.rediron.platform.domain.model.response.SummaryInfoResponse;
import com.rediron.platform.domain.model.response.TenderCertificateInfoResponse;
import com.rediron.platform.domain.model.response.UpdatedAttributeResponse;
import com.rediron.platform.domain.model.response.VendorImportInfoResponse;
import com.rediron.platform.domain.models.ItemCodesModel;
import com.rediron.platform.domain.models.ItemLookUpModel;
import com.rediron.platform.domain.models.ItemNotesRequest;
import com.rediron.platform.domain.response.AttributeListValuesInfoResponse;
import com.rediron.platform.domain.response.DealsInfoResponse;
import com.rediron.platform.domain.response.DuplicateItemInfoResponse;
import com.rediron.platform.domain.response.GlobalInfoResponse;
import com.rediron.platform.domain.response.ItemTypeCodeResponse;
import com.rediron.platform.domain.response.SiteGroupInfoResponse;
import com.rediron.platform.domain.response.SiteInfoResponse;
import com.rediron.platform.domain.response.StatesProvinceInfoResponse;
import com.rediron.platform.domain.response.SuccessResponse;
import com.rediron.platform.domain.response.VendorDetailsInfoResponse;
import com.rediron.platform.domain.services.InventoryService;
import com.rediron.platform.domain.util.AuthUtil;
import com.rediron.security.common.ISecurity;
import com.rediron.security.common.exceptions.ExternalSecurityException;
import com.rediron.security.common.exceptions.InvalidToken;
import com.tomax.api.UserIdentity;
import com.tomax.config.api.ItemIdentifierType;
import com.tomax.config.dto.ConfigrecDTO;
import com.tomax.deal.dto.DealdtlDTOId;
import com.tomax.invVendorHelper.custom.dto.InventoryBySiteAndVendorItemListsDTO;
import com.tomax.invVendorHelper.custom.dto.ItemPrimaryDataDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTO;
import com.tomax.inventory.dto.ItemNoteDTO;
import com.tomax.inventory.dto.StatusClassHdrDTO;
import com.tomax.inventory.dto.custom.CostDTO;
import com.tomax.inventory.dto.custom.ExpectedQtyFullDTO;
import com.tomax.inventory.dto.custom.InventoryGlobalInfoDTO;
import com.tomax.inventory.dto.custom.InventorySearchResultsDTO;
import com.tomax.inventory.dto.custom.ItemCreationDefaultsInfoDTO;
import com.tomax.inventory.dto.custom.ItemMovementDetailDTO;
import com.tomax.inventory.dto.custom.ReservedQtyFullDTO;
import com.tomax.site.dto.GroupsAllDTO;
import com.tomax.site.dto.SiteDTO;
import com.tomax.util.domain.ErrorDTO;
import com.tomax.vendor.dto.VendorDTO;
import com.tomax.vendor.dto.VendorItemDTO;
import com.tomax.vendor.dto.custom.VendorItemInfoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Created by Jai.Ruwari on 20-07-2020.
 */

@Api(basePath = IRnetDomain.INVENTORY_API, value = "RetailNet Back office Inventory API ", description = "RetailNet Back office Inventory Api")
@RestController
@RequestMapping(IRnetDomain.INVENTORY_API)

public class InventoryAPIController {
	Logger logger = LoggerFactory.getLogger(InventoryAPIController.class);

	// TODO - Code refactoring required
	
	@Autowired
	private AuthUtil authUtil;

	@Autowired
	private InventoryService invService;

	@Autowired
	private Errors errors;
	
	// req res changes start here
	
	// Create New Item
	@PostMapping(IRnetDomain.CREATE_INVENTORY_ITEM)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Item", notes = "Returns the created item SKU number.", response = SkuNumberResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 404, message = IRnetDomain.ITEM_TYPE_CD_NOT_FOUND),
			@ApiResponse(code = 500, message = IRnetDomain.ITEM_NOT_CREATED),
			@ApiResponse(code = 500, message = IRnetDomain.INVENTORY_EXCEPTION)})
	public SkuNumberResponse createInvItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Create Item Default Type Code", required = true) @RequestParam(name = "item_type_code") String itemCreateDefaultTypeCode,
			@ApiParam(value = "Create Item Model", required = true) @Valid @RequestBody ItemCreationDefaultsInfoRequest itemCreateDefaultInfoModel)
			throws InvalidToken, ExternalSecurityException, InvalidParameterException, ServiceException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		ItemCreationDefaultsInfoDTO itemCreateInfoDTO = invService.getItemCreationDefaultsInfo(domainUser,
				itemCreateDefaultTypeCode);
		if (itemCreateInfoDTO == null) {
			throw new ServiceException(errors.getErrors().get("ITEM_TYPE_NOT_FOUND").formatMessage(itemCreateDefaultTypeCode));
		}
		Integer newSuNo = invService.createItemFromDefaults(domainUser, itemCreateInfoDTO, itemCreateDefaultInfoModel);
		return new SkuNumberResponse(newSuNo);
	}
	// req res changes ends here

	// TODO - Get valid SKU number from DB which is having data to check item status
	// Get Item Restriction information

	@GetMapping(IRnetDomain.GET_ITEM_STATUS_INFO)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Item current status information by SKU Number from Item Inventory", notes = "Returns Item's current status information", response = StatusClassHdrDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = StatusClassHdrDTO.class) })
	public StatusClassHdr getItemCurrentStatusInformation(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemCurrentStatusDescription(domainUser, skuNumber, siteNumber);
	}

	// Find Inventory by Lookup
	// User Preference support
	@PostMapping(value = IRnetDomain.FIND_INVENTORY_ITEM)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find Inventory Items", notes = "Returns an array of inventory items.", response = InventorySearchResultsDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = InventorySearchResultsDTO.class) })
	public List<InventorySearchResultsDTO> findInventory(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Item Lookup Model", required = true) @Valid @RequestBody ItemLookUpModel itemLookup)
			throws InvalidToken, ExternalSecurityException, ServiceException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.findInventoryItem(domainUser, itemLookup.getSiteNo(), itemLookup);
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// Drop Down data & Helper API Call
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@GetMapping(IRnetDomain.ITEM_TYPE_BY_CODE)
	@ApiOperation(value = "Find Item Type by code", notes = "Returns item Type by code name.", response = ItemCreationDefaultsHdr.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemCreationDefaultsHdr.class) })
	public ItemCreationDefaultsHdr getItemTypeByCode(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Item Type Code", required = true) @PathVariable(name = "item_type_code") String itemTypeCode) {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getCustomItemCreateDefaultHdr(domainUser, itemTypeCode);
	}

	@GetMapping(IRnetDomain.GET_ITEM_CREATE_DEFAULTS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Item Creation Defaults", notes = "Returns an array of the available Item Creation Defaults", response = ItemCreationDefaultModel.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemCreationDefaultModel.class) })
	public List<ItemCreationDefaultModel> getItemCreationDefaults(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException, ServiceException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		List<ItemCreationDefaultModel> retTypes = null;
		List<ItemCreationDefaultsHdrDTO> itemTypes = invService.getItemCreationDefaults(domainUser);
		if (itemTypes != null && itemTypes.size() > 0) {
			retTypes = new ArrayList<ItemCreationDefaultModel>();
			for (ItemCreationDefaultsHdrDTO type : itemTypes) {
				retTypes.add(buildItemCreationDefaultModel(type));
			}
		} else {
			throw new ServiceException(errors.getErrors().get("NO_ITEM_TYPE_CODE_FOUND"));
		}
		return retTypes;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// Helper methods
	// --------------------------------------------------------------------------------------------------------------------------------------------

	private ItemCreationDefaultModel buildItemCreationDefaultModel(ItemCreationDefaultsHdrDTO type) {
		ItemCreationDefaultModel itemDefsModel = new ItemCreationDefaultModel();
		itemDefsModel.setItemTypeCd(type.getItemTypeCd());
		itemDefsModel.setDisplayLabel(type.getDisplayLabel());
		itemDefsModel.setFunctionCd(type.getFunctionCd());
		itemDefsModel.setHintText(type.getHintText());
		return itemDefsModel;
	}
	
	// changes start here

	// General Info form from diff tables with 3 api service calls combined
	@GetMapping(value = IRnetDomain.SEARCH_SKU) // SKU- 1304716
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search sku details from Inventory", notes = "Returns sku details for given SKU number", response = SkuInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SkuInfoResponse.class) })
	public SkuInfoResponse getDetailSKUInfo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNumber)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getDetailSKUInfo(domainUser, skuNumber, siteNumber);
	}

	@GetMapping(value = IRnetDomain.GET_ITEM_NOTES) // SKU- 1304716
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search item notes from Item Inventory", notes = "Returns list of item notes for a given SKU and site number.", response = ItemNote.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemNote.class) })
	public List<ItemNote> getItemNotesList(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemNotesList(domainUser, skuNumber, siteNumber);
	}

	// returns the attribute value for the given sku/attribute name. Returns
	// NULL if item doesn't have the given attribute
	// for AttributeNameEntity only not useful at time
	@GetMapping(value = IRnetDomain.GET_ITEM_ATTR_VALUE) // SKU- 1304716
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search attribute value from Item Inventory", notes = "Returns value of attribute for given SKU number.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public String getItemAttributeValue(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber,
			@ApiParam(value = "Attribute Name", required = true) @PathVariable(name = "attrName") String attrName)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemAttributeValue(domainUser, skuNumber, attrName);
	}

	// getCurrentSite

	@GetMapping(value = IRnetDomain.GET_SITE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search current site nummber", notes = "Returns current site number of the logged in user.", response = Integer.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer getCurrentSite(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getCurrentSite(domainUser);
	}

	// item upc by sku and type code

	@GetMapping(value = IRnetDomain.GET_ITEM_UPC)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search item upc by SKU number and type code", notes = "Returns item upc details for given SKU number and type code.", response = ItemUpcResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemUpcResponse.class) })
	public List<ItemUpcResponse> getItemupcBySkuAndTypeCd(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber,
			@ApiParam(value = "UPC Type Code", required = true) @RequestParam(name = "upc_type_code") String typeCode)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemupcBySkuAndTypeCd(domainUser, skuNumber, typeCode);
	}

	// item upc by sku only
	@GetMapping(value = IRnetDomain.GET_ITEM_UPC_BY_SKU)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search item upc by SKU number", notes = "Returns item upc details for given SKU number.", response = ItemUpcResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemUpcResponse.class) })
	public List<ItemUpcResponse> getItemUPCBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemUPCBySku(domainUser, skuNumber);
	}

	// TBD get full item information
	@GetMapping(value = IRnetDomain.GET_ITEM_PRIMARY)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search primary item from the Inventory", notes = "Returns list of primary item data for a given site and item id.", response = ItemPrimaryDataDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemPrimaryDataDTO.class) })
	public List<ItemPrimaryDataDTO> getItemsPrimaryData(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber,
			@ApiParam(value = "Item Id", required = true) @PathVariable(name = "itemId") String itemId)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemsPrimaryData(domainUser, siteNumber, itemId);
	}

	// get vendor item details
	@GetMapping(value = IRnetDomain.GET_MAIN_VEN_ITEM)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search main vendor item from vendors", notes = "Returns main vendor item from vendors", response = VendorItemDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorItemDTO.class) })
	public VendorItemDTO getMainVendorItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber,
			@ApiParam(value = "Vendor Id", required = true) @PathVariable(name = "vendorId") String vendorId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getMainVendorItem(domainUser, siteNumber, skuNumber, vendorId);
	}

	// get primary vendor item
	@GetMapping(value = IRnetDomain.GET_PRIMARY_VEN_ITEM)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search primary vendor item from vendors", notes = "Returns primary vendor item from vendors", response = VendorItemDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorItemDTO.class) })
	public VendorItemDTO getPrimaryVendorItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getPrimaryVendorItem(domainUser, siteNumber, skuNumber);
	}

	// get primary vendor item
	// 1472208/111
	@GetMapping(value = IRnetDomain.GET_PRIMARY_VEN_ITEM_FOR_SKU)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search primary Vendor Item For Sku And Site numbers", notes = "Returns primary vendor item if any else error dto", response = ErrorDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ErrorDTO.class) })
	public List<ErrorDTO> getPrimaryVendorItemForSiteAndSku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getPrimaryVendorItemForSiteAndSku(domainUser, siteNumber, skuNumber);
	}

	// get item identifiers
	// TBD check the valid reccords in DB
	@GetMapping(value = IRnetDomain.GET_ITEM_IDENTIFIERS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search item Identifier for a sku , site and vendor id", notes = "Returns item identifier for a sku or mfg or upc or vendor item", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public String getItemIdentifier(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Identifier Type", required = true) @PathVariable(name = "identifierType") String identifierType,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber,
			@ApiParam(value = "Vendor Id", required = true) @PathVariable(name = "vendorId") String vendorId)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		String searchResult = null;
		
			if ((identifierType.equalsIgnoreCase(ItemIdentifierType.VENDOR_ITEM.toString()) && skuNumber > 0
					&& siteNumber > 0))
				searchResult = invService.getItemIdentifier(domainUser, ItemIdentifierType.VENDOR_ITEM, siteNumber,
						skuNumber, vendorId);

			else if (identifierType.equalsIgnoreCase(ItemIdentifierType.MFG_CD.toString()))
				searchResult = invService.getItemIdentifier(domainUser, ItemIdentifierType.MFG_CD, siteNumber,
						skuNumber, vendorId);

			else if (identifierType.equalsIgnoreCase(ItemIdentifierType.SKU.toString()))
				searchResult = invService.getItemIdentifier(domainUser, ItemIdentifierType.SKU, siteNumber, skuNumber,
						vendorId);

			else if (identifierType.equalsIgnoreCase(ItemIdentifierType.UPC.toString()))
				searchResult = invService.getItemIdentifier(domainUser, ItemIdentifierType.UPC, siteNumber, skuNumber,
						vendorId);

		return searchResult;
	}

	// TBD vendor item search for vendor info screen however needs additiional fields hence will return full dto
	@GetMapping(value = IRnetDomain.SEARCH_VEN_ITEM)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search vendor item from vendors", notes = "Returns vendor item from vendors", response = VendorItemInfoDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorItemInfoDTO.class) })
	public List<VendorItemInfoDTO> searchVendorItemInfo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Group", required = true) @PathVariable(name = "siteGroup") String siteGroup,
			@ApiParam(value = "Is Primary", required = true) @RequestParam(name = "isPrimary") boolean isPrimary,
			@ApiParam(value = "Vendor Id", required = true) @RequestParam(name = "vendorId") String vendorId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getVendorItemInfo(domainUser, skuNumber, siteGroup, isPrimary, vendorId);
	}

	@GetMapping(value = IRnetDomain.GET_GROUP)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search site group by group id", notes = "Returns site group by group id", response = SiteGroupInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SiteGroupInfoResponse.class) })
	public SiteGroupInfoResponse getGroup(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Group Id", required = true) @PathVariable(name = "groupId") String groupId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getGroup(domainUser, groupId);
	}

	// all vendors for the item
	// need to check the custom fields needed in case UI needed
	@GetMapping(value = IRnetDomain.GET_VEN_OF_ITEMS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search vendors for the items", notes = "Returns list of vendors for a given SKU and site number.", response = VendorDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorDTO.class) })
	public List<VendorDTO> getVendorsForItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getVendorsForItem(domainUser, siteNumber, skuNumber);
	}

	// all active vendors for the item

	@GetMapping(value = IRnetDomain.GET_ACTIVE_VEN_OF_ITEMS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search active vendors for the items", notes = "Returns list of active vendors for given SKU and site number.", response = VendorDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorDTO.class) })
	public List<VendorDTO> getVendorsForItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Show Active Vendors Flag", required = true) @RequestParam(name = "showActiveVendors") boolean showActiveVendors)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getVendorsForItem(domainUser, siteNumber, skuNumber, showActiveVendors);
	}

	// deal api

	@GetMapping(value = IRnetDomain.GET_DEALS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search deals for a given site sku and deal number", notes = "Returns deals details for a given site sku and deal number", response = DealsInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = DealsInfoResponse.class) })
	public DealsInfoResponse getDealdtl(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber,
			@ApiParam(value = "Seq Number", required = true) @PathVariable(name = "seqNo") long seqNo,
			@ApiParam(value = "Deal Number", required = true) @PathVariable(name = "dealNo") long dealNo)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		DealdtlDTOId id = new DealdtlDTOId(dealNo, siteNumber, seqNo); 
		return invService.getDealdtl(domainUser, id);
	}
	
	// for retail cost as per the site and sku

	@GetMapping(value = IRnetDomain.GET_COSTS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search cost details for a given skua and site number", notes = "Returns different type of costs associated with the item", response = CostDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = CostDTO.class) })
	public CostDTO getCost(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getCost(domainUser, skuNumber, siteNumber);
	}

	// Find Inventory Item by LookUpCode & UPCId & SiteNumber
	@GetMapping(value = IRnetDomain.FIND_DUPLICATES_ITEMS) // lookUpCd=456RT&isUpcId=false&siteNo=111
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find the duplicate items", notes = "Returns a list of duplicate items", response = DuplicateItemInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = DuplicateItemInfoResponse.class),
			@ApiResponse(code = 501, message = IRnetDomain.INVALID_UDTOKEN) })
	public List<DuplicateItemInfoResponse> findDuplicates(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Lookup Code (MFG Code or UPC Id)", required = true) @RequestParam(value = IRnetDomain.LOOKUP_CD) String lookUpCode,
			@ApiParam(value = "Is UPC Id", defaultValue = "false", required = true) @RequestParam(value = IRnetDomain.IS_UPC_ID) Boolean isUpc,
			@ApiParam(value = "Site Number", required = true, defaultValue = "0") @RequestParam(value = IRnetDomain.SITE_NO) Integer siteNo)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.findDuplicateItems(domainUser, isUpc, lookUpCode, siteNo);
	}

	// Get Global information
	@GetMapping(IRnetDomain.GET_ITEM_GLOBAL_INFO) // SKU-1289701
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Item global information by sku and site num from Item Inventory", notes = "Returns global information of the item", response = GlobalInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = GlobalInfoResponse.class) })
	public GlobalInfoResponse getItemGlobalInformation(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getInventoryItemGlobalInfo(domainUser, skuNumber, siteNumber);
	}

	@GetMapping(IRnetDomain.GET_ITEM_KIT_INFO)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Item Kit information by SKU Number", notes = "Returns kit information of the item", response = InventoryGlobalInfoDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = InventoryGlobalInfoDTO.class) })
	public InventoryGlobalInfoDTO getItemKitInfoBySKU(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getKitInfoBySkuNo(domainUser, skuNumber);
	}

	@GetMapping(IRnetDomain.GET_ITEM_RESTRICTION_INFO)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Item restriction information by SKU Number", notes = "Returns restriction information of the item", response = StatusClassHdrDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = StatusClassHdrDTO.class) })
	public StatusClassHdrDTO getItemRestrictInformation(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemRestrictDescription(domainUser, skuNumber, siteNumber);
	}

	@GetMapping(value = IRnetDomain.GET_ITEM_POS_INFO)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get POS information of the item by SKU Number", notes = "Returns POS information of the item", response = StatusClassHdrDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = StatusClassHdrDTO.class) })
	public StatusClassHdrDTO getItemPosStatusInformation(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemPosStatus(domainUser, skuNumber, siteNumber);
	}

	// Item Types Dropdown
	// Unit of Measurement
	@GetMapping(IRnetDomain.ITEMS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Item Type Codes", notes = "Returns list of item type codes", response = ItemTypeCodeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemTypeCodeResponse.class) })
	public List<ItemTypeCodeResponse> getItemType(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemType(domainUser);
	}

	// Item Hierarchy Drop-down
	@GetMapping(IRnetDomain.DEPT_GROUPS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Department groups assigned to user", notes = "Return list of department groups with department group number and description", response = DepartmentGroupResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = DepartmentGroupResponse.class) })
	public List<DepartmentGroupResponse> getDeptGroups(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getDeptGroups(domainUser);
	}

	// departments drop-down
	// Item Hierarchy Drop-down
	@GetMapping(IRnetDomain.DEPTARTMENTS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Departments on the basis of department group number", notes = "Return list of departments with department number and description", response = Department.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Department.class) })
	public List<Department> getDepts(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Dept Group No)", required = true) @PathVariable(name = "department_group_no") int deptGrpNo) {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getDepts(domainUser, deptGrpNo);
	}

	// Classes drop down

	@GetMapping(IRnetDomain.GET_CLASSES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get classes of the item", notes = "Returns list of class with class number and description", response = ClassDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ClassDetails.class) })
	public List<ClassDetails> getClass(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Dept No)", required = true) @PathVariable(name = "department_no") int deptNo) {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getClasses(domainUser, deptNo);
	}

	// Lines Drop-down

	@GetMapping(IRnetDomain.GET_LINES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get lines information", notes = "Returns list of lines with line number and description ", response = Line.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Line.class) })
	public List<Line> getLines(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Dept No)", required = true) @PathVariable(name = "department_no") int deptNo,
			@ApiParam(value = "Class No)", required = true) @PathVariable(name = "class_no") int classNo) {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getLines(domainUser, deptNo, classNo);
	}

	@GetMapping(IRnetDomain.GET_UM_CODES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get code values and ref id", notes = "Returns list of codes with id, value and meaning.", response = DomainRefCode.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = DomainRefCode.class) })
	public List<DomainRefCode> getUMCodesInfo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {

		List<DomainRefCode> list = new ArrayList<>();
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		list = invService.getUMCodesInfo(domainUser, IRnetDomain.PACK_UM_CODE_VALUES);
		return list;
	}

	// cloned apis end here

	// site visibility from current site
	// need to check for the permission related settings if any
	@GetMapping(value = IRnetDomain.GET_SITES_VISIBLE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all sites visible from the current site", notes = "Returns all sites visible from the current site", response = SiteDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SiteDTO.class) })
	public List<SiteDTO> getSitesVisibility(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getSitesVisibility(domainUser, siteNumber);
	}

	// for owner id call siteAPI
	@GetMapping(value = IRnetDomain.GET_SITE_DETAILS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get site details using site number", notes = "Returns all information for specific site number", response = SiteInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SiteInfoResponse.class) })
	public SiteInfoResponse getSite(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getSite(domainUser, siteNumber);
	}

	// item attributes drop-down for SELL_UM
	// TBD check how attribute id is selected currently it is 957

	

	// search vendor by Vendor Id
	// public VendorDTO getVendor(IUserIdentity userIdentity, String vendorId);

	@GetMapping(value = IRnetDomain.GET_VENDOR_BY_ID)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get vendor by vendor id", notes = "Returns vendor details", response = VendorDetailsInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorDetailsInfoResponse.class) })
	public VendorDetailsInfoResponse getVendor(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Vendor Id", required = true) @PathVariable(value = "vendorId") String vendorId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getVendor(domainUser, vendorId);
	}

	@GetMapping(value = IRnetDomain.SEARCH_TENDER_CERTIFICATES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get tender certificates", notes = "Returns list of all tender certificates", response = TenderCertificateInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = TenderCertificateInfoResponse.class) })
	public List<TenderCertificateInfoResponse> getTenderCertificatePrograms(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getTenderCertificatePrograms(domainUser);
	}

	@GetMapping(value = IRnetDomain.SEARCH_GL_CATEGORIES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get G/L categories", notes = "Returns list of all G/L categories", response = GLCategoryInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = GLCategoryInfoResponse.class) })
	public List<GLCategoryInfoResponse> getGLCategories(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getGLCategories(domainUser);
	}

	@GetMapping(value = IRnetDomain.GET_COUNTRIES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get countries", notes = "Returns list of countries", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public List<String> getCountryCodes(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getCountryCodes(domainUser);
	}

	@GetMapping(value = IRnetDomain.GET_STATES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get velocity codes", notes = "Returns list of velocity codes", response = StatesProvinceInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = StatesProvinceInfoResponse.class) })
	public List<StatesProvinceInfoResponse> getGeographicRegionsForCountry(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Country Code", required = true) @PathVariable(value = "countryCd") String countryCd)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getGeographicRegionsForCountry(domainUser, countryCd);
	}

	// public List<AttributeListValueAllDTO>
	// getAttributeListValuesById(IUserIdentity userIdentity, Integer attributeId);
	@GetMapping(value = IRnetDomain.GET_ATTRIBUTE_VALUES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get attribute values", notes = "Returns list of attribute values for a given attribute id", response = AttributeListValuesInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = AttributeListValuesInfoResponse.class) })
	public List<AttributeListValuesInfoResponse> getAttributeListValuesById(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Attribute Id", required = true) @PathVariable(value = "attributeId") int attributeId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getAttributeListValuesById(domainUser, attributeId);
	}

	// public ItemCreationDefaultsInfoDTO getItemCreationDefaults(IUserIdentity
	// userIdentity, Long itemCreationId);
	@GetMapping(value = IRnetDomain.GET_ITEM_CREATE_ATTRIBUTE_DEFAULTS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get default attribute id and list of values", notes = "Returns default attribute id and list of values", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public ItemCreationDefaultsInfoDTO getItemCreateDefaults(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Item Creation Id", required = true) @PathVariable(value = "itemCreationId") long itemCreationId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemCreateDefaults(domainUser, itemCreationId);
	}

	// public ConfigrecDTO getConfigrecByName(IUserIdentity userIdentity, String
	// configName);
	@GetMapping(value = IRnetDomain.ATTRIBUTE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get default attribute id", notes = "Returns default attribute details", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public ConfigrecDTO getConfigrecByName(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getConfigrecByName(domainUser, IRnetDomain.ATTRIBUTE1_ID);
	}

	// vendor imports
	@GetMapping(IRnetDomain.VENDOR_IMPORTS) // SKU-1289701
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get vendor import by sku and site num from Item Inventory", notes = "Returns vendor import information", response = VendorImportInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorImportInfoResponse.class) })
	public VendorImportInfoResponse getVendorImport(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getVendorImport(domainUser, skuNumber, siteNumber);
	}

	@GetMapping(IRnetDomain.ALL_DEPTARTMENTS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all the departments", notes = "Return list of departments with dept number and description", response = DepartmentDetail.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = DepartmentDetail.class) })
	public List<DepartmentDetail> getAllDepartmentss(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		List<DepartmentGroupResponse> listOfDeptGrpNos = invService.getDeptGroups(domainUser);

		List<DepartmentDetail> allDepartments = new ArrayList<>();

		if (!listOfDeptGrpNos.isEmpty()) {
			for (DepartmentGroupResponse response : listOfDeptGrpNos) {

				List<Department> listOfDepts = invService.getDepts(domainUser,
						response.getDeptGrpNo());

				for (Department deptRes : listOfDepts) {

					DepartmentDetail resp = new DepartmentDetail();

					if (response.getDeptGrpNo() != null && response.getDeptGrpNo() > 0)
						resp.setDeptGrpNo(response.getDeptGrpNo());

					if (deptRes.getDeptNo() != null && deptRes.getDeptNo() > 0)
						resp.setDeptNo(deptRes.getDeptNo());

					if (StringUtils.isNotBlank(deptRes.getDescription()))
						resp.setDescription(deptRes.getDescription());

					allDepartments.add(resp);
				}

			}
		}
		return allDepartments;
	}

	@GetMapping(value = IRnetDomain.ITEM_NOTES_BY_SKU) // SKU- 1304716
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search item notes for a given sku", notes = "Returns list of item notes for a given SKU number.", response = ItemNote.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemNote.class) })
	public List<ItemNote> getItemNoteBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemNoteBySku(domainUser, skuNumber);
	}

	@PostMapping(IRnetDomain.CREATE_ITEM_NOTE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Item note for a given SKU number", notes = "Returns the created item note for a given SKU number .", response = ItemNoteDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public ItemNote createItemNoteBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Item Note Model", required = true) @Valid @RequestBody ItemNotesModel itemNotesModel)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.createItemNoteBySku(domainUser, itemNotesModel);
	}
	
	// check starts
	
	@PostMapping("/sample/createnote")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Item note for a given SKU number", notes = "Returns the created item note for a given SKU number .", response = ItemNoteDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public ItemNoteDTO createItemNoteTests(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Item Note Model", required = true) @Valid @RequestBody ItemNotesRequest itemNotesModel)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.createItemNoteBySkuTests(domainUser, itemNotesModel);
	}
	// ends 

	@DeleteMapping(IRnetDomain.DELETE_ITEM_NOTE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete Item note for a given sku number", notes = "Deletes Item note for a given sku number", response = SuccessResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public SuccessResponse deleteItemNoteBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Note Id", required = true) @PathVariable(name = "note_id") int noteId,
			@ApiParam(value = "Item Note Model", required = true) @Valid @RequestBody ItemNotesModel itemNotesModel)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.deleteItemNoteBySku(domainUser, noteId, itemNotesModel);
	}
	
	@PutMapping(IRnetDomain.UPDATE_ITEM_NOTE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Item note for a given sku number", notes = "Updates Item note for a given sku number", response = ItemNote.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public ItemNote updateItemNoteBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Note Id", required = true) @PathVariable(name = "note_id") int noteId,
			@ApiParam(value = "Item Note Model", required = true) @Valid @RequestBody ItemNotesModel itemNotesModel)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.updateItemNoteBySku(domainUser, noteId, itemNotesModel);
	}
	
	// public List<AttributeNameDTO>
	// getAttributeNameListByFunctionalUsageAndActiveFl(IUserIdentity userIdentity,
	// AttributeSearchDTO AttributeSearchDTO);
	@GetMapping(IRnetDomain.ATTR_LIST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "List of attributes available", notes = "Returns list of attributes available", response = ItemAttributeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemAttributeResponse.class) })
	public List<ItemAttributeResponse> getAttributeNameListByFunctionalUsageAndActiveFl(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getAttributeNameListByFunctionalUsageAndActiveFl(domainUser);
	}
	
	@GetMapping(value = IRnetDomain.GET_ATTRIBUTES_LIST_BY_OWNER_ATTRIBUTE_IDS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get sell UM", notes = "Returns sell UM values", response = ItemAttributeValueResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemAttributeValueResponse.class) })
	public List<ItemAttributeValueResponse> getAttributeListValuesByAttrIdOwnerId(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Attribute Id", required = true) @PathVariable(value = "attribute_id") Integer attributeId,
			@ApiParam(value = "Owner Id", required = true) @PathVariable(value = "owner_id") Integer ownerId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getAttributeListValuesByAttrIdOwnerId(domainUser, attributeId, ownerId);
	}

	@GetMapping(value = IRnetDomain.GET_ITEM_ATTR_LIST) // 1472208/111
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search list of attributes from Item Inventory", notes = "Returns list of item attributes for given SKU and site numbers.", response = ItemAttributeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemAttributeResponse.class) })
	public List<com.rediron.platform.domain.model.response.ItemAttributeResponse> getItemAttributeBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemAttributeBySku(domainUser, skuNumber, siteNumber);
	}
	
	// item attribute screen

	@GetMapping(value = IRnetDomain.ITEM_ATTR_LIST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search list of attributes from Item Inventory", notes = "Returns list of item attributes for given SKU number.", response = ItemAttributeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemAttributeResponse.class) })
	public List<ItemAttributeResponse> getItemAttributesBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemAttributesBySku(domainUser, skuNumber);
	}

	@PostMapping(IRnetDomain.CREATE_ATTRIBUTE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Item attribute for a given sku number", notes = "Returns the created item attribute SKU number.", response = AttributeNameResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public AttributeNameResponse createItemAttributeBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Attribute Model", required = true) @Valid @RequestBody AttributeModel attributeModel)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.createItemAttributeBySku(domainUser, attributeModel);
	}
	
	@PutMapping(IRnetDomain.UPDATE_ATTRIBUTE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update item attribute for a sku number", notes = "Updates item attribute for a sku number", response = AttributeNameResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public UpdatedAttributeResponse updateItemAttributeBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Attribute Model", required = true) @Valid @RequestBody AttributeModel attributeModel)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.updateItemAttributeBySku(domainUser, attributeModel);
	}

	@DeleteMapping(IRnetDomain.DELETE_ATTRIBUTE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete Item attribute for a given sku number", notes = "Deletes Item attribute for a given sku number", response = SuccessResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SuccessResponse.class) })
	public SuccessResponse deleteItemAttributeBySku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Attribute Model", required = true) @Valid @RequestBody AttributeModel attributeModel)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.deleteItemAttributeBySku(domainUser, attributeModel);
	}

	// all site groups
	@GetMapping(value = IRnetDomain.GET_ALL_SITE_GROUPS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all site groups", notes = "Returns list of all site groups", response = GroupsAllDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = GroupsAllDTO.class) })
	public List<GroupsAllDTO> getAllSiteGroups(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getAllSiteGroups(domainUser);
	}

	@PostMapping(IRnetDomain.CREATE_UPC)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create UPC for a given sku number", notes = "Returns the created item upc for a given SKU number.", response = ItemUpcResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public ItemUpcResponse createUPCForSku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "UPC Model", required = true) @Valid @RequestBody ItemUpcModel upcModel,
			@ApiParam(value = "Sku No", required = true) @PathVariable(name = "sku_no") int skuNo)
			throws InvalidToken, ExternalSecurityException, ServiceException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.createUPCForSku(domainUser, upcModel, skuNo);
	}

	@PutMapping(IRnetDomain.UPDATE_UPC)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Item upc for a given upc number", notes = "Returns the updated item upc for a given SKU number.", response = ItemUpcResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public ItemUpcResponse updateUPCForSku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "UPC Model", required = true) @Valid @RequestBody ItemUpcModel upcModel,
			@ApiParam(value = "Sku No", required = true) @PathVariable(name = "sku_no") int skuNo)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.updateUPCForSku(domainUser, upcModel, skuNo);
	}

	@DeleteMapping(IRnetDomain.DELETE_UPC)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete Item upc for a given sku number", notes = "Deletes Item upc for a given sku number", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public String deleteUPCForSku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Sku No", required = true) @PathVariable(name = "sku_no") int skuNo,
			@ApiParam(value = "Sku No", required = true) @PathVariable(name = "upc_id") String upcId,
			@ApiParam(value = "Sku No", required = true) @PathVariable(name = "modifier") int modifier)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken); 
		return invService.deleteUPCForSku(domainUser, skuNo, upcId, modifier);  
	}

	@PutMapping(IRnetDomain.UPDATE_INVENTORY)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update inventory information for a sku number", notes = "Returns updated inventory record for a sku number", response = Inventory.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public Inventory updateInvtory(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Inventory Model", required = true) @Valid @RequestBody com.rediron.platform.domain.model.request.InventoryModel inventoryModel,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(value = "sku_no") int skuNo,
			@ApiParam(value = "Site Number", required = true) @PathVariable(value = "site_no") int siteNo)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.updateInvtory(domainUser, inventoryModel, skuNo, siteNo);
	}

	@GetMapping(value = IRnetDomain.INVBYSIT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search inventory by site details", notes = "Returns inventory by site details for given site and SKU numbers", response = InvbysitAndPromoDetails.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = InvbysitAndPromoDetails.class) })
	public InvbysitAndPromoDetails getInvbysit(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNumber,
			@ApiParam(value = "Pricing Type", required = true) @RequestParam(name = "pricing_type") String pricingType)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getInvbysit(domainUser, skuNumber, siteNumber, pricingType);
	}
	
	@GetMapping(value = "/authoriseItem")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Item authorisation at HQ site", notes = "Authorizes item at HQ site", response = InventoryBySiteAndVendorItemListsDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = InventoryBySiteAndVendorItemListsDTO.class) })
	public List<InventoryBySiteAndVendorItemListsDTO> authoriseItemAtShipSite(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Ship Site No", required = true) @RequestParam(value = "shipSiteNo") int shipSiteNo,
			@ApiParam(value = "SKU No", required = true) @RequestParam(value = "skuNo") int skuNo,
			@ApiParam(value = "Vendor Id", required = false) @RequestParam(value = "vendorId") String vendorId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.authoriseItemAtShipSite(domainUser, shipSiteNo, skuNo, vendorId );
	}
	
	@GetMapping(value = "/isAuthorised")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Checks if the item is already authorized at HQ site", notes = "Checks if the item is already authorized at HQ site", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Boolean.class) })
	public Boolean isItemAlreadyAuthorized(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Ship Site No", required = true) @RequestParam(value = "ship_site_no") int shipSiteNo,
			@ApiParam(value = "SKU No", required = true) @RequestParam(value = "sku_no") int skuNo)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.isItemAlreadyAuthorized(domainUser, shipSiteNo, skuNo );
	}
	
	@GetMapping(value = "/search/itemcodes/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides user defined codes.", notes = "Returns user defined codes.", response = ItemCodeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemCodeResponse.class) })
	public ItemCodeResponse getItemCodes(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU No", required = true) @PathVariable(value = "sku_no") int skuNo)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getItemCodes(domainUser, skuNo);
	}
	
	@PutMapping(value = "/itemcodes/{sku_no}/{site_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Save or update the item codes for the sku", notes = "Save or update the item codes for the sku", response = Integer.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer updateItemCodes(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Item Codes Model", required = true) @RequestBody ItemCodesModel itemCodes,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(value = "sku_no") int skuNo,
			@ApiParam(value = "Site Number", required = true) @PathVariable(value = "site_no") int siteNo)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.saveOrUpdateItemCodes(domainUser, itemCodes, skuNo);
	}
	
	// reserved quantity details from App Manager
	@GetMapping(value = "/reservedQty")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides reserved quantity details for the sku number", notes = "Returns reserved quantity details for the sku number", response = ReservedQtyFullDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ReservedQtyFullDTO.class) })
	public ReservedQtyFullDTO getReservedQtyDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site No", required = true) @RequestParam(value = "siteNo") int siteNo,
			@ApiParam(value = "SKU No", required = true) @RequestParam(value = "skuNo") int skuNo,
			@ApiParam(value = "Site Group Id", required = true) @RequestParam(value = "siteGroupId") String siteGroupId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getReservedQtyDetails(domainUser, siteNo, skuNo, siteGroupId);
	}
	
	// public List<ItemMovementDetailDTO> getMovementDetails(IUserIdentity userIdentity,Integer skuNo, Integer siteNo)
	@GetMapping(value = "/movementinfo")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides movement details for the sku number", notes = "Returns movement details for the sku number", response = ItemMovementDetailDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ItemMovementDetailDTO.class) })
	public List<ItemMovementDetailDTO> getMovementDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site No", required = true) @RequestParam(value = "siteNo") int siteNo,
			@ApiParam(value = "SKU No", required = true) @RequestParam(value = "skuNo") int skuNo)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getMovementDetails(domainUser, siteNo, skuNo);
	}
	
	// public ExpectedQtyFullDTO getExpectedQtyFullBySiteGroupAndSku(IUserIdentity userIdentity,
	// InventoryGeneralSearchDTO invGeneralSearchDTO);
	
	@GetMapping(value = "/expectedQty")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides expected quantity details for the sku number", notes = "Returns expected quantity details for the sku number", response = ExpectedQtyFullDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ExpectedQtyFullDTO.class) })
	public ExpectedQtyFullDTO getExpectedQtyFullBySiteGroupAndSku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU No", required = true) @RequestParam(value = "skuNo") int skuNo,
			@ApiParam(value = "Site Group Id", required = true) @RequestParam(value = "siteGroupId") String siteGroupId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getExpectedQtyFullBySiteGroupAndSku(domainUser, skuNo, siteGroupId);
	}
	
	// summary info
	@GetMapping(value = "/summaryinfo/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of summary information for the sku number", notes = "Returns list of summary information for the sku number", response = SummaryInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SummaryInfoResponse.class) })
	public List<SummaryInfoResponse> getSummaryInfo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU No", required = true) @PathVariable(value = "sku_no") int skuNo,
			@ApiParam(value = "Site Group Id", required = true, defaultValue = "ALL") @RequestParam(value = "site_group_id") String siteGroupId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.getSummaryInfo(domainUser, skuNo, siteGroupId);
	}
	
	@PostMapping(value = "/updatesummaryinfo/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of summary information for the sku number", notes = "Returns list of summary information for the sku number", response = SummaryInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SummaryInfoResponse.class) })
	public List<SummaryInfoResponse> getSummaryInfo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU No", required = true) @PathVariable(value = "sku_no") int skuNo,
			@ApiParam(value = "Site Group Id", required = true, defaultValue = "ALL") @RequestParam(value = "site_group_id") String siteGroupId,
			@ApiParam(value = "Summary Info Model", required = true, defaultValue = "ALL") @RequestBody SummaryInfoRequest summaryInfoRequest)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.updateSummaryInfo(domainUser, skuNo, siteGroupId, summaryInfoRequest);
	}
	
	// update invbysit list in change site tab
	@PutMapping(value = "/updatechangesiteinfo/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of invbysit for the sku number", notes = "Returns list of invbysit for the sku number", response = Invbysit.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Invbysit.class) })
	public List<Invbysit> updateChangeSiteInfo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU No", required = true) @PathVariable(value = "sku_no") int skuNo,
			@ApiParam(value = "Site Group Id", required = true, defaultValue = "ALL") @RequestParam(value = "site_group_id") String siteGroupId,
			@ApiParam(value = "Change Site Info Model", required = true) @Valid @RequestBody ChangeSiteInfoRequest changeSiteInfoRequest)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.updateChangeSiteInfo(domainUser, skuNo, siteGroupId, changeSiteInfoRequest);
	}
	
	// update invbysit
	@PutMapping(IRnetDomain.UPDATE_INVBYSIT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of invbysit for the sku number", notes = "Returns list of invbysit for the sku number", response = Invbysit.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Invbysit.class) })
	public Invbysit updateInvbysitInfo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU No", required = true) @PathVariable(value = "sku_no") int skuNo,
			@ApiParam(value = "Site No", required = true) @PathVariable(value = "site_no") int siteNo,
			@ApiParam(value = "Change Site Info Model", required = true) @Valid @RequestBody UpdateInvbysitRequest updateInvbysitRequest)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return invService.updateInvbysit(domainUser, skuNo, siteNo, updateInvbysitRequest);
	}
	// changes end here

}
