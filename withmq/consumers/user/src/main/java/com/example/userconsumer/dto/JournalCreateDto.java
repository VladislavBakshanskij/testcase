package com.example.userconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JournalCreateDto {
    private String login;
    private String action;
    private Map<String, Object> currentState;
}
