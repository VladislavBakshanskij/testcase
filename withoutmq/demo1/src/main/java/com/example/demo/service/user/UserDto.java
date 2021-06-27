package com.example.demo.service.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import com.example.demo.model.user.User;

@ApiModel(value = "Пользователь")
@Value
public class UserDto {
    @ApiModelProperty(value = "Уникальный идентификатор пользователя")
    long userId;
    @ApiModelProperty(value = "Имя пользователя")
    String userName;
    @ApiModelProperty(value = "Сгенерированный пароль")
    String password;

    public UserDto(final User user) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.password = user.getPassword();
    }
}
