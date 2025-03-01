package com.example.spring_boot.services;

import com.example.spring_boot.dto.UserRequest;
import com.example.spring_boot.repositories.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserManager {
    private final IUserRepository _userRepository;

    public UserManager(IUserRepository userRepository) {
        _userRepository = userRepository;
    }

    public void saveUser(UserRequest user){
        _userRepository.saveUser(user);
    }

    public boolean emailExists(String email) {
        return _userRepository.findByEmail(email).isPresent();
    }

}
