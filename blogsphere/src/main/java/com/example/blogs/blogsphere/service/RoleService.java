package com.example.blogs.blogsphere.service;

import com.example.blogs.blogsphere.DAO.RoleDAO;
import com.example.blogs.blogsphere.DAO.UserDAO;
import com.example.blogs.blogsphere.entity.Role;
import com.example.blogs.blogsphere.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleService {

    private final RoleDAO roleDAO;
    private final UserDAO userDAO;

    public RoleService(RoleDAO roleDAO, UserDAO userDAO) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
    }

    // Retrieve all roles for a given user id.
    public List<Role> getRolesByUserId(Long userId) {
        return roleDAO.findByUserUserId(userId);
    }

    // Assign a role to a user using the user's ID.
    @Transactional
    public Role addRoleToUser(Long userId, Role role) {
        User user = userDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        role.setUser(user);
        return roleDAO.save(role);
    }

    // Remove a role for a user, identified by user id and role id.
    @Transactional
    public void removeRoleForUser(Long userId, Long roleId) {
        Role role = roleDAO.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        // Ensure that the role belongs to the specified user.
        if (!role.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Role does not belong to user with id: " + userId);
        }
        roleDAO.delete(role);
    }
}
