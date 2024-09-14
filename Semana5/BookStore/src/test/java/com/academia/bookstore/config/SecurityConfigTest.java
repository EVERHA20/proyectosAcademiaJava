package com.academia.bookstore.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUserAccessToStoreEndpoint() throws Exception {
        mockMvc.perform(get("/api/store"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUserAccessToBooksByPrice() throws Exception {
        mockMvc.perform(get("/api/store/books/by-price")
                .param("price", "20")
                .param("moreThan", "true"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminAccessToBookControllerEndpoints() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/books")
                .contentType("application/json")
                .content("{\"title\":\"New Book\",\"author\":\"Author\",\"pages\":300,\"price\":19.99}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUserAccessToAdminEndpoints() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType("application/json")
                .content("{\"title\":\"New Book\",\"author\":\"Author\"}"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/store"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}