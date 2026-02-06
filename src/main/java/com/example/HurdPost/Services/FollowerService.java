package com.example.HurdPost.Services;

import com.example.HurdPost.Models.Follower;
import com.example.HurdPost.Models.User;
import com.example.HurdPost.Repositories.FollowerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class FollowerService {


    private final FollowerRepos followerRepos;
    private final UserService userService;

    @Autowired
    public FollowerService(FollowerRepos followerRepos, UserService userService) {
        this.followerRepos = followerRepos;
        this.userService = userService;
    }


    @Transactional
    public void followOnUser(long youId, long ownId) {

        User follower = userService.getUserById(youId);
        User following = userService.getUserById(ownId);

        Follower newFollow = new Follower(follower, following);
        newFollow.setCreated_at(new Date());
        followerRepos.save(newFollow);
    }





}
