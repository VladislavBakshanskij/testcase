package com.example.testcase.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.testcase.app.SwaggerProperties;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {
    @Bean
    @Autowired
    public Docket api(final SwaggerProperties swaggerProperties) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(swaggerProperties.getTitle(),
                        swaggerProperties.getDescription(),
                        swaggerProperties.getVersion(),
                        "...",
                        new Contact(swaggerProperties.getContactInfo().getName(),
                                swaggerProperties.getContactInfo().getUrl(),
                                swaggerProperties.getContactInfo().getEmail()),
                        swaggerProperties.getLicense().getName(),
                        swaggerProperties.getLicense().getUrl(),
                        Collections.emptyList()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.testcase.controller"))
                .paths(PathSelectors.any())
                .build()
                .enable(swaggerProperties.isEnabled());
    }
}
