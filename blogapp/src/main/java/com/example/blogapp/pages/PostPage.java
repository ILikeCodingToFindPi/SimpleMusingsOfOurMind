package com.example.blogapp.pages;

import com.example.blogapp.models.Account;
import com.example.blogapp.models.Comment;
import com.example.blogapp.models.Post;
import com.example.blogapp.actions.AccountCreatorFinder;
import com.example.blogapp.actions.PostUpdator;
import com.example.blogapp.tempstorage.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostPage {

    private final PostUpdator postUpdator;
    private final AccountCreatorFinder accountCreatorFinder;
    private final CommentRepository commentRepository;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model, Comment newComment) {
        Optional<Post> optionalPost = postUpdator.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            List<Comment> comments = post.getComments(); // Retrieve comments for the post
            model.addAttribute("post", post);
            model.addAttribute("comments", comments); // Add comments to the model
            model.addAttribute("newComment", newComment); // Add an empty Comment object for the form
            return "postpage";
        } else {
            return "404";
        }
    }

    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Post post) {
        Optional<Post> optionalPost = postUpdator.getById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postUpdator.save(existingPost);
        }
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/posts/{id}/addComment")
    public String addComment(@PathVariable Long id, @ModelAttribute Comment newComment) {
        Optional<Post> optionalPost = postUpdator.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            Comment xComment = new Comment();
            xComment.setAuthor(newComment.getAuthor());
            xComment.setText(newComment.getText());
            xComment.setPost(post);

            post.getComments().add(xComment);

            postUpdator.save(post);

            return "redirect:/posts/" + id;
        }
        return "404";
    }



    @GetMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Account> optionalAccount = accountCreatorFinder.findOneByEmail(authUsername);
        if (optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "newpost";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(@ModelAttribute Post post) {


        postUpdator.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model) {


        Optional<Post> optionalPost = postUpdator.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "editpost";
        } else {
            return "404";
        }
    }


    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id) {


        Optional<Post> optionalPost = postUpdator.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
         postUpdator.delete(post);
            return "redirect:/";
        } else {
            return "404";
        }
    }
}

