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

import com.example.blogs.blogsphere.entity.Role;
import com.example.blogs.blogsphere.entity.User;
import com.example.blogs.blogsphere.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users - Retrieve all users.
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET /api/users/{id} - Retrieve a user by ID.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/users - Create a new user.
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/users/{id} - Update an existing user.
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // DELETE /api/users/{id} - Delete a user.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/users/{id}/roles - Assign a role to a user.
    @PostMapping("/{id}/roles")
    public ResponseEntity<User> assignRole(@PathVariable("id") Long userId, @RequestBody Role role) {
        return ResponseEntity.ok(userService.assignRole(userId, role));
    }

    // DELETE /api/users/{id}/roles/{roleId} - Remove a role from a user.
    @DeleteMapping("/{id}/roles/{roleId}")
    public ResponseEntity<User> removeRole(@PathVariable("id") Long userId, @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(userService.removeRole(userId, roleId));
    }
}
