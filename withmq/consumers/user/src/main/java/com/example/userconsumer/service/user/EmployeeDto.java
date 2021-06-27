package com.example.userconsumer.service.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private short type = 1;
    private String role;
    private String fio;
    private String post;
}
