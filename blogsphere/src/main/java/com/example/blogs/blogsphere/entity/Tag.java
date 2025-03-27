package com.example.blogs.blogsphere.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    
    private String name;
    
    // Many-to-Many relationship with posts (bidirectional)
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    // Constructors
    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getTagId() {
        return tagId;
    }
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Post> getPosts() {
        return posts;
    }
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }


    // Helper methods (optional) can be added to manage the relationship.
    public void addPost(Post post) {
        posts.add(post);
        post.getTags().add(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.getTags().remove(this);
    }
}
