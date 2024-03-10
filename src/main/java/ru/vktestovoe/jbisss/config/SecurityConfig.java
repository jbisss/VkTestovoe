package ru.vktestovoe.jbisss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("posts").password("password").roles("POSTS").build());
        manager.createUser(users.username("albums").password("password").roles("ALBUMS").build());
        manager.createUser(users.username("users").password("password").roles("USERS").build());
        manager.createUser(users.username("admin").password("password").roles("ADMIN","POSTS", "ALBUMS", "USERS").build());
        return manager;
    }

    @Bean
    public SecurityFilterChain postsFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/posts/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .hasAnyRole("ADMIN", "POSTS"))
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public SecurityFilterChain albumsFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/albums/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .hasAnyRole("ADMIN", "ALBUMS"))
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public SecurityFilterChain usersFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/users/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .hasAnyRole("ADMIN", "USERS"))
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
