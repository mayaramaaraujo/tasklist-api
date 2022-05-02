package com.tasklist.api.services;

import com.tasklist.api.dtos.UserDTO;
import com.tasklist.api.exceptions.customexceptions.InvalidUserException;
import com.tasklist.api.exceptions.customexceptions.UserExistsException;
import com.tasklist.api.models.User;
import com.tasklist.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User userById(String userId) {
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        if(!user.isPresent()) {
            throw new InvalidUserException("Usuário não existe.");
        }

        return user.get();
    }

    public boolean userExists(UUID userId) {
        Optional<User> userDB = userRepository.findById(userId);
        return userDB.isPresent();
    }

    public User registerUser(UserDTO userDTO) {
        User userRegister = userDTO.convertToUser();
        userRepository.save(userRegister);
        return userById(userRegister.getId().toString());
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if(user != null) {
            throw new UserExistsException("Usuário já existe.");
        }

        return user;
    }
}
