package com.example.demo.app;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "swagger")
@PropertySource(value = "classpath:swagger.properties", encoding = "UTF-8")
public class SwaggerProperties {
    private boolean enabled;
    private String title;
    private String description;
    private String version;
    private ContactInfo contactInfo;
    private License license;

    @Getter
    @Setter
    public static class ContactInfo {
        private String name;
        private String url;
        private String email;
    }

    @Getter
    @Setter
    public static class License {
        private String name;
        private String url;
    }
}
