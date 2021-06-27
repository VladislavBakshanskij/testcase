package com.example.userconsumer.service.user.impl;

import com.example.userconsumer.model.user.User;
import com.example.userconsumer.model.user.UserActionType;
import com.example.userconsumer.service.command.CommandResolver;
import com.example.userconsumer.service.user.EmployeeDto;
import com.example.userconsumer.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CommandResolver commandResolver;

    @Override
    public User resolve(EmployeeDto dto) {
        UserActionType actionType = UserActionType.getById(dto.getType());
        return commandResolver.resolveWithExecute(dto, actionType);
    }
}
