package com.patternpedia.auth.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/signin")
public class CreateUserController {
    private final UserRepository repository;

    CreateUserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

//    private final PasswordEncoder passwordEncoder;

//    public SignInController(UserRepository repository, PasswordEncoder passwordEncoder) {
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @GetMapping(value = "/getAll")
    List<UserEntity> all() {
        return this.repository.findAll();
    }
//
//    @PostMapping
//    User signin(@RequestParam String email, @RequestParam String password) {
//        User u = new User(null, email, passwordEncoder.encode(password), User.Role.USER, 0D, null);
//        return repository.save(u);
//    }
//
//    @PostMapping("/validateEmail")
//    Boolean emailExists(@RequestParam String email) {
//        return repository.existsByEmail(email);
//    }

}

