package com.example.spring_boot.services;

import com.example.spring_boot.dtos.UserRequest;
import com.example.spring_boot.models.UserModel;
import com.example.spring_boot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {
    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserRequest userRequest) {
        UserModel user = new UserModel();
        user.setEmail(userRequest.getEmail());
        user.setIpAddress(userRequest.getIpAddress());
        userRepository.save(user);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    public List<UserModel> getSubscribers() {
        return userRepository.findAll();
    }

}
