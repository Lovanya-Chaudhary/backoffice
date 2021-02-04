package com.rediron.platform.domain.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.domain.constants.IDomainConstants;
import com.rediron.platform.domain.model.response.DomainRef;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.services.DomainService;
import com.rediron.platform.domain.util.AuthUtil;
import com.rediron.security.common.ISecurity;
import com.rediron.security.common.exceptions.ExternalSecurityException;
import com.rediron.security.common.exceptions.InvalidToken;
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
@Api(basePath = IDomainConstants.DOMAIN, value = "RetailNet Back office Domain Code Maintenance ", description = "RetailNet Back office Domain Code Maintenance")
@RestController
@RequestMapping(IDomainConstants.DOMAIN)
public class DomainController {
	
	Logger logger = LoggerFactory.getLogger(DomainController.class);
	
	@Autowired
	private AuthUtil authUtil;
	
	@Autowired
	private DomainService domainService;
	
	@GetMapping(IDomainConstants.DOMAIN_CODES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Domain Maintenance Information Records", notes = "Returns list of available domain maintenance code records available", response = DomainRefCode.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = DomainRefCode.class) })
	public List<DomainRefCode> getDomainEntities(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Description", required = true) @RequestParam String description)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return domainService.getDomainCodes(domainUser, description);
	}
	
	@GetMapping(IDomainConstants.DOMAIN_DESCRIPTION)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides list of domain descriptions available", notes = "Returns list of domain descriptions", response = DomainRef.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = DomainRef.class) })
	public List<DomainRef> getDomainRefInformation(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return domainService.getDomainRefInformation(domainUser);
	}

}
