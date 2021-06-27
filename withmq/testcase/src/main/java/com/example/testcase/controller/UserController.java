package com.example.testcase.controller;

import com.example.testcase.configuration.AmqpConfiguration;
import com.example.testcase.controller.error.ErrorMessage;
import com.example.testcase.dto.EmployeeDto;
import com.example.testcase.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final RabbitTemplate rabbitTemplate;

    @ApiOperation("${users.get}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешно", response = UserDto.class),
            @ApiResponse(code = 400, message = "Неизвестнй тип действия", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Пользователь не найден", response = ErrorMessage.class)
    })
    @SneakyThrows
    @PostMapping
    public UserDto execute(@RequestBody @Valid EmployeeDto dto) {
        rabbitTemplate.convertAndSend(AmqpConfiguration.USER_INPUT.getExchangeName(), AmqpConfiguration.USER_INPUT.getQueueName(), dto);
        final UserDto result = rabbitTemplate.receiveAndConvert(AmqpConfiguration.USER_OUTPUT.getQueueName(),
                1500, new ParameterizedTypeReference<>() {});
        if (result == null) {
            throw rabbitTemplate.receiveAndConvert(AmqpConfiguration.ERROR.getQueueName(),
                    new ParameterizedTypeReference<Exception>() {});
        }
        return result;
    }
}
