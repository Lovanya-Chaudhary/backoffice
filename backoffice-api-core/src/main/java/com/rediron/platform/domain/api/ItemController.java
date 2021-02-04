package com.rediron.platform.domain.api;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.domain.constants.IItemConstants;
import com.rediron.platform.domain.services.ItemService;
import com.rediron.platform.domain.todo.Demo;
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
@Api(basePath = IItemConstants.B_ITEM, value = "RetailNet Back office B Item functions ", description = "RetailNet Back office B Item fucntions")
@RestController
@RequestMapping(IItemConstants.B_ITEM)
public class ItemController {
	
	Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService bItemService; 
	
	@Autowired
	private AuthUtil authUtil;
	
	@GetMapping(IItemConstants.SEARCH_SKU)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides item id for the passed item type", notes = "Returns item id for the passed item type", response = BigDecimal.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = BigDecimal.class) })
	public BigDecimal getSiteFlag(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "sku_no") int skuNumber,
			@ApiParam(value = "Item Type", required = true) @RequestParam(name = "item_type") String itemType){
		return bItemService.getSkuNumber(skuNumber, itemType);
	}
	
	// for testing url mapping will be changed
	@GetMapping("/exec")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides item id for the passed item type", notes = "Returns item id for the passed item type", response = Map.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Map.class) })
	public Map<String, Object> executeProc(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site No", required = true) @RequestParam(name = "siteNo") int siteNo,
			@ApiParam(value = "Ad Id", required = true) @RequestParam(name = "adId") int adId,
			@ApiParam(value = "Ad Site No", required = true) @RequestParam(name = "adSiteNo") int adSiteNo,
			@ApiParam(value = "Sku No", required = true) @RequestParam(name = "skuNo") int skuNo){
		return bItemService.executeProc(siteNo, adId, adSiteNo, skuNo);
	}
	
	@PostMapping("/exectest")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides item id for the passed item type", notes = "Returns item id for the passed item type", response = Map.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Map.class) })
	public String executeTestProc(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Demo Model", required = true) @RequestBody Demo demo) throws Exception{
		return bItemService.processPromoDtl(demo.getList());
	}
	
	// for testing only url mapping will be changed
	@GetMapping("/handle")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Check the promotions for the newly generated sku", notes = "Returns boolean for the promotions of the newly generated sku", response = Boolean.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Boolean.class) })
	public boolean handlePromotions(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Dept No", required = true) @RequestParam(name = "deptNo") int deptNo,
			@ApiParam(value = "Class No", required = true) @RequestParam(name = "classNo") int classNo,
			@ApiParam(value = "Line No", required = true) @RequestParam(name = "lineNo") int lineNo,
			@ApiParam(value = "SKU No", required = true) @RequestParam(name = "skuNo") int skuNo){
		
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return bItemService.handlePromotions(domainUser, deptNo, classNo, lineNo, skuNo);
	}

}
