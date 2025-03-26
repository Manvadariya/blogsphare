package com.example.blogs.blogsphere.DAO;

import com.example.blogs.blogsphere.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDAO extends JpaRepository<Post, Long> {
}
