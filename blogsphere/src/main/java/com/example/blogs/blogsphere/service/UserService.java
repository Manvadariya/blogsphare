package com.example.blogs.blogsphere.service;

import com.example.blogs.blogsphere.DAO.UserDAO;
import com.example.blogs.blogsphere.entity.Role;
import com.example.blogs.blogsphere.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    // Retrieve a user by ID, throw exception if not found
    public User getUserById(Long userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    // Create a new user
    @Transactional
    public User createUser(User user) {
        return userDAO.save(user);
    }

    // Update an existing user
    @Transactional
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = getUserById(userId);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        return userDAO.save(existingUser);
    }

    // Delete a user by ID
    @Transactional
    public void deleteUser(Long userId) {
        userDAO.deleteById(userId);
    }
    
    // Assign a role to a user
    @Transactional
    public User assignRole(Long userId, Role role) {
        User user = getUserById(userId);
        user.getRoles().add(role);
        role.setUser(user);
        return userDAO.save(user);
    }
    
    // Remove a role from a user
    @Transactional
    public User removeRole(Long userId, Long roleId) {
        User user = getUserById(userId);
        // Remove role by iterating through the roles list
        user.getRoles().removeIf(r -> r.getRoleId().equals(roleId));
        return userDAO.save(user);
    }
}
