package com.rediron.platform.domain.api;

import java.math.BigDecimal;

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

import com.rediron.platform.domain.constants.ITmxGblConstants;
import com.rediron.platform.domain.services.TmxGblService;
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
@Api(basePath = ITmxGblConstants.TMXGBL, value = "RetailNet Back office TMX Global functions", description = "RetailNet Back office TMX Global functions")
@RestController
@RequestMapping(ITmxGblConstants.TMXGBL)
public class TmxGblController {
	
	Logger logger = LoggerFactory.getLogger(TmxGblController.class);
	
	@Autowired
	private TmxGblService tmxGblService;
	
	@GetMapping(ITmxGblConstants.TMXGBL_FLAG)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides String value for the passed configuration parameter", notes = "Returns String value for the passed configuration parameter", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public String getFlag(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Config Name", required = true) @RequestParam(name = "config_name") String configName){
		return tmxGblService.getFlag(configName);
	}
	
	@GetMapping(ITmxGblConstants.TMXGBL_SITE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides String value for the passed configuration parameter and site number", notes = "Returns String value for the passed configuration parameter and site number", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public String getSiteFlag(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Config Name", required = true) @RequestParam(name = "config_name") String configName,
			@ApiParam(value = "Site No", required = true) @PathVariable(name = "site_no") int siteNo){
		return tmxGblService.getSiteFlag(configName, siteNo);
	}
	
	@GetMapping(ITmxGblConstants.TMXGBL_NUM)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides numeric value for the passed configuration parameter", notes = "Returns numeric value for the passed configuration parameter", response = BigDecimal.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = BigDecimal.class) })
	public BigDecimal getNum(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Config Name", required = true) @RequestParam(name = "config_name") String configName){
		return tmxGblService.getNum(configName);
	}
	
	@GetMapping(ITmxGblConstants.TMXGBL_TEXT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides value for the passed configuration parameter", notes = "Returns value for the passed configuration parameter", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	public String getText(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Config Name", required = true) @RequestParam(name = "config_name") String configName){
		return tmxGblService.getText(configName);
	}
	
	@GetMapping(ITmxGblConstants.HQSITE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Validates if current site is hq or not", notes = "Returns true if current site is hq else false.", response = Integer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer getHqSite(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return tmxGblService.getHqSite();
	}
	
	@GetMapping(ITmxGblConstants.CURRENT_SITE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Validates if current site is hq or not", notes = "Returns true if current site is hq else false.", response = Integer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer getCurrentSite(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return tmxGblService.getCurrentSite();
	}
	
	@GetMapping(ITmxGblConstants.OWNER_ID)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Provides owner id for the logged in site number", notes = "Returns owner id for the logged in site number", response = Integer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 200, message = "OK", response = Integer.class) })
	public Integer getOwnerId(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken){
		return tmxGblService.getOwnerId();
	}

}
