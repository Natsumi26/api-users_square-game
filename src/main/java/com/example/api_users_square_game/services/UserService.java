package com.example.api_users_square_game.services;


import com.example.api_users_square_game.models.User;

import java.util.Collection;
import java.util.UUID;

public interface UserService {

    Collection<User> getUsers();

    User getUserById(UUID id);

    User createUser(User user);

    void deleteUser(UUID id);

    boolean existById(UUID id);

}
