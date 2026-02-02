package com.example.HurdPost.Controllers;

import com.example.HurdPost.Models.User;
import com.example.HurdPost.Repositories.UserRepos;
import com.example.HurdPost.Security.PersonDetails;
import com.example.HurdPost.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class HiController {


    private final UserService userService;
    private final UserRepos userRepos;
    @Autowired
    public HiController(UserService userService, UserRepos userRepos) {
        this.userService = userService;
        this.userRepos = userRepos;
    }


    @GetMapping("/posts")
    public String LetPosts(Model model,
                           @AuthenticationPrincipal PersonDetails personDetails){

        Optional<User> optUser = userRepos.findByUsername(personDetails.getUsername());
        User user = optUser.get();
//        if(optUser.isEmpty())
//            model.addAttribute("user", null);

        model.addAttribute("user", user);
        return "auth/posts/posts";
    }

    @GetMapping("/myProfile/{id}")
    public String getProfile(@PathVariable("id") int id, Model model){

        model.addAttribute("user", userService.getUserById(id));

        return "auth/posts/profile";
    }



}
