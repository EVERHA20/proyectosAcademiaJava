package com.academia.bookstore.controllers;

import com.academia.bookstore.dto.RoleCreateRequest;
import com.academia.bookstore.dto.RoleResponse;
import com.academia.bookstore.models.Role;
import com.academia.bookstore.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    public void createRole_ReturnsCreated() throws Exception {
        RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
        roleCreateRequest.setRoleName("ROLE_ADMIN");

        Role role = new Role(1L, "ROLE_ADMIN", null);
        when(roleService.createRole(any(Role.class))).thenReturn(role);

        mockMvc.perform(post("/api/roles")
                        .contentType("application/json")
                        .content("{\"roleName\": \"ROLE_ADMIN\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.roleName").value("ROLE_ADMIN"));
    }

    @Test
    public void getRoleById_ReturnsRole() throws Exception {
        Role role = new Role(1L, "ROLE_USER", null);
        when(roleService.getRoleById(anyLong())).thenReturn(Optional.of(role));

        mockMvc.perform(get("/api/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.roleName").value("ROLE_USER"));
    }

    @Test
    public void getRoleById_ReturnsNotFound() throws Exception {
        when(roleService.getRoleById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/roles/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllRoles_ReturnsRoles() throws Exception {
        Role role1 = new Role(1L, "ROLE_USER", null);
        Role role2 = new Role(2L, "ROLE_ADMIN", null);
        List<Role> roles = Arrays.asList(role1, role2);
        when(roleService.getAllRoles()).thenReturn(roles);

        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].roleName").value("ROLE_USER"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].roleName").value("ROLE_ADMIN"));
    }

    @Test
    public void updateRole_ReturnsUpdatedRole() throws Exception {
        RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
        roleCreateRequest.setRoleName("ROLE_ADMIN");

        Role updatedRole = new Role(1L, "ROLE_ADMIN", null);
        when(roleService.updateRole(anyLong(), any(Role.class))).thenReturn(updatedRole);

        mockMvc.perform(put("/api/roles/1")
                        .contentType("application/json")
                        .content("{\"roleName\": \"ROLE_ADMIN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.roleName").value("ROLE_ADMIN"));
    }

    @Test
    public void updateRole_ReturnsNotFound() throws Exception {
        RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
        roleCreateRequest.setRoleName("ROLE_ADMIN");

        when(roleService.updateRole(anyLong(), any(Role.class))).thenReturn(null);

        mockMvc.perform(put("/api/roles/1")
                        .contentType("application/json")
                        .content("{\"roleName\": \"ROLE_ADMIN\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteRole_ReturnsNoContent() throws Exception {
        doNothing().when(roleService).deleteRole(anyLong());

        mockMvc.perform(delete("/api/roles/1"))
                .andExpect(status().isNoContent());
    }
}