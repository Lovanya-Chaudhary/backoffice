package com.rediron.platform.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Jai.Ruwari on 10-08-2020.
 * Cross-Origin Resource Sharing (CORS) is a security concept that allows restricting the resources implemented in web browsers. It prevents the JavaScript code producing or consuming the requests against different origin.
 */
@Configuration
public class CORSConfiguration {

    //TODO - Should be configurable
    // @Value("${cors.permitted.sites:#{null}}")
    private String[] permittedSites;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  //Allow for every api end points
                        //.allowedOrigins("http://localhost:3000", "https://rediron-react-poc.netlify.app/")
                        .allowedOrigins("*")  //Allow for any origin
                        .allowedMethods("*")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }
}
