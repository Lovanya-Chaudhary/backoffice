package com.rediron.platform.domain.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.domain.model.request.ConfigReqrdFieldsRequest;
import com.rediron.platform.domain.model.response.ConfigReqrdFieldResponse;
import com.rediron.platform.domain.services.ConfigService;
import com.rediron.platform.domain.util.AuthUtil;
import com.rediron.security.common.ISecurity;
import com.tomax.api.UserIdentity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(basePath = "/v1/config", value = "RetailNet Back office Config API ", description = "RetailNet Back office Config Api")
@RestController
@RequestMapping("/v1/config")
public class ConfigController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

	@Autowired
	private AuthUtil authUtil;
	
	@Autowired
	private ConfigService configService;
	
	@GetMapping("/search/config")
	@ApiOperation(value = "Find required fields in inventory form", notes = "Returns required fields in inventory form", response = ConfigReqrdFieldResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ConfigReqrdFieldResponse.class) })
	public List<ConfigReqrdFieldResponse> getConfiguration(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		LOGGER.info("=== Fetching required configuration fields ===");
		return configService.getConfiguration(domainUser);
	}
	
	@PutMapping("/updateconfig")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update required fields in inventory form", notes = "Returns required fields in inventory form", response = ConfigReqrdFieldResponse.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = ConfigReqrdFieldResponse.class) })
	public List<ConfigReqrdFieldResponse> updateConfiguration(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Config Reqrd Fields Request Model", required = true) @Valid @RequestBody ConfigReqrdFieldsRequest request){
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		LOGGER.info("=== Updating required configuration fields ===");
		return configService.updateConfiguration(domainUser, request);
	}
	
	
}
