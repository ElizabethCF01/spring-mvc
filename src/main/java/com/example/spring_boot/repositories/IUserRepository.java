package com.example.spring_boot.repositories;

import com.example.spring_boot.dto.UserRequest;
import com.example.spring_boot.model.UserModel;

import java.util.Optional;

public interface IUserRepository {

    public Optional<UserModel> findByEmail(String email);
    public void saveUser(UserRequest user);

}
