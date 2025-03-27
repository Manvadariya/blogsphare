package com.example.blogs.blogsphere.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blogs.blogsphere.DAO.RoleDAO;
import com.example.blogs.blogsphere.DAO.UserDAO;
import com.example.blogs.blogsphere.entity.Role;
import com.example.blogs.blogsphere.entity.User;


@Service
@Transactional
public class UserService {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }


    // Retrieve all users.
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    // Retrieve a user by its ID.
    public User getUserById(Long userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    }

    // In UserService.java
@Transactional
public User createUser(User user) {
    // Ensure the password is BCrypt-encoded before saving.
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userDAO.save(user);
}

@Transactional
public User updateUser(Long userId, User userDetails) {
    return userDAO.findById(userId)
            .map(user -> {
                user.setUserName(userDetails.getUserName());
                user.setEmail(userDetails.getEmail());
                // Re-encode password if it is being updated.
                if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                    user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
                }
                return userDAO.save(user);
            })
            .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
}


    // Delete a user by its ID.
    public void deleteUser(Long userId) {
        if (userDAO.existsById(userId)) {
            userDAO.deleteById(userId);
        } else {
            throw new RuntimeException("User not found with id " + userId);
        }
    }

    // Assign a role to a user using the helper method.
    public User assignRole(Long userId, Role role) {
        return userDAO.findById(userId)
                .map(user -> {
                    // Leverage the helper method on User entity.
                    user.addRole(role);
                    // Save the role (if necessary) and the user.
                    roleDAO.save(role);
                    return userDAO.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    }

    // Remove a role from a user using the helper method.
    public User removeRole(Long userId, Long roleId) {
        return userDAO.findById(userId)
                .map(user -> {
                    Role roleToRemove = user.getRoles().stream()
                            .filter(role -> role.getRoleId().equals(roleId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));
                    
                    // Leverage the helper method for removal.
                    user.removeRole(roleToRemove);
                    // Optionally, remove the role from the database.
                    roleDAO.delete(roleToRemove);
                    return userDAO.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    }
}
