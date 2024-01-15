package com.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import com.socialmedia.service.PostService;
import com.socialmedia.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/fetch-and-save")
    public List<Post> fetchAndSavePosts() {
        return postService.fetchAndSavePosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        // Retrieve the user by ID or some other criteria
        User user = userService.getUserById(post.getUser().getId());

        // Set the retrieved user to the post
        post.setUser(user);

        userService.createUser(user);
        // You may want to implement validation and additional logic here
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        return postService.updatePost(id, updatedPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}

