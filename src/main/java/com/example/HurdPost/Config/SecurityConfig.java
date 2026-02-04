package com.example.HurdPost.Config;

import com.example.HurdPost.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Executable;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;
    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/adminPage").hasRole("ADMIN")

                    .requestMatchers("/auth/**", "/error", "/style/style.css" ).permitAll()

                    .requestMatchers("/auth/**", "/error", "/style/style.css").permitAll()

                    .anyRequest().hasAnyRole("ADMIN", "MODERATOR", "USER")
        ).formLogin(
                login -> login.loginPage("/auth/login").loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/posts", true)
                                .failureUrl("/auth/login?error")

        ).logout(
                logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/auth/login")
        );
        return http.build();

    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(getPasswordEncoder());

        return authenticationManagerBuilder.build();


    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
