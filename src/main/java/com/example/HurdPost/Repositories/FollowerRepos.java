package com.example.HurdPost.Repositories;

import com.example.HurdPost.Models.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepos extends JpaRepository<Follower, Integer> {



}
