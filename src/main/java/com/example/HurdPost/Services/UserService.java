package com.example.HurdPost.Services;

import com.example.HurdPost.Models.User;
import com.example.HurdPost.Repositories.UserRepos;
import com.example.HurdPost.Security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


    private final UserRepos userRepos;

    @Autowired
    public UserService(UserRepos userRepos) {
        this.userRepos = userRepos;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepos.findByName(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found!");


        return new PersonDetails(user.get());
    }
}
