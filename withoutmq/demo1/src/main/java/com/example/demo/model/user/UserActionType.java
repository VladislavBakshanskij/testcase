package com.example.demo.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.demo.app.exception.ActionTypeNotFoundException;

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
