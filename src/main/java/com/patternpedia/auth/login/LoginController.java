package com.patternpedia.auth.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/signup")
    String signup() {
        return "signup";
    }
}