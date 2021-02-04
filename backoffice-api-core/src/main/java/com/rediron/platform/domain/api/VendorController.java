package com.rediron.platform.domain.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

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

import com.rediron.platform.domain.constants.IVendorConstants;
import com.rediron.platform.domain.entities.VendorItemEntity;
import com.rediron.platform.domain.model.request.MenuItem;
import com.rediron.platform.domain.model.request.SupplierRequest;
import com.rediron.platform.domain.model.request.SuppliersToBeUpdated;
import com.rediron.platform.domain.model.request.VendorItemFieldsToUpdate;
import com.rediron.platform.domain.model.request.VendorItemModel;
import com.rediron.platform.domain.model.request.VendorRequest;
import com.rediron.platform.domain.model.response.SupplierDetailsResponse;
import com.rediron.platform.domain.model.response.VendorItemResponse;
import com.rediron.platform.domain.services.VendorService;
import com.rediron.platform.domain.util.AuthUtil;
import com.rediron.security.common.ISecurity;
import com.tomax.api.UserIdentity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author lovanya.chaudhary
 *
 */
@Api(basePath = IVendorConstants.B_VENDOR, value = "RetailNet Back office B Vendor functions ", description = "RetailNet Back office B Vendor fucntions")
@RestController
@RequestMapping(IVendorConstants.B_VENDOR)
public class VendorController {
	
	Logger logger = LoggerFactory.getLogger(VendorController.class);
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	AuthUtil authUtil;
	
	@GetMapping(IVendorConstants.OWNERID)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides owner id for the logged in site number of the user", notes = "Returns owner id for the logged in site number of the user", response = BigDecimal.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = BigDecimal.class) })
	public BigDecimal getSiteFlag(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNo){
		return vendorService.getOwnerIdFromVendor(siteNo);
	}
	
