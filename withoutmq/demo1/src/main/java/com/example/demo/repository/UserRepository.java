package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.app.exception.ObjectNotFoundException;
import com.example.demo.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrThrow(long id) {
        return findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(User.class.getSimpleName(), id));
    }
}
