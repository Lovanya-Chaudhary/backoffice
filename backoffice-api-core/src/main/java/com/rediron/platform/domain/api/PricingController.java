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

import com.rediron.platform.domain.constants.IPricingConstants;
import com.rediron.platform.domain.services.PriceListService;
import com.rediron.platform.domain.util.AuthUtil;
import com.rediron.security.common.ISecurity;
import com.rediron.security.common.exceptions.ExternalSecurityException;
import com.rediron.security.common.exceptions.InvalidToken;
import com.tomax.api.UserIdentity;
import com.tomax.pricing.dto.PricechgDTO;
import com.tomax.pricing.dto.custom.CustomPriceListDtlLiteDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(basePath = IPricingConstants.PRICING, value = "RetailNet Back office Pricing", description = "RetailNet Back office Pricing")
@RestController
@RequestMapping(IPricingConstants.PRICING)
public class PricingController {
	
	Logger logger = LoggerFactory.getLogger(PricingController.class);
	
	@Autowired
	private AuthUtil authUtil;
	
	@Autowired
	private PriceListService priceListService;
	
	// Get Pricelist for PriceID
	@GetMapping(IPricingConstants.FIND_PRICELIST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find Price List Details", notes = "Returns a list of price list details.", response = CustomPriceListDtlLiteDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = CustomPriceListDtlLiteDTO.class),
			@ApiResponse(code = 501, message = IPricingConstants.PRICE_LIST_NOT_FOUND) })
	public List<CustomPriceListDtlLiteDTO> getPricelist(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Price List Id", required = true, defaultValue = "0") @RequestParam(value = IPricingConstants.PRICE_LIST_ID) Long priceListId,
			@ApiParam(value = "Price List Inactive", required = true) @RequestParam(value = IPricingConstants.PRICE_LIST_IS_INACTIVE) Boolean isInactive,
			@ApiParam(value = "Price List Type", required = true) @RequestParam(value = IPricingConstants.PRICE_LIST_TYPE) String priceListType,
			@ApiParam(value = "Site Group", required = true, defaultValue = "0") @RequestParam(value = IPricingConstants.SITE_GROUP) Integer siteGrp)
			throws InvalidToken, ExternalSecurityException {

		List<CustomPriceListDtlLiteDTO> priceListDtls = null;
			UserIdentity domainUser = authUtil.getDomainUser(userToken);
			priceListDtls = priceListService.getPriceListDtls(domainUser, priceListId, siteGrp, isInactive,
					priceListType);
		return priceListDtls;
	}
	
	@GetMapping(value = IPricingConstants.PRICE_CHNG)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search price change details", notes = "Returns price change details", response = PricechgDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = PricechgDTO.class) })
	public PricechgDTO getPricechg(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Promo Key Number", required = true) @PathVariable(name = "promoKeyNumber") long promoKeyNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return priceListService.getPricechg(domainUser, promoKeyNumber);
	}

}
