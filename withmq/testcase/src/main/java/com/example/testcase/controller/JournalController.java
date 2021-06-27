package com.example.testcase.controller;

import com.example.testcase.app.PagedData;
import com.example.testcase.app.SearchInfo;
import com.example.testcase.configuration.AmqpConfiguration;
import com.example.testcase.dto.JournalDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journals")
@RequiredArgsConstructor
public class JournalController {
    private final RabbitTemplate rabbitTemplate;

    @ApiOperation("${journals.all}")
    @ApiResponse(code = 200, message = "Успешно", response = PagedData.class)
    @SneakyThrows
    @GetMapping
    public PagedData<JournalDto> all(@RequestParam(name = "limit", defaultValue = "100") int limit,
                                     @RequestParam(name = "offset", defaultValue = "0") int offset) {
        rabbitTemplate.convertAndSend(AmqpConfiguration.JOURNAL_INPUT.getExchangeName(), AmqpConfiguration.JOURNAL_INPUT.getQueueName(),
                new SearchInfo(limit, offset));
        final PagedData<JournalDto> result = rabbitTemplate.receiveAndConvert(AmqpConfiguration.JOURNAL_OUTPUT.getQueueName(), 3000,
                new ParameterizedTypeReference<>() {});
        if (result == null) {
            throw rabbitTemplate.receiveAndConvert(AmqpConfiguration.ERROR.getQueueName(),
                    new ParameterizedTypeReference<Exception>() {});
        }
        return result;
    }
}
