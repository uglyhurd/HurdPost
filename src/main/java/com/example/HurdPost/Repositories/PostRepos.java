package com.example.HurdPost.Repositories;

import com.example.HurdPost.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepos extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p ORDER BY p.created_at DESC ")
    List<Post> findAllOrderedByDateAsc();

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.created_at DESC")
    List<Post> findAllByUserIdOrderByCreated_atDesc(@Param("userId") long id);

}
