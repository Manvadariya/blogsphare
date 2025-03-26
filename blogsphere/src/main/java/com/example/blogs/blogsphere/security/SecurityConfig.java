package com.example.blogs.blogsphere.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        
        // Query to load user details from the "users" table.
        // Here, we assume all users are enabled (active).
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select username, password, true as enabled from users where username=?"
        );
        
        // Query to load authorities (roles) using a join between "users" and "roles"
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select u.username, r.name as authority " +
            "from users u join roles r on u.user_id = r.user_id " +
            "where u.username = ?"
        );
        
        // Use the role names exactly as stored (e.g., "admin", "author", "reader")
        jdbcUserDetailsManager.setRolePrefix("");
        
        return jdbcUserDetailsManager;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // Only users with "admin" authority can access /api/admin/**
                .requestMatchers("/api/admin/**").hasAuthority("admin")
                // Other endpoints are accessible by users with any of these authorities.
                .requestMatchers("/api/users/**", "/api/posts/**", "/api/comments/**", "/api/tags/**")
                    .hasAnyAuthority("admin", "author", "reader")
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults())
            .csrf(csrf -> csrf.disable());
        
        return http.build();
    }
}
