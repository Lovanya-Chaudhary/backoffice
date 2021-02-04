package com.rediron.platform.domain.api;

import java.util.List;

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

import com.rediron.platform.domain.constants.ILOVConstants;
import com.rediron.platform.domain.constants.ItemStatusTypeCode;
import com.rediron.platform.domain.constants.UserDefinedTypeCode;
import com.rediron.platform.domain.model.response.BuyerDetailsResponse;
import com.rediron.platform.domain.model.response.CodeDetailsResponse;
import com.rediron.platform.domain.model.response.CommissionCodeDetailsResponse;
import com.rediron.platform.domain.model.response.DefaultDCDetailsResponse;
import com.rediron.platform.domain.model.response.GLCategoryInfoResponse;
import com.rediron.platform.domain.model.response.InventoryTypeResponse;
import com.rediron.platform.domain.model.response.LinkChargesDetailsResponse;
import com.rediron.platform.domain.model.response.ReportCodeDetailsResponse;
import com.rediron.platform.domain.model.response.SiteDetailResponse;
import com.rediron.platform.domain.model.response.StatusDetailsResponse;
import com.rediron.platform.domain.model.response.StatusInfoDetailsResponse;
import com.rediron.platform.domain.model.response.SupplierDetailsResponse;
import com.rediron.platform.domain.model.response.TareDetailsResponse;
import com.rediron.platform.domain.model.response.TenderCertificateInfoResponse;
import com.rediron.platform.domain.model.response.VendorsResponse;
import com.rediron.platform.domain.services.LOVService;
import com.rediron.security.common.ISecurity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * @author lovanya.chaudhary
 *
 */
@Api(basePath = ILOVConstants.LOV, value = "RetailNet Back office LOVs", description = "RetailNet Back office LOVs")
@RestController
@RequestMapping(ILOVConstants.LOV)
public class LOVController {
	
	Logger logger = LoggerFactory.getLogger(LOVController.class);
	
	@Autowired
	private LOVService lovService;
	
	@GetMapping(ILOVConstants.BUYERS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of buyers", notes = "Returns list of buyers", response = BuyerDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = BuyerDetailsResponse.class) })
	public List<BuyerDetailsResponse> getBuyerDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(value = "site_no") int siteNo){

		return lovService.getBuyerDetails(siteNo);
	}
	
