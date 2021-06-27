package com.example.demo.controller;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import com.example.demo.test.TestUtils;
import com.example.demo.test.IntegrationTest;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class JournalControllerTest extends IntegrationTest {
    private static final String BASE_URL = "/journals";
    private static final String PATH_TEST_JSON = "json/" + JournalControllerTest.class.getSimpleName() + "/%s";

    @SuppressWarnings("unused")
    static Stream<Arguments> allSuccessArgs() {
        return Stream.of(
                Arguments.arguments(buildJson("createSuccessTest/all.json"), HttpStatus.OK.value())
        );
    }

    @ParameterizedTest
    @MethodSource("allSuccessArgs")
    @Sql(scripts = {
            "classpath:db/JournalControllerTest/data.sql"
    })
    @SneakyThrows
    void allSuccessTest(final String response, int status) {
        mvc.perform(TestUtils
                .createGet(BASE_URL))
                .andDo(print())
                .andExpect(status().is(status))
                .andExpect(content().json(response, true));
    }

    private static String buildJson(String resource, Object... args) {
        String template = TestUtils.readClassPathResourceAsString(String.format(PATH_TEST_JSON, resource));
        return String.format(template, args);
    }
}
