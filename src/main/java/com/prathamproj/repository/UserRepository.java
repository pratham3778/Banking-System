package com.prathamproj.repository;

import com.prathamproj.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    
    Boolean existsByAccountNumber(String accountNumber);
   
    User findByAccountNumber(String accountNumber);


}