	@GetMapping(ILOVConstants.INVENTORY_TYPES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of inventory types", notes = "Returns list of inventory types", response = InventoryTypeResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = InventoryTypeResponse.class) })
	public List<InventoryTypeResponse> getInventoryType(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getInventoryType();
	}
	
	//user defined codes
	// on the basis of type code we can hit diff query and fetch required data
	@GetMapping(ILOVConstants.CODE_TYPES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of codes for the specific type codes", notes = "Returns list of codes for the specific type codes", response = CodeDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = CodeDetailsResponse.class) })
	public List<CodeDetailsResponse> getCodeDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Type Code", required = true) @RequestParam(name = "type_code") UserDefinedTypeCode typeCode){
		return lovService.getCodeDetails(typeCode.value());
	}
	
	@GetMapping(ILOVConstants.TARE_DETAILS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of tare details", notes = "Returns list of tare details", response = TareDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = TareDetailsResponse.class) })
	public List<TareDetailsResponse> getTareDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getTareDetails();
	}
	
	@GetMapping(ILOVConstants.REPORT_DETAILS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of report details", notes = "Returns list of report details", response = ReportCodeDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ReportCodeDetailsResponse.class) })
	public List<ReportCodeDetailsResponse> getReportCodeDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getReportCodeDetails();
	}
	
	@GetMapping(ILOVConstants.TENDER_CERTIFICATE_PROGRAMS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of tender programs", notes = "Returns list of tender programs", response = TenderCertificateInfoResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = TenderCertificateInfoResponse.class) })
	public List<TenderCertificateInfoResponse> getTenderPrograms(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getTenderPrograms();
	}
	
	@GetMapping(ILOVConstants.GLCATEGORIES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of GL Categories", notes = "Returns list of GL Categories", response = GLCategoryInfoResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = GLCategoryInfoResponse.class) })
	public List<GLCategoryInfoResponse> getGLCategoryDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getGLCategoryDetails();
	}
	
	@GetMapping(ILOVConstants.COMMISSION_CODES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of Commission Codes", notes = "Returns list of Commission Codes", response = CommissionCodeDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = CommissionCodeDetailsResponse.class) })
	public List<CommissionCodeDetailsResponse> getCommissionCodeDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getCommissionCodeDetails();
	}
	
	@GetMapping(ILOVConstants.LINK_CHARGES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of Link Charge Details", notes = "Returns list of Link Charge Details", response = LinkChargesDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = LinkChargesDetailsResponse.class) })
	public List<LinkChargesDetailsResponse> getLinkChargesDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getLinkChargesDetails();
	}
	
	@GetMapping(ILOVConstants.STATUS_DETAILS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of Status Details", notes = "Returns list of Status Details", response = StatusDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = StatusDetailsResponse.class) })
	public List<StatusDetailsResponse> getStatusDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Status Item", required = true) @RequestParam(name = "status_item") ItemStatusTypeCode statusItem,
			@ApiParam(value = "Flexible Item", required = true) @RequestParam(name = "flexible_item") boolean flexibleItem){
		return lovService.getStatusDetails(statusItem.value(), flexibleItem);
	}
	
	@GetMapping(ILOVConstants.ITEM_STATUS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of associated item status information", notes = "Returns list of associated item status information", response = StatusInfoDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = StatusInfoDetailsResponse.class) })
	public List<StatusInfoDetailsResponse> getStatusInfoDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Status Item", required = true) @RequestParam(name = "status_item") ItemStatusTypeCode statusItem,
			@ApiParam(value = "Status Id", required = true) @RequestParam(name = "status_id") String statusId){
		return lovService.getStatusInfoDetails(statusItem.value(), statusId);
	}
	
	@GetMapping(ILOVConstants.DEFAULT_DC)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of default dc details", notes = "Returns list of default dc details", response = DefaultDCDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = DefaultDCDetailsResponse.class) })
	public List<DefaultDCDetailsResponse> getDefaultDCDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getDefaultDCDetails();
	}
	
	@GetMapping(ILOVConstants.SUPPLIER_DETAILS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of supplier details", notes = "Returns list of supplier details", response = SupplierDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SupplierDetailsResponse.class) })
	public List<SupplierDetailsResponse> getSupplierDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getSupplierDetails();
	}
	
	@GetMapping(ILOVConstants.SITE_DETAILS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides all the sites available to the user.", notes = "Returns list of all the sites available to the user.", response = SiteDetailResponse.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SiteDetailResponse.class) })
	public List<SiteDetailResponse> getSiteDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return lovService.getSiteDetails();
	}
	
	@GetMapping(ILOVConstants.SITES_INVBYSIT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of sites on the basis of the sku number", notes = "Returns list of sites on the basis of the sku number", response = SiteDetailResponse.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SiteDetailResponse.class) })
	public List<SiteDetailResponse> getSitesForInvBySit(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU No", required = true) @PathVariable(name = "sku_no") int skuNo){
		return lovService.getSitesForInvBySit(skuNo);
	}
	
	// M / S / I 
	@GetMapping("/suppliersbytypecode")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of sites on the basis of the sku number", notes = "Returns list of sites on the basis of the sku number", response = VendorsResponse.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = VendorsResponse.class) })
	public List<VendorsResponse> getSuppliersByTypeCode(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Type Code", required = true) @RequestParam(name = "type_code") String typecode){
		return lovService.getSuppliersByTypeCode(typecode);
	}
	
	
}
