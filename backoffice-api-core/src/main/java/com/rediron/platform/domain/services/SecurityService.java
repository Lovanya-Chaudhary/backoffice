package com.rediron.platform.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.repositories.SecurityRepository;
import com.tomax.api.UserIdentity;


/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class SecurityService {
	
	Logger logger = LoggerFactory.getLogger(SecurityService.class);
	
	@Autowired
	private SecurityRepository securityRepository;
	
	public boolean isUserAllowed(UserIdentity domainUser, int siteNo, String configName) {
		return securityRepository.isUserAllowed(domainUser, siteNo, configName);
	}
	
	public boolean isItemAuthorised(UserIdentity domainUser, int skuNo, int siteNo) {
		return securityRepository.isItemAuthorised(domainUser, skuNo, siteNo);
	}

}
