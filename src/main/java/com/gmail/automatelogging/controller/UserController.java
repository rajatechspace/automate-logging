package com.gmail.automatelogging.controller;

import com.gmail.automatelogging.model.User;
import com.gmail.automatelogging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    //test method

    @GetMapping("/")
    public String testGet() {
        return "i am in ";
    }

    //Get all users;
    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //create new user
    @PostMapping("/newUser")
    public User createNewUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    //get a single user

    @GetMapping("/findUser/{userId}")
    public User getUserByUserId(@PathVariable(value = "userId") Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }

    // update user
    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {

        User appUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserId(user.getUserId());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());

        return userRepository.save(appUser);
    }

    //Delete user

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }


}
