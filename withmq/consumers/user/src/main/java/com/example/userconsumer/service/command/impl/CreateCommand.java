package com.example.userconsumer.service.command.impl;

import com.example.userconsumer.model.user.User;
import com.example.userconsumer.model.user.UserActionType;
import com.example.userconsumer.model.user.UserPost;
import com.example.userconsumer.model.user.UserRole;
import com.example.userconsumer.service.command.AbstractUserCommand;
import com.example.userconsumer.service.user.EmployeeDto;
import com.example.userconsumer.service.user.UserNameGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateCommand extends AbstractUserCommand {
    private final UserNameGenerator userNameGenerator;

    @Override
    public User execute(EmployeeDto dto) {
        User user = new User()
                .setName(userNameGenerator.generate(dto.getFio()))
                .setFullName(dto.getFio())
                .setPost(UserPost.valueOf(dto.getPost()))
                .setRole(UserRole.valueOf(dto.getRole()))
                .setPassword(RandomStringUtils.randomAlphanumeric(8));
        return save(user);
    }

    @Override
    public UserActionType getAction() {
        return UserActionType.CREATE;
    }
}
