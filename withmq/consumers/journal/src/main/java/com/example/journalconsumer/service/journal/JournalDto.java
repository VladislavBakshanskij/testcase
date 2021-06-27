package com.example.journalconsumer.service.journal;

import com.example.journalconsumer.model.Journal;
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

    public JournalDto(final Journal journal) {
        this.id = journal.getId();
        this.createDate = journal.getCreateDate().getEpochSecond();
        this.currentState = journal.getCurrentState();
        this.login = journal.getLogin();
        this.userActionType = journal.getUserActionType();
    }
}
