package com.example.spring_boot.repositories;

import com.example.spring_boot.dto.UserRequest;
import com.example.spring_boot.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    private final List<UserModel> users = new ArrayList<>();

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return users.stream()
                .filter(sub -> sub.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public void saveUser(UserRequest request) {
        var id = users.size() + 1;
        var user = new UserModel(id, request.email, request.ipAddress);
        users.add(user);

        System.out.println("User saved successfully");
        System.out.println(user);
    }
}
