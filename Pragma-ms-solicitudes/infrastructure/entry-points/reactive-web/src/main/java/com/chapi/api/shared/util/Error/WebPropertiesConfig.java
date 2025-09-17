package com.chapi.api.shared.util.Error;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides WebProperties.Resources bean required by GlobalErrorWebExceptionHandler
 */
@Configuration
public class WebPropertiesConfig {

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
}
