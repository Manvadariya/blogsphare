package com.example.blogs.blogsphere.DAO;

import com.example.blogs.blogsphere.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoleDAO extends JpaRepository<Role, Long> {
    // Retrieve all roles for a given user id.
    List<Role> findByUserUserId(Long userId);
}
