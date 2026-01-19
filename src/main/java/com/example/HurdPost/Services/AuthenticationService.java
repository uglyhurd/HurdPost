package com.example.HurdPost.Services;

import com.example.HurdPost.Models.User;
import com.example.HurdPost.Repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepos userRepos;

    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepos userRepos) {
        this.passwordEncoder = passwordEncoder;
        this.userRepos = userRepos;
    }
    @Transactional
    public void createUser(User user){

        String passwordEncoded = passwordEncoder.encode(user.getPassword());

        System.out.println(user.getUsername());
        user.setPassword(passwordEncoded);
        user.setCreated_at(new Date());
        user.setPassword("ROLE_USER");
        userRepos.save(user);

    }

}
