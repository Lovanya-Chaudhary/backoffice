package com.rediron.platform.domain.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tomax.api.UserIdentity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {
	
	private static String jwtSecret;
	
	@Autowired 
	public JwtUtils(@Value("${jwt.secret}") String jwtSecret)
	{
		JwtUtils.jwtSecret = jwtSecret;	
	}
	
	static final long ONE_MINUTE_IN_MILLIS=60000;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @return the UserIdentity object extracted from specified token or null if a token is invalid.
     */
    public static UserIdentity parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(JwtUtils.jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            
            if( log.isDebugEnabled() ) 
            {
            	log.debug( body.toString() );
            }
            
            System.out.println( body.toString() );
            
            UserIdentity domainUser = new UserIdentity( (String)body.get("idmToken"), body.getSubject() );
            return domainUser;

        } catch ( JwtException | ClassCastException e ) {        	
        	log.error( e.getMessage() );
            throw e;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and IDM/UD token as an additional claim. These properties are taken from the specified
     * UserIdentity object. JWT Token validity is infinite.
     * 
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(UserIdentity domainUser) {
    	
    	long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
    	
        Claims claims = Jwts.claims()
        		.setSubject(domainUser.getUserName() )
        		.setIssuer("RAP")
        		.setIssuedAt(now)
        		.setAudience("backoffice");
        claims.put("idmToken", domainUser.getToken());

        long expMillis = nowMillis + (15 * ONE_MINUTE_IN_MILLIS); // set JWT to expire in 15 minutes.
        Date exp = new Date(expMillis);
       
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JwtUtils.jwtSecret)
                .setExpiration(exp)
                .compact();
    }
}
