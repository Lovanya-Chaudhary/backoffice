package com.rediron.platform.domain.api;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.models.ItemLookUpModel;
import com.rediron.platform.domain.services.CoreApiService;
import com.rediron.security.common.ISecurity;
import com.rediron.security.common.exceptions.ExternalSecurityException;
import com.rediron.security.common.exceptions.InvalidToken;
import com.rediron.security.common.service.SecurityCacheReadService;
import com.tomax.api.UserIdentity;
import com.tomax.config.domainRef.dto.DomainRefCodeDTO;
import com.tomax.inventory.dto.ClassDTO;
import com.tomax.inventory.dto.DeptgrpDTO;
import com.tomax.inventory.dto.InvbysitDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTO;
import com.tomax.inventory.dto.LineDTO;
import com.tomax.inventory.dto.StatusClassHdrDTO;
import com.tomax.inventory.dto.custom.InventoryGlobalInfoDTO;
import com.tomax.inventory.dto.custom.InventorySearchResultsDTO;
import com.tomax.vendor.dto.custom.VendorItemInfoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(basePath = "/core", value = "RetailNet Back office Core APIs", description = "RetailNet Back office Core Api(s)")
@RestController
@RequestMapping("/core")
public class CoreAPIController {
	
	Logger logger = LoggerFactory.getLogger(CoreAPIController.class);
	
    @Autowired
    private CoreApiService coreService;
    
    @Autowired
    public SecurityCacheReadService userIdCache;
    
