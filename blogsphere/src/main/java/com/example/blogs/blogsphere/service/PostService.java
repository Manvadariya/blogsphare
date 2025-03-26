package com.example.blogs.blogsphere.service;

import com.example.blogs.blogsphere.DAO.PostDAO;
import com.example.blogs.blogsphere.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private final PostDAO postDAO;

    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    // Retrieve all posts
    public List<Post> getAllPosts() {
        return postDAO.findAll();
    }

    // Retrieve a post by its ID
    public Post getPostById(Long postId) {
        return postDAO.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
    }

    // Create a new post
    @Transactional
    public Post createPost(Post post) {
        // Set the publish date if needed (here we use the current time)
        post.setPublishDate(new Date());
        return postDAO.save(post);
    }

    // Update an existing post
    @Transactional
    public Post updatePost(Long postId, Post updatedPost) {
        Post existingPost = getPostById(postId);
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        // Optionally update other fields like likeCount, tags, etc.
        return postDAO.save(existingPost);
    }

    // Delete a post by its ID
    @Transactional
    public void deletePost(Long postId) {
        postDAO.deleteById(postId);
    }
}
