package com.rediron.platform.domain.api;

import java.math.BigDecimal;
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

import com.rediron.platform.domain.constants.ILookUpConstants;
import com.rediron.platform.domain.model.response.DefaultDCDetailsResponse;
import com.rediron.platform.domain.response.SKULinkDetails;
import com.rediron.platform.domain.services.LookUpService;
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
@Api(basePath = ILookUpConstants.LOOK_UP, value = "RetailNet Back office Look Up Api", description = "RetailNet Back office Look Up Api")
@RestController
@RequestMapping(ILookUpConstants.LOOK_UP)
public class LookUpController {
	
	Logger logger = LoggerFactory.getLogger(LookUpController.class);
	
	@Autowired
	private LookUpService lookUpService;
	
	@GetMapping(ILookUpConstants.LINK_SKU)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides link sku details", notes = "Returns link sku details", response = SKULinkDetails.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SKULinkDetails.class) })
	public SKULinkDetails getSKULinkDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Link Sku Number", required = true) @RequestParam(value = "linkSkuNo") int linkSkuNo){

		return lookUpService.getSKULinkDetails(linkSkuNo);
	}
	
	@GetMapping(ILookUpConstants.MFGCODE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides link sku details", notes = "Returns link sku details", response = Integer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer getSKULinkDetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "MFG Code", required = true) @RequestParam(value = "mfgCode") String mfgCode){

		return lookUpService.getCount(mfgCode);
	}
	
	@GetMapping(ILookUpConstants.XREF)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides xref number from inventory", notes = "Returns xref number from inventory", response = Integer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public BigDecimal getXRef(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Sku Number", required = true) @RequestParam(value = "skuNo") int skuNo){

		return lookUpService.getXRef(skuNo);
	}
	
	@GetMapping(ILookUpConstants.PO_DETAIL)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides po dtl count number for the Default DC and site number", notes = "Returns po dtl count number for the Default DC and site number", response = Integer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer getPODetailCount(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Sku Number", required = true) @PathVariable(value = "sku_no") int skuNo,
			@ApiParam(value = "Site Number", required = true) @PathVariable(value = "site_no") int siteNo,
			@ApiParam(value = "DC SKU Level", required = true) @PathVariable(value = "default_dc") int defaultDc){
		return lookUpService.getPODetailCount(skuNo, siteNo, defaultDc);
	}
	
	@GetMapping("/xrefDetail/{sku_no}/{site_no}/{default_dc}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides po dtl count number for the Default DC and site number", notes = "Returns po dtl count number for the Default DC and site number", response = Integer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer getXrefDetailCount(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Sku Number", required = true) @PathVariable(value = "sku_no") int skuNo,
			@ApiParam(value = "Site Number", required = true) @PathVariable(value = "site_no") int siteNo,
			@ApiParam(value = "DC SKU Level", required = true) @PathVariable(value = "default_dc") int defaultDc){
		return lookUpService.getXrefDetailCount(skuNo, siteNo, defaultDc);
	}
	
	@GetMapping("/defaultSkuDclevel/{default_dc}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides po dtl count number for the Default DC and site number", notes = "Returns po dtl count number for the Default DC and site number", response = DefaultDCDetailsResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public List<DefaultDCDetailsResponse> getDefaultDCDetail(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "DC SKU Level", required = true) @PathVariable(value = "default_dc") int defaultDc){
		return lookUpService.getDefaultDCDetail(defaultDc);
	}

}
