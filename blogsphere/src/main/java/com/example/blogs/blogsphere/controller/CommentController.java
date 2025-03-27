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

import com.example.blogs.blogsphere.entity.Comment;
import com.example.blogs.blogsphere.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // GET /api/comments - Retrieve all comments.
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // GET /api/comments/{id} - Retrieve a comment by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    // POST /api/comments - Create a new comment.
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment created = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/comments/{id} - Update an existing comment.
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(id, comment));
    }

    // DELETE /api/comments/{id} - Delete a comment.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
