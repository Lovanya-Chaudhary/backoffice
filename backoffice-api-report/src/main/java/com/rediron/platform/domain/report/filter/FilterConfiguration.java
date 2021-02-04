package com.rediron.platform.domain.report.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import com.rediron.security.common.config.SecurityConfig;


/**
 * @author lovanya.chaudhary
 *
 */
@Configuration
@Order(1000)
public class FilterConfiguration extends SecurityConfig {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Added request url for authentication api end point so that Security filter ignore security filter for these request mapping
        web.ignoring()
                .antMatchers("/authenticate/login")
                .antMatchers("/authenticate/logout");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //  Enables CORS Setting
        httpSecurity.cors();
    }
}
