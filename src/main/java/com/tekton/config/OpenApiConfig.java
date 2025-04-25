package com.tekton.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    public static final String DOC_API_NAME = "Challenge Tekton Service";
    public static final String DOC_API_DESCRIPTION = "This is a challenge service for Tekton";
    public static final String DOC_API_VERSION = "1.0";

    @Bean
    public OpenAPI springDocOpenApi(){
        return new OpenAPI()
                .info(new Info().title(DOC_API_NAME)
                        .description(DOC_API_DESCRIPTION)
                        .version(DOC_API_VERSION));
    }
}
