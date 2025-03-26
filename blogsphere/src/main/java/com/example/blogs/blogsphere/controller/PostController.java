package com.example.blogs.blogsphere.controller;

import com.example.blogs.blogsphere.entity.Post;
import com.example.blogs.blogsphere.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    
    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    // GET /api/posts - retrieve all posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    // GET /api/posts/{id} - retrieve a post by id
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
    
    // POST /api/posts - create a new post
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
    
    // PUT /api/posts/{id} - update an existing post
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }
    
    // DELETE /api/posts/{id} - delete a post
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
