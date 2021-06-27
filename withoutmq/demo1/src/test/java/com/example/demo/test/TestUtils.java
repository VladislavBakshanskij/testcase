package com.example.demo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static MockHttpServletRequestBuilder createGet(String url) {
        return MockMvcRequestBuilders.get(url, new Object[0]).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name());
    }

    public static MockHttpServletRequestBuilder createPost(String url) {
        return MockMvcRequestBuilders.post(url, new Object[0]).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name());
    }

    @SneakyThrows
    public static <T> T readOrThrow(String json, Class<T> clazz, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Throwable var4) {
            throw var4;
        }
    }

    @SneakyThrows
    public static byte[] readClassPathResourceAsByteArray(String path) {
        try {
            return Files.readAllBytes(Paths.get((new ClassPathResource(path)).getURI()));
        } catch (Throwable var2) {
            throw var2;
        }
    }

    public static String readClassPathResourceAsString(String path) {
        return StringUtils.toEncodedString(readClassPathResourceAsByteArray(path), StandardCharsets.UTF_8);
    }

    public static <T> T readClassPathResourceAsObject(String path, Class<T> clazz, ObjectMapper mapper) {
        String json = new String(readClassPathResourceAsByteArray(path));
        return readOrThrow(json, clazz, mapper);
    }

    public static String strMultiple(final String s, final int count) {
        return s + s.repeat(Math.max(0, count - 1));
    }
}
