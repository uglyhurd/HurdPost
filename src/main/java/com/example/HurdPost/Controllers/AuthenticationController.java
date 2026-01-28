package com.example.HurdPost.Controllers;

import com.example.HurdPost.Models.User;
import com.example.HurdPost.MyExeptions.UserArleadyExistExeption;
import com.example.HurdPost.Repositories.UserRepos;
import com.example.HurdPost.Services.AuthenticationService;
import com.example.HurdPost.Services.UserService;
import com.example.HurdPost.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserValidator userValidator;
    private final UserService userService;
    private final UserRepos userRepos;

    @Autowired
    public AuthenticationController(AuthenticationService authService, UserValidator userValidator, UserService userService, UserRepos userRepos) {
        this.authService = authService;
        this.userValidator = userValidator;
        this.userService = userService;
        this.userRepos = userRepos;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }




    @GetMapping("/createUser")
    public String registerPage(@ModelAttribute("user")User user){
        return "auth/register";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("user") User user, BindingResult bindingResult,
                             @RequestParam(value = "name", required = false) String name,
                             Model model){

        if(bindingResult.hasErrors())
            return "/auth/register";

        try {
            authService.checkUser(user);
            model.addAttribute("user", user);
            return "auth/firstStep";
        }catch (UserArleadyExistExeption exeption){
            if (exeption.getMessage().contains("имя")) {
                bindingResult.rejectValue("username", "error.username",
                        "Такое имя пользователя уже существует");
                model.addAttribute("error", bindingResult);
            } else if (exeption.getMessage().contains("email")){
                bindingResult.rejectValue("email", "error.email",
                        "Пользователь с таким email уже существует");
                model.addAttribute("error", bindingResult);
            }
        }

        return "/auth/register";

//        authService.createUser(user);



    }

    @PostMapping("/finishReg")
    public String finishCreate(@ModelAttribute("user") User user,
                             BindingResult bindingResult, Model model){

//        if(bindingResult.hasErrors())
//            return "/auth/register";


        userValidator.validate(user, bindingResult);



        authService.createUser(user);
        return "redirect:/auth/login?success";




    }












}








