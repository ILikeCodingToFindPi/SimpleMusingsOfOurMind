package com.example.blogapp.actions;

import com.example.blogapp.models.Comment;
import com.example.blogapp.models.Post;
import com.example.blogapp.tempstorage.CommentRepository;
import com.example.blogapp.tempstorage.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostUpdator {

    private final PostRepository postRepository;
   
    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }
  

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

}
