package com.tasklist.api.services;

import com.tasklist.api.dtos.TaskDTO;
import com.tasklist.api.models.Task;
import com.tasklist.api.models.User;
import com.tasklist.api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    public Task addTask(TaskDTO taskDTO, String userId) {
        User existingUser = userService.findUser(UUID.fromString(userId));
        Task task = taskDTO.convertToTask(existingUser);
        taskRepository.save(task);
        return getTask(task.getId());
    }

    public Task getTask(UUID taskId) {
        return taskRepository.findById(taskId).get();
    }

    public List<Task> getTasks(String userId) {
        User existingUser = userService.findUser(UUID.fromString(userId));
        return taskRepository.findAllByUserId(existingUser.getId());
    }
}
