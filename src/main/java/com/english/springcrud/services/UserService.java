package com.english.springcrud.services;


import com.english.springcrud.models.User;
import com.english.springcrud.models.enums.Role;
import com.english.springcrud.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepo.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Save new User with email: {}", email);
        userRepo.save(user);
        return true;
    }
}
