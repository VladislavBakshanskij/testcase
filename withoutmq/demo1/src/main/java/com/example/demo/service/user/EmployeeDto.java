package com.example.demo.service.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Объект для действия над пользователем")
@Data
public class EmployeeDto {
    @ApiModelProperty(value = "Идентификатор пользователя")
    private Long id;
    @ApiModelProperty(value = "Дейтсвие которое будет просходить над пользователем", required = true)
    @Range(min = 1, max = 3)
    private short type = 1;
    @ApiModelProperty(value = "Роль пользователя", required = true)
    @NotBlank
    private String role;
    @ApiModelProperty(value = "Фамилия, имя и отчество пользователя", required = true)
    @NotBlank
    private String fio;
    @ApiModelProperty(value = "Долэность пользователя", required = true)
    @NotBlank
    private String post;
}
