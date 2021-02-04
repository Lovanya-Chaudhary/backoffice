package com.rediron.platform.domain.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.domain.constants.ISiteConstants;
import com.rediron.platform.domain.model.response.GroupsAllResponse;
import com.rediron.platform.domain.model.response.SiteDetailResponse;
import com.rediron.platform.domain.model.response.SiteResponse;
import com.rediron.platform.domain.services.SiteService;
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

@Api(basePath = ISiteConstants.SITE, value = "RetailNet Back office Site Api", description = "RetailNet Back office Site Api")
@RestController
@RequestMapping(ISiteConstants.SITE)
public class SiteController {

	Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private SiteService siteService;

	@Autowired
	private AuthUtil authUtil;

	@GetMapping(value = ISiteConstants.SITE_GROUP)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Search site group by site group id", notes = "Returns site group by site group id", response = GroupsAllResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = GroupsAllResponse.class) })
	public GroupsAllResponse getGroup(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Group Id", required = true) @RequestParam(name = "site_group_id") String siteGroupId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return siteService.getSiteGroup(domainUser, siteGroupId);
	}

	// current site
//	@PreAuthorize("hasPermission(0, 'ImsActivityGroup','read')")
	@GetMapping(value = ISiteConstants.CURRENT_SITE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides current site nummber", notes = "Returns current site number of the logged in user.", response = Integer.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer getCurrentSite(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {

		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return siteService.getCurrentSite(domainUser);
	}

	// site
	@GetMapping(value = ISiteConstants.SITE_DETAILS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get site details of the provided site number", notes = "Returns site details of the provided site number", response = SiteResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SiteResponse.class) })
	public SiteResponse getSiteDeetails(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Number", required = true) @PathVariable(name = "site_no") int siteNumber)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return siteService.getSiteDetails(domainUser, siteNumber);
	}

	@GetMapping(value = ISiteConstants.ALL_SITE_GROUPS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all site groups", notes = "Returns list of all site groups", response = GroupsAllResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = GroupsAllResponse.class) })
	public List<GroupsAllResponse> getAllSiteGroups(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return siteService.getAllSiteGroups(domainUser);
	}

	@GetMapping(value = ISiteConstants.ALL_SITES)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all sites", notes = "Returns list of all sites", response = SiteDetailResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SiteDetailResponse.class) })
	public List<SiteDetailResponse> getAllSites(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
			throws InvalidToken, ExternalSecurityException {
//		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return siteService.getAllSites();
	}

	@GetMapping(value = ISiteConstants.SITES_BY_SITEGROUPID)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get list of all sites for the site group id", notes = "Returns list of all sites for the site group id", response = SiteResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = SiteResponse.class) })
	public List<SiteResponse> getSitesInfoForGroup(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Site Group Id", required = true) @RequestParam(name = "site_group_id") String siteGroupId)
			throws InvalidToken, ExternalSecurityException {
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		return siteService.getSitesInfoForGroup(domainUser, siteGroupId);
	}

}
