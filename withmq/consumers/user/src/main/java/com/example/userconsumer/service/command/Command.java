package com.example.userconsumer.service.command;

import com.example.userconsumer.model.user.User;
import com.example.userconsumer.model.user.UserActionType;
import com.example.userconsumer.service.user.EmployeeDto;

public interface Command {
    User execute(EmployeeDto data);
    UserActionType getAction();
}
