package com.example.HurdPost.Controllers;

import com.example.HurdPost.Models.Post;
import com.example.HurdPost.Models.User;
import com.example.HurdPost.Repositories.UserRepos;
import com.example.HurdPost.Security.PersonDetails;
import com.example.HurdPost.Services.FollowerService;
import com.example.HurdPost.Services.PostService;
import com.example.HurdPost.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
public class HiController {


    private final UserService userService;
    private final UserRepos userRepos;
    private final PostService postService;
    private final FollowerService followerService;
    @Autowired
    public HiController(UserService userService, UserRepos userRepos, PostService postService, FollowerService followerService) {
        this.userService = userService;
        this.userRepos = userRepos;
        this.postService = postService;
        this.followerService = followerService;
    }


    @GetMapping("/posts")
    public String LetPosts(Model model,
                           @AuthenticationPrincipal PersonDetails personDetails){

        Optional<User> optUser = userRepos.findByUsername(personDetails.getUsername());
        User user = optUser.get();
//        if(optUser.isEmpty())
//            model.addAttribute("user", null);

        model.addAttribute("user", user);
        model.addAttribute("allPosts", postService.getAllPosts());
        return "auth/posts/posts";
    }

//    @GetMapping("/myProfile/{id}")
//    public String myProfile(@PathVariable("id") int id, Model model){
//
//        model.addAttribute("user", userService.getUserById(id));
//
//        model.addAttribute("userPosts", postService.getPostsByUserId(id));
//        return "auth/posts/profile";
//    }
    @GetMapping("/user/{username}")
    public String getProfile(Model model, @PathVariable("username") String username, @AuthenticationPrincipal PersonDetails personDetails){
        Optional<User> user = userRepos.findByUsername(username);
        Optional<User> myUser = userRepos.findByUsername(personDetails.getUsername());
        if (user.isEmpty())
            return "redirect:/posts";
        User userOwn = user.get();
        model.addAttribute("user", userOwn);

        model.addAttribute("userPosts", postService.getPostsByUserId(userOwn.getId()));
        boolean myProfile = false;
        if (personDetails != null) {
            Optional<User> myUserOpt = userRepos.findByUsername(personDetails.getUsername());
            if (myUserOpt.isPresent()) {
                model.addAttribute("myUser", myUserOpt.get());
                myProfile = Objects.equals(personDetails.getUsername(), username);
            }
        }
        User youUser = myUser.get();
        model.addAttribute("myUser", youUser);
        model.addAttribute("myProfile", myProfile);
        if(followerService.checkFollow(youUser.getId(), userOwn.getId()))
            model.addAttribute("isFollow", true);
        else{
            model.addAttribute("isFollow", false);
        }


        model.addAttribute("countFollowing", youUser.getFollower().size());
        model.addAttribute("countFollower", youUser.getFollowing().size());


        model.addAttribute("countFollowerOwn", userOwn.getFollowing().size());
        model.addAttribute("countFollowingOwn", userOwn.getFollower().size());


        return "auth/posts/profile";
    }


    @GetMapping("/createPost")
    public String createPost(Model model, @AuthenticationPrincipal PersonDetails personDetails){

        Optional<User> user = userRepos.findByUsername(personDetails.getUsername());

        model.addAttribute("user", user.get());
        model.addAttribute("post", new Post());
        return "auth/posts/createPost";
    }

    @PostMapping("/createPost/getPost")
    public String getPost(@ModelAttribute("post") Post post, @AuthenticationPrincipal PersonDetails personDetails,
                          Model model){

        Optional<User> user = userRepos.findByUsername(personDetails.getUsername());

        post.setUser(user.get());
        postService.savePost(post);

        return "redirect:/posts";
    }

    @PostMapping("/user/follow/{id}")
    public String followOn(@AuthenticationPrincipal PersonDetails personDetails,
                           @PathVariable("id") long id) {

        User ownUsername = userService.getUserById(id);

        Optional<User> follower = userRepos.findByUsername(personDetails.getUsername());

//        follower.ifPresent(user -> followerService.followOnUser(user.getId(), id));

        follower.ifPresent(user -> followerService.followOnUser(user.getId(), id));
        return "redirect:/user/" + ownUsername.getUsername();
    }
    @PostMapping("/user/unfollow/{id}")
    public String unfollow(@AuthenticationPrincipal PersonDetails personDetails,
                           @PathVariable("id") long id) {
        User ownUsername = userService.getUserById(id);

        Optional<User> youUser = userRepos.findByUsername(personDetails.getUsername());
        youUser.ifPresent(user -> followerService.unFollow(user.getId(), id));

        return "redirect:/user/" + ownUsername.getUsername();
    }

    @PostMapping("/posts/deletePost/{id}")
    public String deletePost(@PathVariable("id") long id, @AuthenticationPrincipal PersonDetails personDetails ){
        Optional<User> user = userRepos.findByUsername(personDetails.getUsername());

        postService.deletePostById(id);
        return "redirect:/user/"+user.get().getUsername();
    }


}
