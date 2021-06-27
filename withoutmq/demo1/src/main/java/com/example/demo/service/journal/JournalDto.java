package com.example.demo.service.journal;

import com.example.demo.model.Journal;
import com.example.demo.model.user.UserActionType;
import lombok.Value;

import java.time.Instant;
import java.util.Map;

@Value
public class JournalDto {
    long id;
    long createDate;
    Map<String, Object> currentState;
    String login;
    UserActionType userActionType;

    public JournalDto(final Journal journal) {
        this.id = journal.getId();
        this.createDate = journal.getCreateDate().getEpochSecond();
        this.currentState = journal.getCurrentState();
        this.login = journal.getLogin();
        this.userActionType = journal.getUserActionType();
    }
}
