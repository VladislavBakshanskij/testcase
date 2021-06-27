package com.example.userconsumer.listener;

import com.example.userconsumer.configuration.AmqpConfiguration;
import com.example.userconsumer.dto.JournalCreateDto;
import com.example.userconsumer.model.user.User;
import com.example.userconsumer.model.user.UserActionType;
import com.example.userconsumer.service.user.EmployeeDto;
import com.example.userconsumer.service.user.UserDto;
import com.example.userconsumer.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserQueueListener {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;

    @RabbitListener(queues = "user_input")
    public void listen(final EmployeeDto dto) {
        try {
            final UserActionType actionType = UserActionType.getById(dto.getType());
            final User user = userService.resolve(dto);
            rabbitTemplate.convertAndSend(AmqpConfiguration.USER_OUTPUT.getQueueName(), new UserDto(user));
            rabbitTemplate.convertAndSend(AmqpConfiguration.JOURNAL_CREATE.getExchangeName(),
                    AmqpConfiguration.JOURNAL_CREATE.getQueueName(),
                    new JournalCreateDto(user.getName(), actionType.name(),
                            objectMapper.convertValue(user, new TypeReference<>() {
                            })));
        } catch (Exception e) {
            rabbitTemplate.convertAndSend(AmqpConfiguration.ERROR.getExchangeName(),
                    AmqpConfiguration.ERROR.getQueueName(), e);
        }
    }
}
