package com.example.demo.controller;

import com.example.demo.model.user.UserPost;
import com.example.demo.model.user.UserRole;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import com.example.demo.test.IntegrationTest;
import com.example.demo.test.TestUtils;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class UserControllerTest extends IntegrationTest {
    private static final String PATH_TEST_JSON = "json/" + UserControllerTest.class.getSimpleName() + "/%s";
    private static final String BASE_URL = "/users";

    @SuppressWarnings("unused")
    static Stream<Arguments> createSuccessArgs() {
        final String pathTemplate = "createSuccessTest/%s";
        String fakeName = String.format("%s %s %s", TestUtils.strMultiple("n", 3),
                TestUtils.strMultiple("n", 3), TestUtils.strMultiple("n", 3));

        return Stream.of(
                Arguments.arguments(buildJson(String.format(pathTemplate, "full_request.json")),
                        buildJson(String.format(pathTemplate, "full_response_with_out_pass.json")), HttpStatus.OK.value()),
                Arguments.arguments(buildJson("default_request.template.json",
                        null, 1, fakeName, UserRole.USER.name(), UserPost.DIRECTOR.name()),
                        buildJson("default_response.template.json",
                                1, String.format("%s %s.%s.", TestUtils.strMultiple("n", 3), "n", "n")),
                        HttpStatus.OK.value())
        );
    }

    @ParameterizedTest
    @MethodSource("createSuccessArgs")
    @SneakyThrows
    void createSuccessTest(final String request, final String response, int status) {
        mvc.perform(TestUtils
                .createPost(BASE_URL)
                .content(request))
                .andDo(print())
                .andExpect(status().is(status))
                .andExpect(content().json(response, false));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createFailArgs() {
        String pathTemplate = "createFailTest/%s";
        return Stream.of(
                Arguments.arguments(buildJson(String.format(pathTemplate, "name_is_empty_request.json")),
                        buildJson(String.format(pathTemplate, "name_is_empty_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "name_is_null_request.json")),
                        buildJson(String.format(pathTemplate, "name_is_null_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "type_negative_request.json")),
                        buildJson(String.format(pathTemplate, "type_negative_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "role_is_empty_request.json")),
                        buildJson(String.format(pathTemplate, "role_is_empty_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "role_is_null_request.json")),
                        buildJson(String.format(pathTemplate, "role_is_null_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "post_is_empty_request.json")),
                        buildJson(String.format(pathTemplate, "post_is_empty_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "post_is_null_request.json")),
                        buildJson(String.format(pathTemplate, "post_is_null_response.json")), HttpStatus.BAD_REQUEST.value())
        );
    }

    @ParameterizedTest
    @MethodSource("createFailArgs")
    @SneakyThrows
    void createFailTest(final String request, final String response, int status) {
        mvc.perform(TestUtils
                .createPost(BASE_URL)
                .content(request))
                .andDo(print())
                .andExpect(status().is(status))
                .andExpect(content().json(response, true));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> updateSuccessArgs() {
        String pathTemplate = "updateSuccessTest/%s";
        return Stream.of(
                Arguments.arguments(buildJson(String.format(pathTemplate, "update_all_request.json")),
                        buildJson(String.format(pathTemplate, "update_all_response.json")), HttpStatus.OK.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "update_name_request.json")),
                        buildJson(String.format(pathTemplate, "update_name_response.json")), HttpStatus.OK.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "update_post_request.json")),
                        buildJson(String.format(pathTemplate, "update_post_response.json")), HttpStatus.OK.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "update_role_request.json")),
                        buildJson(String.format(pathTemplate, "update_role_response.json")), HttpStatus.OK.value())
        );
    }

    @ParameterizedTest
    @MethodSource("updateSuccessArgs")
    @Sql(scripts = {
            "classpath:db/UserControllerTest/data.sql"
    })
    @SneakyThrows
    void updateSuccessTest(final String request, final String response, int status) {
        mvc.perform(TestUtils
                .createPost(BASE_URL)
                .content(request))
                .andDo(print())
                .andExpect(status().is(status))
                .andExpect(content().json(response, false));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> updateFailArgs() {
        String pathTemplate = "updateFailTest/%s";
        return Stream.of(
                Arguments.arguments(buildJson(String.format(pathTemplate, "user_not_found_request.json")),
                        buildJson(String.format(pathTemplate, "user_not_found_response.json")), HttpStatus.NOT_FOUND.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "name_is_empty_request.json")),
                        buildJson(String.format(pathTemplate, "name_is_empty_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "name_is_null_request.json")),
                        buildJson(String.format(pathTemplate, "name_is_null_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "type_negative_request.json")),
                        buildJson(String.format(pathTemplate, "type_negative_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "role_is_empty_request.json")),
                        buildJson(String.format(pathTemplate, "role_is_empty_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "role_is_null_request.json")),
                        buildJson(String.format(pathTemplate, "role_is_null_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "post_is_empty_request.json")),
                        buildJson(String.format(pathTemplate, "post_is_empty_response.json")), HttpStatus.BAD_REQUEST.value()),
                Arguments.arguments(buildJson(String.format(pathTemplate, "post_is_null_request.json")),
                        buildJson(String.format(pathTemplate, "post_is_null_response.json")), HttpStatus.BAD_REQUEST.value())
        );
    }

    @ParameterizedTest
    @MethodSource("updateFailArgs")
    @Sql(scripts = {
            "classpath:db/UserControllerTest/data.sql"
    })
    @SneakyThrows
    void updateFailTest(final String request, final String response, int status) {
        mvc.perform(TestUtils
                .createPost(BASE_URL)
                .content(request))
                .andDo(print())
                .andExpect(status().is(status))
                .andExpect(content().json(response, false));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> deleteSuccessArgs() {
        String pathTemplate = "deleteSuccessTest/%s";
        return Stream.of(
                Arguments.arguments(buildJson(String.format(pathTemplate, "full_request.json")),
                        buildJson(String.format(pathTemplate, "full_response.json")), HttpStatus.OK.value())
        );
    }

    @ParameterizedTest
    @MethodSource("deleteSuccessArgs")
    @Sql(scripts = {
            "classpath:db/UserControllerTest/data.sql"
    })
    @SneakyThrows
    void deleteSuccessTest(final String request, final String response, int status) {
        mvc.perform(TestUtils
                .createPost(BASE_URL)
                .content(request))
                .andDo(print())
                .andExpect(status().is(status))
                .andExpect(content().json(response, false));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> deleteFailArgs() {
        String pathTemplate = "deleteFailTest/%s";
        return Stream.of(
                Arguments.arguments(buildJson(String.format(pathTemplate, "user_not_found_request.json")),
                        buildJson(String.format(pathTemplate, "user_not_found_response.json")), HttpStatus.NOT_FOUND.value())
        );
    }

    @ParameterizedTest
    @MethodSource("deleteFailArgs")
    @Sql(scripts = {
            "classpath:db/UserControllerTest/data.sql"
    })
    @SneakyThrows
    void deleteFailTest(final String request, final String response, int status) {
        mvc.perform(TestUtils
                .createPost(BASE_URL)
                .content(request))
                .andDo(print())
                .andExpect(status().is(status))
                .andExpect(content().json(response, false));
    }

    private static String buildJson(String resource, Object... args) {
        String template = TestUtils.readClassPathResourceAsString(String.format(PATH_TEST_JSON, resource));
        return String.format(template, args);
    }
}
