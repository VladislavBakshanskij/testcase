package com.example.testcase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Пользователь")
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @ApiModelProperty(value = "Уникальный идентификатор пользователя")
    private long userId;
    @ApiModelProperty(value = "Имя пользователя")
    private String userName;
    @ApiModelProperty(value = "Сгенерированный пароль")
    private String password;
}
