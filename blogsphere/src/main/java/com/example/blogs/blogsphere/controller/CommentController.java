package com.example.blogs.blogsphere.controller;

import com.example.blogs.blogsphere.entity.Comment;
import com.example.blogs.blogsphere.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    
    // GET /api/comments - retrieve all comments
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
    
    // GET /api/comments/{id} - retrieve a comment by id
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }
    
    // POST /api/comments - create a new comment
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }
    
    // PUT /api/comments/{id} - update an existing comment
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }
    
    // DELETE /api/comments/{id} - delete a comment
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
