package com.example.userconsumer.service.command.impl;

import com.example.userconsumer.model.user.User;
import com.example.userconsumer.model.user.UserActionType;
import com.example.userconsumer.model.user.UserPost;
import com.example.userconsumer.model.user.UserRole;
import com.example.userconsumer.service.command.AbstractUserCommand;
import com.example.userconsumer.service.user.EmployeeDto;
import com.example.userconsumer.service.user.UserNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateCommand extends AbstractUserCommand {
    private final UserNameGenerator userNameGenerator;

    @Override
    public User execute(EmployeeDto dto) {
        User user = findById(dto.getId());
        user.setName(userNameGenerator.generate(dto.getFio()));
        user.setFullName(dto.getFio());
        user.setPost(UserPost.valueOf(dto.getPost()));
        user.setRole(UserRole.valueOf(dto.getRole()));
        return user;
    }

    @Override
    public UserActionType getAction() {
        return UserActionType.UPDATE;
    }
}
