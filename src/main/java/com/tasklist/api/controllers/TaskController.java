package com.tasklist.api.controllers;

import com.tasklist.api.dtos.TaskDTO;
import com.tasklist.api.models.Task;
import com.tasklist.api.services.TaskService;
import com.tasklist.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addTask(@RequestBody @Valid TaskDTO taskDTO, @PathVariable(required = true) String userId) {

        Task task = taskService.addTask(taskDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getTasksByUser(@PathVariable(required = true) String userId) {

        List<Task> task = taskService.getTasks(userId);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }
}
