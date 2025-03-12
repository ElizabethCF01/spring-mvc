package com.example.spring_boot.services;

import com.example.spring_boot.dtos.UserRequest;
import com.example.spring_boot.models.UserModel;
import com.example.spring_boot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {
    private final UserRepository _userRepository;

    public UserManager(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserRequest userRequest) {
        UserModel user = new UserModel();
        user.setEmail(userRequest.getEmail());
        user.setIpAddress(userRequest.getIpAddress());
        _userRepository.save(user);
    }

    public boolean emailExists(String email) {
        return _userRepository.existsByEmail(email);
    }
    public List<UserModel> getSubscribers() {
        return _userRepository.findAll();
    }

}
