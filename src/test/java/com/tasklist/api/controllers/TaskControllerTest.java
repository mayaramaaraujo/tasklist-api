package com.tasklist.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasklist.api.dtos.TaskDTO;
import com.tasklist.api.dtos.UserDTO;
import com.tasklist.api.models.Task;
import com.tasklist.api.models.User;
import com.tasklist.api.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TaskService taskService;

    private TaskDTO taskDTO = new TaskDTO();
    private UserDTO userDTO = new UserDTO();
    private User user;
    private Task task;
    private List<Task> taskList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");

        userDTO.setUsername("username");

        user = userDTO.convertToUser();
        user.setId(UUID.randomUUID());

        task = taskDTO.convertToTask(user);
        task.setId(UUID.randomUUID());

        when(taskService.addTask(new TaskDTO(), "1234")).thenReturn(task);
        when(taskService.getTasks(user.getId().toString())).thenReturn(taskList);
    }

    @Test
    public void testPostTask() throws Exception {

        mockMvc.perform(post("/task/{userId}", "userId", "1234")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(task))).andExpect(status().isCreated());
    }

    @Test
    public void testGetTaskByUser() throws Exception {

        mockMvc.perform(get("/task/{userId}", "userId", "1234")
                .contentType("application/json")).andExpect(status().isOk()).andReturn().getResponse();
    }

}
