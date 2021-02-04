package com.rediron.platform.domain.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.model.response.SkuNumberResponse;
import com.rediron.platform.domain.rnet.CreateItemModel;
import com.rediron.platform.domain.services.CreateItemService;
import com.rediron.platform.domain.util.AuthUtil;
import com.rediron.security.common.ISecurity;
import com.tomax.api.UserIdentity;
import com.tomax.inventory.dto.custom.ItemCreationDefaultsInfoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(basePath = "/v1/createitem", value = "RetailNet Back office Create item with forms", description = "RetailNet Back office Create item with forms")
@RestController
@RequestMapping("/v1/createitem")
public class CreateItemController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CreateItemController.class);
	
	@Autowired
	private CreateItemService createItemService;
	
	@Autowired
	private AuthUtil authUtil;
	
	@Autowired
	private Errors errors;
	
	@PostMapping("/createsku")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Item", notes = "Returns the created item SKU number.", response = SkuNumberResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
			@ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
			@ApiResponse(code = 404, message = IRnetDomain.ITEM_TYPE_CD_NOT_FOUND),
			@ApiResponse(code = 500, message = IRnetDomain.ITEM_NOT_CREATED),
			@ApiResponse(code = 500, message = IRnetDomain.INVENTORY_EXCEPTION)})
	public SkuNumberResponse createItem(
			@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken,
			@ApiParam(value = "Create Item Default Type Code", required = true) @RequestParam(name = "item_type_code") String itemCreateDefaultTypeCode,
			@ApiParam(value = "Create Item Model", required = true) @Valid @RequestBody CreateItemModel itemCreateDefaultInfoModel) {
		LOGGER.info("=== Creating item using inventory vendor list methods ===");
		UserIdentity domainUser = authUtil.getDomainUser(userToken);
		ItemCreationDefaultsInfoDTO itemCreateInfoDTO = createItemService.getItemCreationDefaultsInfo(domainUser,
				itemCreateDefaultTypeCode);
		if (itemCreateInfoDTO == null) {
			throw new ServiceException(errors.getErrors().get("ITEM_TYPE_NOT_FOUND").formatMessage(itemCreateDefaultTypeCode));
		}
		int skuNumber = createItemService.createItemFromDefaults(domainUser, itemCreateInfoDTO, itemCreateDefaultInfoModel);
		return new SkuNumberResponse(skuNumber);
	}

}
