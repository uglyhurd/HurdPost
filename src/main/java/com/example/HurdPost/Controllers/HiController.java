package com.example.HurdPost.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HiController {



    @GetMapping("/posts")
    public String LetPosts(){
        return "auth/posts/posts";
    }

}
