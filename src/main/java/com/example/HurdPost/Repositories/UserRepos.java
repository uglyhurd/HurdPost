package com.example.HurdPost.Repositories;

import com.example.HurdPost.Models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;

public interface UserRepos extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String name);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
