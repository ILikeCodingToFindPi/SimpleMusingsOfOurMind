package com.example.blogapp.pages;

import com.example.blogapp.models.Account;
import com.example.blogapp.models.Post;
import com.example.blogapp.actions.PostUpdator;
import com.example.blogapp.tempstorage.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomePage {

    private final PostUpdator postUpdator;
    private final AccountRepository accountRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postUpdator.getAll();
        List<Account> contributors = accountRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("contributors",contributors);
        return "home";
    }

}
