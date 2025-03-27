package com.example.blogs.blogsphere.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogs.blogsphere.entity.Role;
import com.example.blogs.blogsphere.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;
    
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // GET /api/roles/user/{userId} - Retrieve all roles for a given user.
    @GetMapping("/user/{userId}")
    public List<Role> getRolesByUserId(@PathVariable("userId") Long userId) {
        return roleService.getRolesByUserId(userId);
    }

    // POST /api/roles/user/{userId} - Assign a role to a user.
    @PostMapping("/user/{userId}")
    public ResponseEntity<Role> addRoleToUser(@PathVariable("userId") Long userId, @RequestBody Role role) {
        Role created = roleService.addRoleToUser(userId, role);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // DELETE /api/roles/user/{userId}/{roleId} - Remove a role from a user.
    @DeleteMapping("/user/{userId}/{roleId}")
    public ResponseEntity<Void> removeRoleForUser(@PathVariable("userId") Long userId,
                                                  @PathVariable("roleId") Long roleId) {
        roleService.removeRoleForUser(userId, roleId);
        return ResponseEntity.noContent().build();
    }
}
