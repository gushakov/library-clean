package com.github.libraryclean.infrastructure.adapter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

/**
 * Configures web security for application with in-memory user details manager. Not used for integration
 * tests.
 */
@Configuration
@EnableWebSecurity
@Profile({"!test"})
public class SecurityConfig {

    @Bean
    public UserDetailsService inMemoryUserDetailsService() {

        // In-memory user details manager with our example user. Username must
        // correspond to the ID of an existing patron (in the database).

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("patron1")
                        .password("secret4p1")
                        .authorities(Set.of())
                        .build());

        return userDetailsManager;

    }

    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {

        // Standard Spring Security configuration for web security with a built-in form-based login.
        // All access requires authentication.

        http.authorizeRequests()
                .mvcMatchers("/error")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .permitAll()
        ;

        return http.build();
    }

}
