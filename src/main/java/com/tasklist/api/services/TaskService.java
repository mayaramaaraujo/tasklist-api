package com.tasklist.api.services;

import com.tasklist.api.dtos.TaskDTO;
import com.tasklist.api.exceptions.customexceptions.TastNotFoundException;
import com.tasklist.api.exceptions.customexceptions.UserExistsException;
import com.tasklist.api.models.Task;
import com.tasklist.api.models.User;
import com.tasklist.api.repositories.TaskRepository;
import com.tasklist.api.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    public Task addTask(TaskDTO taskDTO, String userId) {
        User user = userService.userById(userId);
        Task task = taskDTO.convertToTask(user);
        taskRepository.save(task);
        return taskRepository.getById(task.getId());
    }

    public List<Task> tasksByUserId(String userId) {
        User user = userService.userById(userId);
        return taskRepository.findAllByUserId(user.getId());
    }

    public Task update(String userId, String taskId, TaskDTO taskDTO) {

        if(!userService.userExists(UUID.fromString(userId))) {
            throw new UserExistsException("Usuário não existe.");
        }

        Optional<Task> task = taskRepository.findById(UUID.fromString(taskId));

        if(task.isEmpty()) {
            throw new TastNotFoundException("Tarefa não encontrada. Por favor, passe um ID válido.");
        }

        if(taskDTO.getTitle() != null && taskDTO.getTitle().length() != 0) {
            task.get().setTitle(taskDTO.getTitle());
        }

        if(taskDTO.getDescription() != null && taskDTO.getDescription().length() != 0) {
            task.get().setDescription(taskDTO.getDescription());
        }

        task.get().setUpdatedDate(DateFormat.dateFormat(LocalDateTime.now()));
        taskRepository.save(task.get());

        return task.get();
    }
}
