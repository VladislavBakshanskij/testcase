package com.example.userconsumer.service.user;

import com.example.userconsumer.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String userName;
    private String password;

    public UserDto(final User user) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.password = user.getPassword();
    }
}
