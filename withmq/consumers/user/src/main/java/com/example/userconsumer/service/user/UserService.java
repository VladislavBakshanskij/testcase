package com.example.userconsumer.service.user;

import com.example.userconsumer.model.user.User;

public interface UserService {
    User resolve(EmployeeDto dto);
}
