package io.github.patternatlas.auth.security;

import io.github.patternatlas.auth.user.entities.Privilege;
import io.github.patternatlas.auth.user.entities.Role;
import io.github.patternatlas.auth.user.entities.UserEntity;
import io.github.patternatlas.auth.user.repositories.RoleRepository;
import io.github.patternatlas.auth.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Transactional
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(
            UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user;
        try{
            logger.info("Refresh token login");
            UUID uuid = UUID.fromString(username);
            user = userRepository.findById(uuid).orElseThrow(() -> new UsernameNotFoundException("User with id not found: " + username));
            //do something
        } catch (IllegalArgumentException exception){
            logger.info("E-mail login");
            user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        }
        return new org.springframework.security.core.userdetails.User(user.getId().toString(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            for (Privilege privilege : role.getPrivileges()) {
                auths.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        return auths;
    }
}
