package com.rediron.platform.domain;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.rediron.platform.*")
@EntityScan("com.rediron.platform.domain.entities")
@EnableJpaRepositories("com.rediron.platform.domain.repositories")
public class RnetDomainReportApiApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(RnetDomainReportApiApplication.class);

    private static final String CONFIG_PATH = "/etc/tomax/hq.conf";
    private static File configFile = null;

    //@Bean
    private static Properties readExternalConfig() {
        return createConfig(loadExternalConfig());
    }

    private static Properties loadExternalConfig() {
        Properties configProperties = new Properties();
        configFile = new File(CONFIG_PATH);
        try {
            configProperties.load(new BufferedInputStream(new FileInputStream(configFile)));
        } catch (Exception e) {
            LOGGER.error("Could not load hq.conf file", e);
        }
        return configProperties;
    }

    private static Properties createConfig(Properties configProperties) {
        //Create properties for later use
        Properties properties = new Properties();
        //Read Value from external Config File
        String permittedSites = configProperties.getProperty("PERMITTED_SITES");
        //Set each required Value to properties File
        properties.put("cors.permitted.sites", permittedSites);
        return properties;
    }

    public static void main(String[] args) {
        //SpringApplication.run(RnetDomainApiApplication.class, args);
        SpringApplication application = new SpringApplication(RnetDomainReportApiApplication.class);
        //Read external Configuration & set these value to Spring context for later use through @Value annotation.
        //TODO - This approch can be used later for externalizing all environment specfic configuration to external config
        // application.setDefaultProperties(readExternalConfig());
        application.run(args);
    }
}
