package com.patternpedia.auth.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateUserController {
    private final UserRepository repository;

//    CreateUserController(UserRepository userRepository) {
//        this.repository = userRepository;
//    }

    private final PasswordEncoder passwordEncoder;

    public CreateUserController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/getAll")
    List<UserEntity> all() {
        return this.repository.findAll();
    }
//
    @PostMapping(value = "/signup")
    UserEntity signup(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        UserEntity user = new UserEntity(name, email, password);
        return repository.save(user);
    }
//
//    @PostMapping("/validateEmail")
//    Boolean emailExists(@RequestParam String email) {
//        return repository.existsByEmail(email);
//    }

}

