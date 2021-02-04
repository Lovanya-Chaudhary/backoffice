package com.rediron.platform.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import com.rediron.security.common.config.SecurityConfig;

/**
 * Created by Jai.Ruwari on 05-08-2020.
 */
@Configuration
@Order(1000)
public class FilterConfiguration extends SecurityConfig {
	
	@Autowired
	private BOPermissionEvaluator permissionEvaluator;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Added request url for authentication api end point so that Security filter ignore security filter for these request mapping
        web.ignoring()
                .antMatchers("/backoffice/api/v1/authenticate/login")
                .antMatchers("/backoffice/api/v1/authenticate/logout")
                .antMatchers("/backoffice/api/v1/security/**")
                .antMatchers("/backoffice/api/v1/lookup/**")
                .antMatchers("/backoffice/api/v1/lov/**")
                .antMatchers("/backoffice/api/v1/sku/**")
                .antMatchers("/backoffice/api/v1/tmxglobal/**")
                .antMatchers("/backoffice/api/v1/item/**")
                .antMatchers("/backoffice/api/v1/vendor/**");
        
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        web.expressionHandler(handler);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //  Enables CORS Setting
        httpSecurity.cors();
    }
}
