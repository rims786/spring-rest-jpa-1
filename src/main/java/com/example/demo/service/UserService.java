package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Service class for managing users.
 */
@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        LOGGER.info("Fetching all users");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        LOGGER.info("Fetching user with ID: " + id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    public User createUser(User user) {
        LOGGER.info("Creating new user: " + user.getName());
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        LOGGER.info("Updating user with ID: " + id);
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        LOGGER.info("Deleting user with ID: " + id);
        User user = getUserById(id);
        userRepository.delete(user);
    }
}