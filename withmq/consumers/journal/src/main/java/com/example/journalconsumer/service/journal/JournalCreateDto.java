package com.example.journalconsumer.service.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class JournalCreateDto {
    private String login;
    private String action;
    private Map<String, Object> currentState;
}
