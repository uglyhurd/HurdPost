package com.example.HurdPost.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;


    public Post(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public Post() {
    }
}
