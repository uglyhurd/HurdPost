package com.example.HurdPost.Repositories;

import com.example.HurdPost.Models.Follower;
import com.example.HurdPost.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepos extends JpaRepository<Follower, Integer> {

    boolean existsByFollowerAndFollowing(User follower, User following);

    Follower findByFollower_IdAndFollowing_Id(long follower, long following);


    long countByFollower_Id(long followerId);

    long countByFollowing_Id(long following_id);


}
