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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.domain.constants.ISalesConstants;
import com.rediron.platform.domain.services.SalesService;
import com.rediron.platform.domain.util.AuthUtil;
import com.rediron.security.common.ISecurity;
import com.rediron.security.common.exceptions.ExternalSecurityException;
import com.rediron.security.common.exceptions.InvalidToken;
import com.tomax.api.UserIdentity;
import com.tomax.salesHistory.dto.custom.SalesHistoryByPeriodDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(basePath = ISalesConstants.SALES, value = "RetailNet Back office Sales Api", description = "RetailNet Back office Sales Api")
@RestController
@RequestMapping(ISalesConstants.SALES)
public class SalesController {
	
	Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@Autowired
	private AuthUtil authUtil;
	
	@Autowired
	private SalesService salesService;
	
	@GetMapping("/history")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of sales history for SKU number", notes = "Returns list of sales history for SKU number", response = SalesHistoryByPeriodDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SalesHistoryByPeriodDTO.class) })
	public List<SalesHistoryByPeriodDTO> getSalesHistory(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "siteNumber") int siteNumber,
			@ApiParam(value = "SKU Number", required = true) @PathVariable(name = "skuNumber") int skuNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return salesService.getSalesHistory(domainUser, siteNumber, skuNumber);
	}

}
