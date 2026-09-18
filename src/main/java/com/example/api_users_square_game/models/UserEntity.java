package com.example.api_users_square_game.models;

import jakarta.persistence.*;


import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String roles;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }

    public User toUser() {
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setEmail(this.getEmail());
        user.setRoles(this.getRoles());

        return user;
    }

    public static UserEntity fromUser(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.id = user.getId();
        userEntity.username = user.getUsername();
        userEntity.password = user.getPassword();
        userEntity.email = user.getEmail();
        userEntity.roles = user.getRoles();

        return userEntity;
    }

}
