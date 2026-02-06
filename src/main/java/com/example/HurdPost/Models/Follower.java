package com.example.HurdPost.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower; //кто подписывается
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private User following; //на кого подписываются

    @Column(name = "created_at")
    @CreationTimestamp
    private Date created_at;

    public Follower(User follower, User following) {
        this.follower = follower;
        this.following = following;
        this.created_at = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Follower(long id, User follower, User following, Date created_at) {
        this.id = id;
        this.follower = follower;
        this.following = following;
        this.created_at = created_at;
    }

    public Follower() {
    }
}
