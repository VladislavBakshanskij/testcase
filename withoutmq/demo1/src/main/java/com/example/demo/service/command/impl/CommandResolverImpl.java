package com.example.demo.service.command.impl;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserActionType;
import com.example.demo.service.command.Command;
import com.example.demo.service.command.CommandResolver;
import com.example.demo.service.user.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CommandResolverImpl implements CommandResolver {
    private final Map<UserActionType, Command> userActionTypeCommandMap;

    public CommandResolverImpl(List<Command> commands) {
        this.userActionTypeCommandMap = commands.stream()
                .collect(Collectors.toMap(Command::getAction, Function.identity()));
    }

    @Override
    public User resolveWithExecute(EmployeeDto data, UserActionType actionType) {
        return Optional.ofNullable(userActionTypeCommandMap.get(actionType))
                .orElseThrow(UnsupportedOperationException::new)
                .execute(data);
    }
}
