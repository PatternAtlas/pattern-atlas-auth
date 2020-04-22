//package com.patternpedia.auth.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers()
//                .antMatchers("/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user_info").authenticated()
//                .anyRequest().permitAll();
//
////                .requestMatchers()
//////                .antMatchers(HttpMethod.DELETE, "/oauth/token")
//////                .antMatchers("/login", "/oauth/authorize", "/create")
////                .antMatchers("/login", "/oauth/authorize")
////                authorizeRequests()
////                .antMatchers("/userinfo").authenticated();
////                .antMatchers("/**").permitAll()
////                .and()
////                .authorizeRequests()
//
//
//    }
//}