	@GetMapping(IVendorConstants.ALL_SUPPLIERS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of suppliers for the SKU number", notes = "Returns list of suppliers for the SKU number", response = SupplierDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SupplierDetailsResponse.class) })
	public List<SupplierDetailsResponse> getAllSuppliersForSku(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Sku Number", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.getAllSuppliersForSku(domainUser, skuNo);
	}
	
	// insert / update the supplier info for the sku using vendor id as supplier id
	@PostMapping("/supplier/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns supplier added to the sku number", notes = "Returns supplier added to the sku number", response = SupplierDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SupplierDetailsResponse.class) })
	public List<SupplierDetailsResponse> saveSupplier(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Supplier Model", required = true) @RequestBody SupplierRequest supplier,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		List<String> supplierNames = supplier.getSupplierName();
		return vendorService.saveSuppliers(domainUser, skuNo, supplierNames);
	}
	
	@PutMapping("/updatesupplier/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns supplier added to the sku number", notes = "Returns supplier added to the sku number", response = SupplierDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SupplierDetailsResponse.class) })
	public List<SupplierDetailsResponse> updateSupplier(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Supplier Model", required = true) @RequestBody SuppliersToBeUpdated suppliers,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.updateSupplier(domainUser, skuNo, suppliers);
	}
	
	@DeleteMapping("/deletesupplier/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns supplier added to the sku number", notes = "Returns supplier added to the sku number", response = SupplierDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SupplierDetailsResponse.class) })
	public List<SupplierDetailsResponse> deleteSupplier(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Supplier Model", required = true) @RequestBody SupplierRequest supplier,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		List<String> supplierNames = supplier.getSupplierName();
		return vendorService.deleteSupplier(domainUser, skuNo, supplierNames);
	}
	
	@GetMapping("/allvendors/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of the vendor details for the sku number", notes = "Returns list of the vendor details for the sku number", response = VendorItemResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorItemResponse.class) })
	public List<VendorItemResponse> getVendorItemInfo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Group Id", required = true) @RequestParam(name = "site_group_id") String siteGroupId,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNo,
			@ApiParam(value = "Vendor Id", required = true) @RequestParam(name = "vendor_id") String vendorId){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.getVendorItemInfo(domainUser, siteGroupId, skuNo, vendorId);
	}
	
	@PostMapping("/vendorItem/{site_no}/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates vendor item", notes = "Creates vendor item", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public String createVendorItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Vendor Model", required = true) @RequestBody VendorRequest vendorRequest,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNo,
			@ApiParam(value = "Site Group Id", required = true) @RequestParam(name = "site_group_id") String groupId,
			@ApiParam(value = "SKU No", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.createVendorItem(domainUser, vendorRequest, siteNo, groupId, skuNo);
	}
	
	@PutMapping("/updatevendorItem/{site_no}/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Updates vendor item", notes = "Updates vendor item", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public String updateVendorItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Vendor Item Update Model", required = true) @RequestBody VendorItemFieldsToUpdate vendorItemFieldsToUpdate,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int copyFromSit,
			@ApiParam(value = "Site Group Id", required = true) @RequestParam(name = "site_group_id") String copyToSitGrp,
			@ApiParam(value = "SKU No", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.updateVendorItem(domainUser, vendorItemFieldsToUpdate, copyFromSit, copyToSitGrp, skuNo);
	}
	
	@DeleteMapping("/deletevendorItem/{seq_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Deletes vendor item", notes = "Deletes vendor item", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public String deleteVendorItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Seq Number", required = true) @PathVariable(name = "seq_no") BigInteger seqNo) {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.deleteVendorItem(domainUser, seqNo);		
	}
	
	@GetMapping("/vendors/{sku_no}/{site_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides item id for the passed item type", notes = "Returns item id for the passed item type", response = MenuItem.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = MenuItem.class) })
	public List<VendorItemEntity> getVendorItemsForSkuAndSite(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Sku No", required = true) @PathVariable("sku_no") int skuNo,
			@ApiParam(value = "Site No", required = true) @PathVariable("site_no") int siteNo) throws Exception{
		logger.info("Inside Controller");
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.getVendorItemsForSkuAndSite(domainUser, skuNo, siteNo);
	}
	
	@PostMapping("/create/vendor/item/{site_no}/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates vendor item", notes = "Creates vendor item", response = VendorItemEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorItemEntity.class) })
	public VendorItemEntity createVendorItemBySkuAndSite(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Vendor Model", required = true) @RequestBody VendorItemModel vendorItemModel,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNo,
			@ApiParam(value = "SKU No", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.createVendorItemBySkuAndSite(domainUser, vendorItemModel, siteNo, skuNo);
	}
	
	@PostMapping("/create/vendoritems/{site_no}/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates vendor item", notes = "Creates vendor item", response = VendorItemEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorItemEntity.class) })
	public List<VendorItemEntity> createVendorItemUsingSiteGroup(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Vendor Model", required = true) @RequestBody VendorItemModel vendorItemModel,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNo,
			@ApiParam(value = "Site Group Id", required = true) @RequestParam(name = "site_group_id") String groupId,
			@ApiParam(value = "SKU No", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.createVendorItemUsingSiteGroup(domainUser, vendorItemModel, siteNo, groupId, skuNo);
	}
	
	@PutMapping("/update/vendoritem/{seq_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Updates vendor item", notes = "Updates vendor item", response = VendorItemEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorItemEntity.class) })
	public VendorItemEntity updateVendorItemBySeqNo(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Vendor Item Update Model", required = true) @RequestBody VendorItemModel vendorItemModel,
			@ApiParam(value = "Seq No", required = true) @PathVariable(name = "seq_no") String seqNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.updateVendorItemBySeqNo(domainUser, vendorItemModel, seqNo);
	}
	
	@PutMapping("/update/vendoritems/{sku_no}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Updates vendor item", notes = "Updates vendor item", response = VendorItemEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorItemEntity.class) })
	public List<VendorItemEntity> updateAllVendorItemsForSkuAndVendorId(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Vendor Item Update Model", required = true) @RequestBody VendorItemModel vendorItemModel,
			@ApiParam(value = "SKU No", required = true) @PathVariable(name = "sku_no") int skuNo){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return vendorService.updateAllVendorItemsForSkuAndVendorId(domainUser, vendorItemModel, skuNo);
	}
	
	// for summary info ref below 
	// public List<SiteSummaryDTO> getSiteSummaryInfo(IUserIdentity userIdentity, Integer skuNo, List<Integer> sites, List<Integer> price, List<Integer> cost);

}