    // Item Maintennace Screen
    //--------------------------------------------------------------------------------------------------------------------------------------------
    // Core API
    //--------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    //Search SKU
    @GetMapping(value = IRnetDomain.SEARCH_SKU)  //SKU- 1304716
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Search item by SKU from Item Inventory", notes = "Returns item for given SKU number.", response = InventorySearchResultsDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = InventorySearchResultsDTO.class)})
    public InventorySearchResultsDTO searchSKU(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
            @ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
            @ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber) throws InvalidToken, ExternalSecurityException, IllegalArgumentException {
        InventorySearchResultsDTO inventorySearchResultsDTO = null;
        UserIdentity domainUser = getDomainUser(userToken);
        ItemLookUpModel itemLookup = new ItemLookUpModel();
        itemLookup.setSkuNo(skuNumber);
        itemLookup.setSiteNo(siteNumber);
        try {
            List<InventorySearchResultsDTO> searchResult = coreService.findInventoryItem(domainUser, skuNumber, itemLookup);
            if (CollectionUtils.isNotEmpty(searchResult)) {
                inventorySearchResultsDTO = searchResult.get(0);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Find Inventory Error: " + e.getMessage());
            throw new IllegalArgumentException("Find Inventory Error: " + e.getMessage());
        }
        return inventorySearchResultsDTO;
    }
    
    //Get Global information

    @GetMapping(IRnetDomain.GET_ITEM_GLOBAL_INFO) //SKU-1289701
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Item global information by SKU Number & Site Number from Item Inventory", notes = "Returns Item's global information", response = InventoryGlobalInfoDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = InventoryGlobalInfoDTO.class)})
    public InventoryGlobalInfoDTO getItemGlobalInformation(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
            @ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
            @ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber) throws InvalidToken, ExternalSecurityException {
        return coreService.getInventoryItemGlobalInfo(getDomainUser(userToken), skuNumber, siteNumber);
    }
    
    //TODO - Find SKU Nbr which is having Kit info
    //Get Item Kit Info
    @GetMapping(IRnetDomain.GET_ITEM_KIT_INFO)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Item Kit information by SKU Number from Item Inventory", notes = "Returns Item's Kit information", response = InventoryGlobalInfoDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = InventoryGlobalInfoDTO.class)})
    public InventoryGlobalInfoDTO getItemKitInfoBySKU(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
            @ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber) throws InvalidToken, ExternalSecurityException {
        return coreService.getKitInfoBySkuNo(getDomainUser(userToken), skuNumber);
    }
    
    //TODO - Get valid SKU number from DB which is having data to check item status
    //Get Item Restriction information
    @GetMapping(IRnetDomain.GET_ITEM_RESTRICTION_INFO)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Item restriction information by SKU Number from Item Inventory", notes = "Returns Item's restriction information", response = StatusClassHdrDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = StatusClassHdrDTO.class)})
    public StatusClassHdrDTO getItemRestrictInformation(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
            @ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
            @ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber) throws InvalidToken, ExternalSecurityException {
        return coreService.getItemRestrictDescription(getDomainUser(userToken), skuNumber, siteNumber);
    }
    
    //TODO - Get valid SKU number from DB which is having data to check item POS Status
    //Get Item POS information

    @GetMapping(value = IRnetDomain.GET_ITEM_POS_INFO)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Item POS information by SKU Number from Item Inventory", notes = "Returns Item's POS information", response = StatusClassHdrDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = StatusClassHdrDTO.class)})
    public StatusClassHdrDTO getItemPosStatusInformation(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
            @ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
            @ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber) throws InvalidToken, ExternalSecurityException {
        return coreService.getItemPosStatus(getDomainUser(userToken), skuNumber, siteNumber);
    }
    
    //Item Hierarchy Drop-down
    @GetMapping(IRnetDomain.DEPT_GROUPS)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Hierarchy Items groups",
            notes = "Return list of Dept groups.",
            response = DeptgrpDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = DeptgrpDTO.class)})
    public List<DeptgrpDTO> getDeptGroups(@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {
        return coreService.getDeptGroups(getDomainUser(userToken));
    }

    //Classes drop down

    @GetMapping(IRnetDomain.GET_CLASSES)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Item's class",
            notes = "Return item's class.",
            response = ClassDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = ClassDTO.class)})
    public List<ClassDTO> getClass(@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
                                   @ApiParam(value = "Dept No)", required = true) @PathVariable(name = "deptNo") int deptNo) {
        return coreService.getClasses(getDomainUser(userToken), deptNo);
    }

    //Lines Drop-down

    @GetMapping(IRnetDomain.GET_LINES)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Item's Line ",
            notes = "Return item's Line.",
            response = LineDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = LineDTO.class)})
    public List<LineDTO> getLines(@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
                                  @ApiParam(value = "Dept No)", required = true) @PathVariable(name = "deptNo") int deptNo,
                                  @ApiParam(value = "Class No)", required = true) @PathVariable(name = "classNo") int classNo) {
        return coreService.getLines(getDomainUser(userToken), deptNo, classNo);
    }
    
    //Find Inventory Item by LookUpCode & UPCId & SiteNumber
    @GetMapping(value = IRnetDomain.FIND_ITEM_DUPLICATES)   //lookUpCd=456RT&isUpcId=false&siteNo=111
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find Item Duplicates",
            notes = "Returns an array of items that are duplicates. The site needs to be a number.",
            response = InventorySearchResultsDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = InventorySearchResultsDTO.class),
            @ApiResponse(code = 501, message = IRnetDomain.INVALID_UDTOKEN)})
    public List<InventorySearchResultsDTO> findItemDuplicates(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
            @ApiParam(value = "Lookup Code (MFG Code or UPC Id)", required = true) @RequestParam(value = IRnetDomain.LOOKUP_CD) String lookUpCode,
            @ApiParam(value = "Is UPC Id", defaultValue = "false", required = true) @RequestParam(value = IRnetDomain.IS_UPC_ID) Boolean isUpc,
            @ApiParam(value = "Site Number", required = true, defaultValue = "0") @RequestParam(value = IRnetDomain.SITE_NO) Integer siteNo)
            throws InvalidToken, ExternalSecurityException {
        UserIdentity domainUser = getDomainUser(userToken);
        try {
            return coreService.findDuplicates(domainUser, isUpc, lookUpCode, siteNo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @GetMapping(value = IRnetDomain.GET_ITEM_VENDOR_INFO)   //TODO - Get Valid VendorId ,SKU,siteGroup //http://localhost:9070/inventory/search/vendor/1304716/11/222
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find Item's Vendor Information",
            notes = "Returns items' Vendor information",
            response = InventorySearchResultsDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = VendorItemInfoDTO.class),
            @ApiResponse(code = 501, message = IRnetDomain.INVALID_UDTOKEN)})
    public List<VendorItemInfoDTO> getItemVendorInformation(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
            @ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber,
            @ApiParam(value = "Site Group", required = true) @PathVariable(name = "siteGroup") String siteGroup,
            @ApiParam(value = "Vendor Id", required = true) @PathVariable(name = "vendorId") String vendorId)
            throws InvalidToken, ExternalSecurityException {
        UserIdentity domainUser = getDomainUser(userToken);
        try {
            return coreService.getItemVendorInformation(domainUser, skuNumber, siteGroup, vendorId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    //Item Types Dropdown
    @GetMapping(IRnetDomain.ITEMS)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find Item Types",
            notes = "Returns an array of items Types.",
            response = ItemCreationDefaultsHdrDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = ItemCreationDefaultsHdrDTO.class)})
    public List<ItemCreationDefaultsHdrDTO> getItemType(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {
        return coreService.getItemType(getDomainUser(userToken));
    }
    
    @GetMapping(IRnetDomain.GET_UM_CODES)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Unit of measure codes ",
            notes = "Returns list of Unit of measure codes .",
            response = DomainRefCodeDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = DomainRefCodeDTO.class)})
    public List<DomainRefCodeDTO> getUMCodes(@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {
        return coreService.getUMCodes(getDomainUser(userToken), IRnetDomain.PACK_UM_CODE_VALUES);
    }
    
    private UserIdentity getDomainUser(String udToken) {
        UserIdentity domainUser = null;
        try {
            if (userIdCache != null && userIdCache.getUDToken(udToken) != null) {
                String userId = userIdCache.getUDToken(udToken).getUserName();
                logger.debug("Found user id -> " + userId);
                domainUser = new UserIdentity(udToken, userId);
            } else {
                throw new InvalidToken("User Id not found!");
            }
        } catch (Exception e) {
            // TBD
        }
        return domainUser;
    }
    
    // to check if invbysit can have multiple records
    @GetMapping("/ibscheck/{sku}/{site}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update inventory by site information for a sku number", notes = "Returns updated inventory by site record for a sku number", response = InvbysitDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST) })
	public List<InvbysitDTO> updateInvtoryBySit(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Sku", required = true) @PathVariable("sku") int sku,
			@ApiParam(value = "Site", required = true) @PathVariable("site") String site)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = getDomainUser(userToken);
		return coreService.getInvtoryBySitList(domainUser, sku, site);
	}

}
