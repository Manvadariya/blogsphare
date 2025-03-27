package com.example.blogs.blogsphere.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    
    @Column(length = 1000)
    private String content;
    
    private Date createdAt;
    
    // Many comments belong to one post
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    // Constructors
    public Comment() {}

    public Comment(String content, Date createdAt) {
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
}
