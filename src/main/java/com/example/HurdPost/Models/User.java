package com.example.HurdPost.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name" , unique = true)
    @NotNull
    private String name;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "email" , unique = true)

    private String email;

    @Column(name = "profile_picture_url")
    private String ProfilePictureUrl;

    @Column(name = "bio")
    private String bio;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private Date created_at;

    @Column(name = "role", columnDefinition = "VARCHAR(40) DEFAULT 'USER'")
    private String role;

    @OneToMany(mappedBy = "follower")
    private List<Follower> follower = new ArrayList<>();

    @OneToMany(mappedBy = "following")
    private List<Follower> following = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public List<Follower> getFollower() {
        return follower;
    }

    public User(long id, String name, String username, String password,
                String email, String profilePictureUrl, String bio,
                Date created_at, String role, List<Follower> follower,
                List<Follower> following, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        ProfilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.created_at = created_at;
        this.role = role;
        this.follower = follower;
        this.following = following;
        this.posts = posts;
    }

    public void setFollower(List<Follower> follower) {
        this.follower = follower;
    }

    public List<Follower> getFollowing() {
        return following;
    }

    public void setFollowing(List<Follower> following) {
        this.following = following;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }



    public User(long id, String name, String username, String password, String email,
                String profilePictureUrl, String bio, Date created_at, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        ProfilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.created_at = created_at;
        this.role = role;
    }

    public User() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureUrl() {
        return ProfilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        ProfilePictureUrl = profilePictureUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
