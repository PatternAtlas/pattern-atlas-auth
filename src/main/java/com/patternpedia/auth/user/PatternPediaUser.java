//package com.patternpedia.auth.user;
//
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Collection;
//import java.util.UUID;
//
//public class PatternPediaUser extends User {
//
//    private final UUID id;
//
//    public PatternPediaUser(String username, String password, Collection<? extends GrantedAuthority> authorities, UUID id) {
//        super(username, password, authorities);
//
//        this.id = id;
//    }
//
//
//    public UUID getId() {
//        return id;
//    }
//}
