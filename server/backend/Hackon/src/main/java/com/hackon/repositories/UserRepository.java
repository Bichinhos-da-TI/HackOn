package com.hackon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackon.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
