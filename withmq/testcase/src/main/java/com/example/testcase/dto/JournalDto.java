package com.example.testcase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class JournalDto {
    private long id;
    private long createDate;
    private Map<String, Object> currentState;
    private String login;
    private String userActionType;
}
