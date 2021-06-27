package com.example.demo.service.command;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserActionType;
import com.example.demo.service.user.EmployeeDto;

public interface CommandResolver {
    User resolveWithExecute(EmployeeDto data, UserActionType actionType);
}
