package com.example.demo.service.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserActionType;
import com.example.demo.model.user.UserPost;
import com.example.demo.model.user.UserRole;
import com.example.demo.service.command.AbstractUserCommand;
import com.example.demo.service.user.EmployeeDto;
import com.example.demo.service.user.UserNameGenerator;

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
