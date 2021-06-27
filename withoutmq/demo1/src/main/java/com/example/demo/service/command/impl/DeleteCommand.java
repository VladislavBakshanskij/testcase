package com.example.demo.service.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserActionType;
import com.example.demo.service.command.AbstractUserCommand;
import com.example.demo.service.user.EmployeeDto;

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
