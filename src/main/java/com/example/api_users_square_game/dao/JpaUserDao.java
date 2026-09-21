package com.example.api_users_square_game.dao;

import com.example.api_users_square_game.models.User;
import com.example.api_users_square_game.entities.UserEntity;
import com.example.api_users_square_game.entities.UserEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;


@Repository
public class JpaUserDao implements UserDao {

    private final UserEntityRepository userEntityRepository;

    public JpaUserDao(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    @Override
    public Collection<User> findAll() {
        return userEntityRepository.findAll()
                .stream()
                .map(UserEntity::toUser)
                .toList();
    }

    @Override
    public User findById(UUID id) {
        return userEntityRepository.findById(id)
                .map(UserEntity::toUser)
                .orElse(null);
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserEntity.fromUser(user);
        UserEntity savedEntity = userEntityRepository.save(userEntity);

        return savedEntity.toUser();
    }

    @Override
    public void deleteUser(UUID id) {
        userEntityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return userEntityRepository.existsById(id);
    }

    @Override
    public User findByUsername(String username) {
        UserEntity userEntity = userEntityRepository.findByUsername(username);

        if(userEntity == null){
            return null;
        }

        return userEntity.toUser();
    }


}
