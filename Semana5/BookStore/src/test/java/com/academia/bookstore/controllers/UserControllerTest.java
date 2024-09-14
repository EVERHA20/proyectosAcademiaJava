package com.academia.bookstore.controllers;

import com.academia.bookstore.dto.UserRequest;
import com.academia.bookstore.dto.UserUpdateRequest;
import com.academia.bookstore.models.Role;
import com.academia.bookstore.models.User;
import com.academia.bookstore.services.RoleService;
import com.academia.bookstore.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void createUser_ReturnsCreated() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("username");
        userRequest.setPassword("password");
        userRequest.setEmail("user@example.com");
        userRequest.setRoleNames(Set.of("ROLE_USER"));

        Role role = new Role(1L, "ROLE_USER", new HashSet<>());
        User user = new User(1L, "username", "password", true, "user@example.com", Set.of(role));

        when(roleService.getRoleByName("ROLE_USER")).thenReturn(Optional.of(role));
        when(userService.createUser(anyString(), anyString(), anyString(), anySet())).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"username\": \"username\", \"password\": \"password\", \"email\": \"user@example.com\", \"roleNames\": [\"ROLE_USER\"]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.email").value("user@example.com"));
    }

    @Test
    public void getUserById_ReturnsUser() throws Exception {
        User user = new User(1L, "username", "password", true, "user@example.com", new HashSet<>());

        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.email").value("user@example.com"));
    }

    @Test
    public void getUserById_ReturnsNotFound() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUser_ReturnsUpdatedUser() throws Exception {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setUsername("newUsername");
        userUpdateRequest.setPassword("newPassword");
        userUpdateRequest.setEmail("newemail@example.com");
        userUpdateRequest.setRoleNames(Set.of("ROLE_USER"));

        Role role = new Role(1L, "ROLE_USER", new HashSet<>());
        User updatedUser = new User(1L, "newUsername", "newPassword", true, "newemail@example.com", Set.of(role));

        when(roleService.getRoleByName("ROLE_USER")).thenReturn(Optional.of(role));
        when(userService.updateUser(anyLong(), anyString(), anyString(), anyString(), anySet())).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/1")
                        .contentType("application/json")
                        .content("{\"username\": \"newUsername\", \"password\": \"newPassword\", \"email\": \"newemail@example.com\", \"roleNames\": [\"ROLE_USER\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newUsername"))
                .andExpect(jsonPath("$.email").value("newemail@example.com"));
    }

    @Test
    public void deleteUser_ReturnsNoContent() throws Exception {
        doNothing().when(userService).deleteUser(anyLong());

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void addRoleToUser_ReturnsOk() throws Exception {
        doNothing().when(userService).addRoleToUser(anyLong(), anyString());

        mockMvc.perform(post("/api/users/1/roles/ROLE_USER"))
                .andExpect(status().isOk());
    }

    @Test
    public void removeRoleFromUser_ReturnsNoContent() throws Exception {
        doNothing().when(userService).removeRoleFromUser(anyLong(), anyString());

        mockMvc.perform(delete("/api/users/1/roles/ROLE_USER"))
                .andExpect(status().isNoContent());
    }
}