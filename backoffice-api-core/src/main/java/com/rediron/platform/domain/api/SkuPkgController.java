package com.rediron.platform.domain.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rediron.platform.domain.constants.ISkuPkgConstants;

import io.swagger.annotations.Api;


/**
 * @author lovanya.chaudhary
 *
 */
@Api(basePath = ISkuPkgConstants.SKU, value = "RetailNet Back office SKU functions", description = "RetailNet Back office SKU functions")
@RestController
@RequestMapping(ISkuPkgConstants.SKU)
public class SkuPkgController {
	
	Logger logger = LoggerFactory.getLogger(SkuPkgController.class);

}
