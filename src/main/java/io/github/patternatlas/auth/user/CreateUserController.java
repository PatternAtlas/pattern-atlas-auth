package io.github.patternatlas.auth.user;

import io.github.patternatlas.auth.user.entities.RoleConstant;
import io.github.patternatlas.auth.user.entities.UserEntity;
import io.github.patternatlas.auth.user.repositories.RoleRepository;
import io.github.patternatlas.auth.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@Controller
public class CreateUserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserController(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder

            ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/create")
    public final String create(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        log.info("create user");
        UserEntity user = new UserEntity(name, email, passwordEncoder.encode(password), Arrays.asList(this.roleRepository.findByName(RoleConstant.MEMBER)));
        userRepository.save(user);
        return "redirect:/login";

    }
}

