package com.example.demo.service.audit;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserActionType;

public interface AuditService {
    void save(User user, UserActionType userActionType);
}
