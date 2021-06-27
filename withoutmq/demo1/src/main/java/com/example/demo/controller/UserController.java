package com.example.demo.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.demo.controller.error.ErrorMessage;
import com.example.demo.service.user.EmployeeDto;
import com.example.demo.service.user.UserDto;
import com.example.demo.service.user.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api("${users}")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation("${users.get}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешно", response = UserDto.class,
                    examples = @Example({
                            @ExampleProperty(mediaType = "application/json", value = "{\n" +
                                    "  \"fio\": \"HELLO HELLO HELLO\",\n" +
                                    "  \"id\": null,\n" +
                                    "  \"post\": \"HR\",\n" +
                                    "  \"role\": \"USER\",\n" +
                                    "  \"type\": 1\n" +
                                    "}"),
                            @ExampleProperty(mediaType = "application/json", value = "{\n" +
                                    "  \"fio\": \"WWW WWW WW\",\n" +
                                    "  \"id\": 1,\n" +
                                    "  \"post\": \"HR\",\n" +
                                    "  \"role\": \"ADMIN\",\n" +
                                    "  \"type\": 2\n" +
                                    "}"),
                            @ExampleProperty(mediaType = "application/json", value = "{\n" +
                                    "  \"fio\": \"WWW WWW WW\",\n" +
                                    "  \"id\": 2,\n" +
                                    "  \"post\": \"HR\",\n" +
                                    "  \"role\": \"ADMIN\",\n" +
                                    "  \"type\": 3\n" +
                                    "}")
                    })
            ),
            @ApiResponse(code = 400, message = "Неизвестнй тип действия", response = ErrorMessage.class,
                    examples = @Example({
                            @ExampleProperty(mediaType = "application/json", value = "{\n" +
                                    "  \"fio\": \"HELLO HELLO HELLO\",\n" +
                                    "  \"id\": null,\n" +
                                    "  \"post\": \"HR\",\n" +
                                    "  \"role\": \"USER\",\n" +
                                    "  \"type\": 1321\n" +
                                    "}")
                    })),
            @ApiResponse(code = 404, message = "Пользователь не найден", response = ErrorMessage.class,
                    examples = @Example({
                            @ExampleProperty(mediaType = "application/json", value = "{\n" +
                                    "  \"fio\": \"HELLO HELLO HELLO\",\n" +
                                    "  \"id\": 124214,\n" +
                                    "  \"post\": \"HR\",\n" +
                                    "  \"role\": \"USER\",\n" +
                                    "  \"type\": 2\n" +
                                    "}")
                    }))
    })
    @PostMapping
    public UserDto execute(@RequestBody @Valid EmployeeDto dto) {
        return userService.resolve(dto);
    }
}
