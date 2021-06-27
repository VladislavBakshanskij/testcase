package com.example.userconsumer.model.user;

import com.example.userconsumer.app.exception.ActionTypeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum UserActionType {
    CREATE(1),
    DELETE(2),
    UPDATE(3);

    private final int id;

    public static UserActionType getById(int id) {
        return Arrays.stream(values())
                .filter(actionType -> actionType.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ActionTypeNotFoundException(id));
    }
}
