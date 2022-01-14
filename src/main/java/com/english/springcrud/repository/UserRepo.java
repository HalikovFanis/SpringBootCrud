package com.english.springcrud.repository;

import com.english.springcrud.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
