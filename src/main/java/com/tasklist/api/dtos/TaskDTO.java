package com.tasklist.api.dtos;

import com.tasklist.api.models.Task;
import com.tasklist.api.models.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TaskDTO {

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotNull
    private boolean done;

    public Task convertToTask(User user) {
        return new Task(title, description, done, user);
    }
}
