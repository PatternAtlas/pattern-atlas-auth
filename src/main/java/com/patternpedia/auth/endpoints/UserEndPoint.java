package com.patternpedia.auth.endpoints;


import com.patternpedia.auth.user.UserEntity;
import com.patternpedia.auth.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class UserEndPoint {

    private final UserRepository userRepository;

    public UserEndPoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user_info")
    @ResponseBody
    public Map<String, Object> user(@AuthenticationPrincipal Principal principal) {
        if (principal != null) {
//            log.info(principal.toString());
            UserEntity user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("User not found: " + principal.getName()));
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("email", user.getEmail());
            model.put("name", user.getName());
            model.put("id", user.getId());
            model.put("roles", user.getRoles());
            return model;
        }
        return null;
    }
}
