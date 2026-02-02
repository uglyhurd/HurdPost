package com.example.HurdPost.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "content" ,columnDefinition = "TEXT")
    private String content;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "video_url")
    private String video_url;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updated_at;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void setId(long id) {
        this.id = id;
    }

    public Post(long id, User user, String content, String image_url, String video_url,
                Date created_at, Date updated_at, List<Like> likes, List<Comment> comments) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.image_url = image_url;
        this.video_url = video_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.likes = likes;
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Post(int id, User user, String content, String image_url, String video_url, Date created_at, Date updated_at) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.image_url = image_url;
        this.video_url = video_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Post(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public Post() {
    }



}
