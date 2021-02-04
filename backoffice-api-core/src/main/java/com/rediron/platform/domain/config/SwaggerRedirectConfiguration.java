package com.rediron.platform.domain.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.security.common.ApiServerInfo;
import com.rediron.security.common.SwaggerRedirectService;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerRedirectConfiguration {
    Logger logger = LoggerFactory.getLogger(SwaggerRedirectConfiguration.class);

    @Autowired
    SwaggerRedirectService swaggerRedirectService;
    @Autowired
    Docket docket;
    @Autowired
    private ApiServerInfo apiServerInfo;

    @Value("${server.ssl.enabled:#{null}}")
    private Boolean sslEnabled;

    @Value("${swagger.url.schema.enabled:#{null}}")
    private Boolean isSchemaValueUsedInUrl;

    @PostConstruct
    public void init() {
        swaggerRedirectService.setRelative_url(IRnetDomain.BASE_PATH);

        //Update Swagger UI Host Name as per configuration
        docket.host(getHost());
        docket.apiInfo(apiInfo());
    }

    /**
     * Build API Host URL from config file parameters
     *  @return url
     */
    private String getHost() {
        String url = null;
        final String schema = (sslEnabled != null && sslEnabled) ? "https://" : "http://";
        if (isSchemaValueUsedInUrl) {
            //Full URL  (Schema://Host:port)
            url = schema + apiServerInfo.getApiHost() + ":" + apiServerInfo.getApiPort();
        } else {
            //Without Schema value in  URL  (Host:port)
            //Swagger UI also appended Schema on URL which leads double schema value in URL become wrong URL for  Swagger UI
            url = apiServerInfo.getApiHost() + ":" + apiServerInfo.getApiPort();
        }
        logger.debug("Swagger UI : API host URL: " + url);
        return (apiServerInfo != null) ? url : null;
    }

    /**
     * Create API  information
     * @return
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Retail.net API Platform",
                "Customer Facing Retail.net APIs",
                "1.0.0",
                "",
                "",
                "",
                "");
        return apiInfo;
    }

}