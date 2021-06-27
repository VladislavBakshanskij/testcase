package com.example.userconsumer.service.command;

import com.example.userconsumer.model.user.User;
import com.example.userconsumer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractUserCommand implements Command {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User u) {
        return userRepository.save(u);
    }

    public User findById(long id) {
        return userRepository.findByIdOrThrow(id);
    }

    public void delete(User u) {
        userRepository.delete(u);
    }
}
