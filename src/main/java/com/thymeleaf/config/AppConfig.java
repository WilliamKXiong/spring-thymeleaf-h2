package com.thymeleaf.config;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(
                value = "classpath:/local.properties",
                ignoreResourceNotFound = true
        )
})
public class AppConfig {
    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    @Value("${DATASOURCE_URL}")
    private String datasourceUrl;

    @Value("${DATASOURCE_USERNAME}")
    private String datasourceUsername;

    @Value("${DATASOURCE_PASSWORD}")
    private String datasourcePassword;

    @PostConstruct
    public void init() {
        LOG.info("Reading Environment Variables...");
        LOG.info("DATASOURCE_URL: " + (StringUtils.isNotBlank(datasourceUrl) ? "[SET]" : "[NOT SET]"));
        LOG.info("DATASOURCE_USERNAME: " + (StringUtils.isNotBlank(datasourceUsername) ? "[SET]" : "[NOT SET]"));
        LOG.info("DATASOURCE_PASSWORD: " + (StringUtils.isNotBlank(datasourcePassword) ? "[SET]" : "[NOT SET]"));
    }
}
