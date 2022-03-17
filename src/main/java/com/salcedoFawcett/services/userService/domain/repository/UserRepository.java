package com.salcedoFawcett.services.userService.domain.repository;

import com.salcedoFawcett.services.userService.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();
    Optional<User> getUserById(int userId);
    User getUserByUsername(String username);
    User save(User user);
    boolean userExist(int userId);
    void delete(int userId);
}
