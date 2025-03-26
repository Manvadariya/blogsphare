package com.example.blogs.blogsphere.controller;

import com.example.blogs.blogsphere.entity.Role;
import com.example.blogs.blogsphere.entity.User;
import com.example.blogs.blogsphere.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    // GET /api/users - Retrieve all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    // GET /api/users/{id} - Retrieve a user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    // POST /api/users - Create a new user (only accessible by ADMIN)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    // PUT /api/users/{id} - Update an existing user (only accessible by ADMIN)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    
    // DELETE /api/users/{id} - Delete a user (only accessible by ADMIN)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    
    // POST /api/users/{id}/roles - Assign a role to a user (only accessible by ADMIN)
    @PostMapping("/{id}/roles")
    public User assignRole(@PathVariable Long id, @RequestBody Role role) {
        return userService.assignRole(id, role);
    }
    
    // DELETE /api/users/{id}/roles/{roleId} - Remove a role from a user (only accessible by ADMIN)
    @DeleteMapping("/{id}/roles/{roleId}")
    public User removeRole(@PathVariable Long id, @PathVariable Long roleId) {
        return userService.removeRole(id, roleId);
    }
}
