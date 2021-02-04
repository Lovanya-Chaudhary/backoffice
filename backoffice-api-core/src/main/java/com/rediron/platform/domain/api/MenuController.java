package com.rediron.platform.domain.api;

import java.util.HashMap;
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

import com.rediron.platform.domain.model.request.MenuItem;
import com.rediron.platform.domain.services.MenuService;
import com.rediron.platform.domain.util.AuthUtil;
import com.rediron.security.common.ISecurity;
import com.tomax.api.UserIdentity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(basePath = "/v1/menu", value = "RetailNet Back office Menu functions ", description = "RetailNet Back office Menu fucntions")
@RestController
@RequestMapping("/v1/menu")
public class MenuController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private AuthUtil authUtil;
	
	// simplified menu display proc
	@GetMapping("/display/{site_no}/{menu_seq}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides menu hierarchy for the logged in user", notes = "Returns menu hierarchy for the logged in user", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = List.class) })
	public List<HashMap<String,String>> getdisplayMenu(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site No", required = true) @PathVariable("site_no") int siteNo,
			@ApiParam(value = "Menu Seq", required = true) @PathVariable("menu_seq") int menuSeq,
			@ApiParam(value = "User Name", required = true) @RequestParam("user_name") String userName) throws Exception{
		// userName param will be removed from controller params after testing phase 
		LOGGER.info("Inside Menu Controller");
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return menuService.getdisplayMenu(domainUser, siteNo, menuSeq, userName);
	}

}
