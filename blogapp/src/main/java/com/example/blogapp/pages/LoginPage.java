package com.example.blogapp.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPage {

    @GetMapping("/login")
    public String getLogin() {

        return "login";
    }
}
