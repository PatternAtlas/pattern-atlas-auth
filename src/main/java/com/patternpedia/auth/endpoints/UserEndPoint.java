package com.patternpedia.auth.endpoints;


import com.patternpedia.auth.user.UserEntity;
import com.patternpedia.auth.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
public class UserEndPoint {

    private final UserRepository userRepository;

    public UserEndPoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user_info")
    @ResponseBody
    public Map<String, Object> userInfo(HttpServletRequest request) throws IOException, UsernameNotFoundException {
        String authorization = request.getHeader("Authorization");
        Map<String, Object> model = new HashMap<String, Object>();

        if (authorization != null && authorization.contains("Bearer")) {
            ObjectMapper objectMapper = new ObjectMapper();
            String tokenId = authorization.substring("Bearer".length() + 1);

            Jwt jwt = JwtHelper.decode(tokenId);
            Map<String, Object> claims = objectMapper.readValue(jwt.getClaims(), Map.class);
            String userId = (String) claims.get("user_id");
            log.info("userInfo for id: {}", userId);
            UserEntity user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new RuntimeException(userId));
            model.put("name", user.getName());
            model.put("mail", user.getMail());
            model.put("role", user.getRole());
            return model;
        }
        return model;
    }
}
