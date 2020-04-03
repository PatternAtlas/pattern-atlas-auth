package com.patternpedia.auth.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
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

    @PostMapping(value = "/create")
    public final String create(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        log.info("create user");
        UserEntity user = new UserEntity(name, email, passwordEncoder.encode(password));
        repository.save(user);
        return "redirect:/login";

    }
//
//    @PostMapping("/validateEmail")
//    Boolean emailExists(@RequestParam String email) {
//        return repository.existsByEmail(email);
//    }

}

