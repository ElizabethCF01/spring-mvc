package com.example.spring_boot.repositories;

import com.example.spring_boot.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    boolean existsByEmail(String email);

}
