package com.tasklist.api.dtos;

import com.tasklist.api.models.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;

    @NotBlank(message = "username is mandatory")
    private String username;

    public UserDTO() {
    }

    public UserDTO(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public User convertToUser() {
        return new User(username);
    }
}
