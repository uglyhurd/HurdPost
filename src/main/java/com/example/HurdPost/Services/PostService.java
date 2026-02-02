package com.example.HurdPost.Services;

import com.example.HurdPost.Models.Post;
import com.example.HurdPost.Repositories.PostRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepos postRepos;

    @Autowired
    public PostService(PostRepos postRepos) {
        this.postRepos = postRepos;
    }

    @Transactional
    public void savePost(Post post){

        post.setCreated_at(new Date());

        postRepos.save(post);

    }

    public List<Post> getAllPosts(){
        return postRepos.findAllOrderedByDateAsc();
    }

    public List<Post> getPostsByUserId(long id){

        return postRepos.findAllByUserIdOrderByCreated_atDesc(id);

    }

}
