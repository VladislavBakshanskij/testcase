package com.example.userconsumer.service.user.impl;

import com.example.userconsumer.service.user.UserNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "generator")
@PropertySource(value = "classpath:generator.properties", encoding = "UTF-8")
public class UserNameGeneratorImpl implements UserNameGenerator {
    @Value("${generator.separator}")
    private String separator;
    @Value("${generator.format}")
    private String format;

    @Override
    public String generate(String fullName) {
        String[] parts = fullName.split(separator);
        String lastName = parts[0];
        String firstLetterFirstName = parts[1].substring(0, 1);
        String firstLetterPatronymic = parts[2].substring(0, 1);
        return String.format(format, lastName, firstLetterFirstName, firstLetterPatronymic);
    }
}
