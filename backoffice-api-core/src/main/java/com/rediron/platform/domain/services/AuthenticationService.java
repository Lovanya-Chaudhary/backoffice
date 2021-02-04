package com.rediron.platform.domain.services;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.security.common.cache.UDToken;
import com.rediron.security.common.connector.ISecurityConnector;
import com.rediron.security.common.exceptions.InvalidCredential;
import com.rediron.security.common.exceptions.InvalidToken;
import com.rediron.security.common.repository.redis.UDTokenRepository;
import com.rediron.security.common.service.SecurityCacheReadService;
import com.tomax.api.UserIdentity;

/**
 * Created by Jai.Ruwari on 21-07-2020.
 */
@Service
public class AuthenticationService {
    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    @Autowired
    public SecurityCacheReadService userIdCache;
    @Autowired
    public ISecurityConnector idmConnector;
    @Autowired
    private UDTokenRepository udTokenRepository;

    /**
     * Authenticate user credential against IDM user account
     *
     * @param username
     * @param password
     * @param userDomain
     * @param clientDesc
     * @return
     */
    public String authenticate(String username, String password, String userDomain, String clientDesc) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new InvalidCredential("Invalid/Missing Credentials");
        }
        try {
            String udToken = idmConnector.login(username, userDomain, password, clientDesc);
            if (udToken != null) {
                String saTokenDesc = "SA-Admin -> domain: " + userDomain;
                UDToken udTokenObj = new UDToken(udToken, saTokenDesc, username);
                if (udTokenObj != null) {
                    //Store token to Cache
                    udTokenRepository.save(udTokenObj);
                }
                return udToken;
            } else {
                throw new InvalidCredential("Invalid/Missing Credentials");
            }
        } catch (InvalidCredential e) {
            throw new InvalidCredential(e.getMessage(), e);
        }
    }

    /**
     * Logout User against IDM account
     * @param udToken
     * @return boolean
     */
    public boolean logout(String udToken) {
        if (udToken != null) {
            idmConnector.logout(udToken);
            udTokenRepository.deleteById(udToken);
            return true;
        }
        return false;
    }

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


