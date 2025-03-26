//package com.example.blogs.blogsphere.controller;
//
//import com.example.blogs.blogsphere.entity.Role;
//import com.example.blogs.blogsphere.service.RoleService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/roles")
//public class RoleController {
//
//    private final RoleService roleService;
//    
//    public RoleController(RoleService roleService) {
//        this.roleService = roleService;
//    }
//    
//    // GET /api/roles/user/{userId} - Retrieve all roles for a specific user
//    @GetMapping("/user/{userId}")
//    public List<Role> getRolesByUserId(@PathVariable Long userId) {
//        return roleService.getRolesByUserId(userId);
//    }
//    
//    // GET /api/roles/{roleId} - Retrieve a specific role by its roleId
//    @GetMapping("/{roleId}")
//    public Role getRoleById(@PathVariable Long roleId) {
//        return roleService.getRoleById(roleId);
//    }
//    
//    // PUT /api/roles/{roleId} - Update a specific role (e.g., change role name)
//    @PutMapping("/{roleId}")
//    public Role updateRole(@PathVariable Long roleId, @RequestBody Role updatedRole) {
//        return roleService.updateRole(roleId, updatedRole);
//    }
//    
//    // DELETE /api/roles/{roleId} - Delete a specific role
//    @DeleteMapping("/{roleId}")
//    public void deleteRole(@PathVariable Long roleId) {
//        roleService.deleteRole(roleId);
//    }
//}
