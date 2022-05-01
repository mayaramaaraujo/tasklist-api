package com.tasklist.api.controllers;

import com.tasklist.api.dtos.UserDTO;
import com.tasklist.api.exceptions.customexceptions.UserExistsException;
import com.tasklist.api.models.User;
import com.tasklist.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO) {

        if(userService.findByUsername(userDTO.getUsername()) != null) {
            throw new UserExistsException("Usuário já existe.");
        }

        User user = userService.registerUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable(required = true) String userId) {

        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

}
