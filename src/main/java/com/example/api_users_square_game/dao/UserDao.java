package com.example.api_users_square_game.dao;

import com.example.api_users_square_game.models.User;

import java.util.Collection;
import java.util.UUID;

public interface UserDao {

    Collection<User> findAll();

    User findById(UUID id);

    User createUser(User user);

    void deleteUser(UUID id);

    boolean existsById(UUID id);

    User findByUsername(String username);
}
