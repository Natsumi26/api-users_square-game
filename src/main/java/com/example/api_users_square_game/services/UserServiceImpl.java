package com.example.api_users_square_game.services;

import com.example.api_users_square_game.dao.UserDao;

import com.example.api_users_square_game.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Collection<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return userDao.findById(id);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        return userDao.createUser(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userDao.deleteUser(id);
    }

    @Override
    public boolean existById(UUID id) {
        return userDao.existsById(id);
    }

}
