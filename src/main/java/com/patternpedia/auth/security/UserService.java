package com.patternpedia.auth.security;

import com.patternpedia.auth.user.PatternPediaUser;
import com.patternpedia.auth.user.UserEntity;
import com.patternpedia.auth.user.UserRepository;
import com.patternpedia.auth.user.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@Transactional
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        UserEntity user = userRepository.findByMail(username).orElseThrow(() -> new RuntimeException("User not found: " + username));
        log.info(user.toString());
        List<SimpleGrantedAuthority> authorities = getAuthorities(user.getRole());
        log.info("{} has role of {}", username, authorities);
        return new PatternPediaUser(user.getMail(), user.getPassword(), authorities, user.getId());
//        return new org.springframework.security.core.userdetails.User(user.getId().toString(), user.getPassword(), authorities);
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<UserRole> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            log.info(roles.toString());
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }


}
