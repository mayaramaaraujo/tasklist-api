package com.tasklist.api.dtos;

import com.tasklist.api.models.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    @NotBlank(message = "username is mandatory")
    private String username;

    public User convertToUser() {
        return new User(username);
    }
}
