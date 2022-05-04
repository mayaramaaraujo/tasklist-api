package com.tasklist.api.controllers;

import com.tasklist.api.dtos.TaskDTO;
import com.tasklist.api.models.Task;
import com.tasklist.api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addTask(@RequestBody @Valid TaskDTO taskDTO, @PathVariable(required = true) String userId) {
        Task task = taskService.addTask(taskDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> tasksByUserId(@PathVariable(required = true) String userId) {
        List<Task> task = taskService.tasksByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }

    @PutMapping("/{userId}/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable String userId, @PathVariable String taskId, @RequestBody TaskDTO taskDTO) {
        Task task = taskService.update(userId, taskId, taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping("/{userId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String userId, @PathVariable String taskId) {
        taskService.delete(userId, taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
