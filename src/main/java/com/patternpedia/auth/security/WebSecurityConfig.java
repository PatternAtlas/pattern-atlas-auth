package com.patternpedia.auth.security;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.filter.CorsFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Identify user
 */

@Configuration
@EnableWebSecurity
//@Order(1)
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private UserDetailsService userDetailsService;

    //    public WebSecurityConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
    public WebSecurityConfig(@Qualifier("userService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public FilterRegistrationBean customCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

        //IMPORTANT
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .formLogin().loginPage("/login").permitAll()
//                .successHandler(myAuthenticationSuccessHandler())
//                .formLogin().permitAll()
                .and()
                .requestMatchers()
//                .antMatchers(HttpMethod.DELETE, "/oauth/token")
//                .antMatchers("/login", "/oauth/authorize", "/create")
                .antMatchers("/login", "/oauth/authorize")
                .mvcMatchers("/.well-known/jwks.json")


                .and()
//                .authorizeRequests().antMatchers("/oauth/check_token").permitAll()
//                .and()
                .authorizeRequests()
                .mvcMatchers("/.well-known/jwks.json").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/oauth/token").permitAll()
                .anyRequest().authenticated();
//                .anyRequest().permitAll();


        //"/alive", "/userinfo"
    }
}