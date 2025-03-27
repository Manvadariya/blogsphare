package com.example.blogs.blogsphere.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogs.blogsphere.entity.Post;
import com.example.blogs.blogsphere.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // GET /api/posts - Retrieve all posts.
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // GET /api/posts/{id} - Retrieve a post by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // POST /api/posts - Create a new post.
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post created = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/posts/{id} - Update an existing post.
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    // DELETE /api/posts/{id} - Delete a post.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/posts/{postId}/tags/{tagId} - Add a tag to a post.
    @PostMapping("/{postId}/tags/{tagId}")
    public ResponseEntity<Post> addTagToPost(@PathVariable Long postId, @PathVariable Long tagId) {
        Post updatedPost = postService.addTagToPost(postId, tagId);
        return ResponseEntity.ok(updatedPost);
    }

    // DELETE /api/posts/{postId}/tags/{tagId} - Remove a tag from a post.
    @DeleteMapping("/{postId}/tags/{tagId}")
    public ResponseEntity<Post> removeTagFromPost(@PathVariable Long postId, @PathVariable Long tagId) {
        Post updatedPost = postService.removeTagFromPost(postId, tagId);
        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping("/tag/{tagName}")
    public List<Post> getPostsByTag(@PathVariable String tagName) {
        return postService.getPostsByTagName(tagName);
    }

}
