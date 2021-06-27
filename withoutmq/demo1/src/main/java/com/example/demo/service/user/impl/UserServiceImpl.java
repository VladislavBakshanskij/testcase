package com.example.demo.service.user.impl;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserActionType;
import com.example.demo.service.audit.AuditService;
import com.example.demo.service.command.CommandResolver;
import com.example.demo.service.user.EmployeeDto;
import com.example.demo.service.user.UserDto;
import com.example.demo.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuditService auditService;
    private final CommandResolver commandResolver;

    @Override
    public UserDto resolve(EmployeeDto dto) {
        UserActionType actionType = UserActionType.getById(dto.getType());
        User user = commandResolver.resolveWithExecute(dto, actionType);
        auditService.save(user, actionType);
        return new UserDto(user);
    }
}
