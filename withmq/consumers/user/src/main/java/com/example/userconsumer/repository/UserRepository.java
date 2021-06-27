package com.example.userconsumer.repository;

import com.example.userconsumer.app.exception.ObjectNotFoundException;
import com.example.userconsumer.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrThrow(long id) {
        return findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(User.class.getSimpleName(), id));
    }
}
