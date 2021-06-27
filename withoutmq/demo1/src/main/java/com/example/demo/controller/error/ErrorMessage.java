package com.example.demo.controller.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "Сообщение об ошибке")
@Data
@Accessors(chain = true)
public class ErrorMessage {
    @ApiModelProperty(value = "Сообщение о произошедшей ошибке")
    private String message;
    @ApiModelProperty(value = "Ошибки, которые произошли")
    private Map<String, String> errors = new HashMap<>();
}
