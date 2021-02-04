package com.rediron.platform.domain.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.domain.constants.ISecurityConstants;

import io.swagger.annotations.Api;


/**
 * @author lovanya.chaudhary
 *
 */
@Api(basePath = ISecurityConstants.B_SECURITY, value = "RetailNet Back office B Security functions ", description = "RetailNet Back office B Security fucntions")
@RestController
@RequestMapping(ISecurityConstants.B_SECURITY)
public class SecurityController {
	
	Logger logger = LoggerFactory.getLogger(SecurityController.class);

}
