package com.academia.bookstore.controllers;

import com.academia.bookstore.dto.RoleCreateRequest;
import com.academia.bookstore.dto.RoleResponse;
import com.academia.bookstore.models.Role;
import com.academia.bookstore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleCreateRequest roleCreateRequest) {
        Role role = new Role();
        role.setRoleName(roleCreateRequest.getRoleName());
        Role createdRole = roleService.createRole(role);
        RoleResponse response = new RoleResponse();
        response.setId(createdRole.getId());
        response.setRoleName(createdRole.getRoleName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(r -> {
            RoleResponse response = new RoleResponse();
            response.setId(r.getId());
            response.setRoleName(r.getRoleName());
            return ResponseEntity.ok(response);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        List<RoleResponse> responses = roles.stream()
                .map(role -> {
                    RoleResponse response = new RoleResponse();
                    response.setId(role.getId());
                    response.setRoleName(role.getRoleName());
                    return response;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Long id, @RequestBody RoleCreateRequest roleCreateRequest) {
        Role role = new Role();
        role.setRoleName(roleCreateRequest.getRoleName());
        Role updatedRole = roleService.updateRole(id, role);
        return updatedRole != null ? new ResponseEntity<>(convertToResponse(updatedRole), HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    private RoleResponse convertToResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setRoleName(role.getRoleName());
        return response;
    }
}