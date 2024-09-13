package com.academia.bookstore.controllers;

import com.academia.bookstore.dto.UserRequest;
import com.academia.bookstore.dto.UserUpdateRequest;
import com.academia.bookstore.exception.UserNotFoundException;
import com.academia.bookstore.models.Role;
import com.academia.bookstore.models.User;
import com.academia.bookstore.services.RoleService;
import com.academia.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : userRequest.getRoleNames()) {
            roleService.getRoleByName(roleName)
                    .ifPresent(roles::add);
        }
        User user = userService.createUser(userRequest.getUsername(), userRequest.getPassword(), roles);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                            @RequestBody UserUpdateRequest userUpdateRequest) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : userUpdateRequest.getRoleNames()) {
            roleService.getRoleByName(roleName)
                    .ifPresent(roles::add);
        }
        User updatedUser = userService.updateUser(id, userUpdateRequest.getUsername(), userUpdateRequest.getPassword(), roles);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/roles/{roleName}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable Long userId, @PathVariable String roleName) {
        userService.addRoleToUser(userId, roleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/roles/{roleName}")
    public ResponseEntity<Void> removeRoleFromUser(@PathVariable Long userId, @PathVariable String roleName) {
        userService.removeRoleFromUser(userId, roleName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}