package com.tasklist.api.services;

import com.tasklist.api.dtos.UserDTO;
import com.tasklist.api.exceptions.customexceptions.InvalidUserException;
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

    public User findUser(UUID userId) {
        if(!userExists(userId)) {
            throw new InvalidUserException("Usuário não existe. Por favor, cadastre-se");
        }

        return getUser(userId.toString());
    }

    public boolean userExists(UUID userId) {
        Optional<User> userDB = userRepository.findById(userId);
        return userDB.isPresent();
    }

    public User registerUser(UserDTO userDTO) {
        User userRegister = userDTO.convertToUser();
        userRepository.save(userRegister);
        return getUser(userRegister.getId().toString());
    }

    public User getUser(String userId) {
       return userRepository.findById(UUID.fromString(userId)).get();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
