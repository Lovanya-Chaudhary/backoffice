package com.rediron.platform.domain.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rediron.security.common.exceptions.InvalidToken;
import com.rediron.security.common.service.SecurityCacheReadService;
import com.tomax.api.UserIdentity;

/**
 * @author lovanya.chaudhary
 *
 */

@Component
public class AuthUtil {
	
	Logger logger = LoggerFactory.getLogger(AuthUtil.class);
	
	@Autowired
	public SecurityCacheReadService userIdCache;
	
	public UserIdentity getDomainUser(String udToken) {
		UserIdentity domainUser = null;
		try {
			if (userIdCache != null && userIdCache.getUDToken(udToken) != null) {
				String userId = userIdCache.getUDToken(udToken).getUserName();
				logger.debug("Found user id -> " + userId);
				domainUser = new UserIdentity(udToken, userId);
			} else {
				throw new InvalidToken("User Id not found!");
			}
		} catch (Exception e) {
			// nothing to do here.
		}
		return domainUser;
	}

}
