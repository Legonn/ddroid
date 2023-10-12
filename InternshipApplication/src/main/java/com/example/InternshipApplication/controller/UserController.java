package com.example.InternshipApplication.controller;

import com.example.InternshipApplication.model.User;
import com.example.InternshipApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/processRegistrationForm")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@ModelAttribute("user") User user){
        userService.createUser(user);
    }
}
