package com.tasklist.api.controllers;

import com.tasklist.api.dtos.UserDTO;
import com.tasklist.api.models.User;
import com.tasklist.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO) {

        userService.findByUsername(userDTO.getUsername());
        User user = userService.registerUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable(required = true) String userId) {
        User user = userService.userById(userId);
        UserDTO userDTO = user.convertToUserDTO();
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping
    public ResponseEntity<?> getUserByUsername(@RequestBody String username) {
        User user = userService.findByUsername(username);
        UserDTO userDTO = user.convertToUserDTO();
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
}
