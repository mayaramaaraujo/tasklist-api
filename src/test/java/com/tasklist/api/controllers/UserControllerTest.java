package com.tasklist.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasklist.api.dtos.UserDTO;
import com.tasklist.api.models.User;
import com.tasklist.api.repositories.UserRepository;
import com.tasklist.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    private User user = new User();
    private UserDTO userDTO;

    @BeforeEach
    public void setup() {
        user.setId(UUID.randomUUID());
        user.setUsername("username");

        userDTO = new UserDTO(user.getId(), user.getUsername());

        when(userService.userById("1234")).thenReturn(user);
        when(userRepository.getById(user.getId())).thenReturn(user);
    }

    @Test
    public void testUserPost() throws Exception {
        mockMvc.perform(post("/user")
                .contentType("application/json").content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    /*@Test
    public void testUserGet() throws Exception {
        mockMvc.perform(get("/user/{userId}", "userId", "1234")
                        .contentType("application/json"))
                .andExpect(status().isOk()).andReturn().getResponse();
    }*/

}
