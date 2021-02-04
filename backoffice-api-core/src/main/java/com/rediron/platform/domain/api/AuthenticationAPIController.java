package com.rediron.platform.domain.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.services.AuthenticationService;
import com.rediron.security.common.ISecurity;
import com.rediron.security.common.exceptions.ExternalSecurityException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Created by Jai.Ruwari on 21-07-2020.
 */
@Api(basePath = IRnetDomain.AUTHENTICATION_API, value = "RetailNet Authentication Api", description = "RetailNet Authentication Api")
@RestController
@RequestMapping(value = IRnetDomain.AUTHENTICATION_API)
public class AuthenticationAPIController {
    Logger logger = LoggerFactory.getLogger(AuthenticationAPIController.class);

    //TODO - Later this will be replaced by security-api .This is just for simplicity & Hack for CORS issue for Security API Call

    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private Errors errors;

    @PostMapping(value = IRnetDomain.AUTHENTICATE_URL)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Authenticate user against IDM User account",
            notes = "This login endpoint authenticates user against user account in "
                    + "IDM application with the given credentials. "
                    + "On successful authentication api returns a idm token (sessionId) in the response as a string.",
            response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Invalid Credentials/User does not have permission"),
            @ApiResponse(code = 200, message = "Authentication successful")})
    public String login(
            @ApiParam(value = "User's username", required = true) @RequestHeader(value = ISecurity.UD_USERNAME) String username,
            @ApiParam(value = "User's password", required = true) @RequestHeader(value = ISecurity.UD_PASSWORD) String password)
            throws ExternalSecurityException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new ServiceException(errors.getErrors().get("INVALID_CREDENTIALS"));
        }
        return authenticationService.authenticate(username, password, ISecurity.USER_DOAMIN_NAME, ISecurity.CLIENT_DESCRIPTION);
    }


    @PostMapping(value = IRnetDomain.LOGOUT_URL)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Logout user session from IDM application",
            notes = "This logout endpoint logout user session from IDM application.",
            response = Boolean.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = "Invalid token"),
            @ApiResponse(code = 200, message = "Logout successful")})
    public boolean logout(
            @ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken)
            throws ExternalSecurityException {
        if (StringUtils.isBlank(userToken)) {
            throw new IllegalArgumentException("Blank UdToken");
        }
        return authenticationService.logout(userToken);
    }

    @GetMapping(IRnetDomain.USER)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get logged-in user name from valid udToken",
            notes = "This api end point used to get logged-in user name from udToken.",
            response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
            @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
            @ApiResponse(code = 200, message = "OK", response = String.class)})
    public String getLoggedInUserName(@ApiParam(value = "User Token", required = true) @RequestHeader(value = ISecurity.UD_TOKEN) String userToken) {
        return authenticationService.getDomainUser(userToken).getUserName();
    }
}
