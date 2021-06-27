package com.example.userconsumer.service.command.impl;

import com.example.userconsumer.model.user.User;
import com.example.userconsumer.model.user.UserActionType;
import com.example.userconsumer.service.command.AbstractUserCommand;
import com.example.userconsumer.service.user.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteCommand extends AbstractUserCommand {
    @Override
    public User execute(EmployeeDto dto) {
        User user = findById(dto.getId());
        delete(user);
        return user;
    }

    @Override
    public UserActionType getAction() {
        return UserActionType.DELETE;
    }
}
