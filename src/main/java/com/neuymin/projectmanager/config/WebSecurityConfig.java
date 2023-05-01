package com.neuymin.projectmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/projects/").permitAll()
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/projects/new").hasRole("ADMIN")
                        .requestMatchers("/projects/*/new").hasRole("ADMIN")
                        .requestMatchers("/projects/edit/*").hasRole("ADMIN")
                        .requestMatchers("/projects/delete/*").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .csrf().disable()
        .headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager userDetailsService =
                new InMemoryUserDetailsManager();

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN", "USER ")
                        .build();

        userDetailsService.createUser(user);
        userDetailsService.createUser(admin);

        return userDetailsService;
    }

}
