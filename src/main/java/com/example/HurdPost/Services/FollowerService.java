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
        if (youId == ownId)
            throw new IllegalArgumentException("Вы пытаетесь подписаться сам на себя");
        User follower = userService.getUserById(youId);
        User following = userService.getUserById(ownId);
        if(followerRepos.existsByFollowerAndFollowing(follower, following))
            throw new IllegalArgumentException("Уже существует подписка!");
        if(follower == null || following == null)
            throw new IllegalArgumentException("Пользователь не найден!");
        Follower newFollow = new Follower(follower, following);
        newFollow.setCreated_at(new Date());
        followerRepos.save(newFollow);
    }
    public boolean checkFollow(long youId, long ownId) {
        User follower = userService.getUserById(youId);
        User following = userService.getUserById(ownId);

        return followerRepos.existsByFollowerAndFollowing(follower, following);
    }
    @Transactional
    public void unFollow(long youId, long ownId) {
        if (youId == ownId)
            throw new IllegalArgumentException("Вы пытаетесь отписаться сам на себя");

        Follower follower = followerRepos.findByFollower_IdAndFollowing_Id(youId, ownId);
        followerRepos.delete(follower);

    }

    public long countFollowing(long follower_id) {
        return followerRepos.countByFollowing_Id(follower_id);
    }

    public long countFollowers(long follower_id) {
        return followerRepos.countByFollower_Id(follower_id);
    }





}
