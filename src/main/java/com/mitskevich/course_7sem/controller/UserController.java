package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.service.interfaces.UserService;
import com.mitskevich.course_7sem.service.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.findAll().stream().map(userMapper::convertToUserDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable UUID id) {
        return userMapper.convertToUserDTO(userService.findById(id));
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addNewAdmin(@RequestBody UserDTO userDTO) {
        User user = userMapper.convertToUser(userDTO);
        if (userService.saveAdmin(user) != null) {
            return ResponseEntity.ok("User created successfully!");
        }
        else {
            return ResponseEntity.badRequest().body("Something went wrong with creating new user...");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.convertToUser(userDTO);
        if ((user = userService.save(user)) != null) {
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.badRequest().body("Something went wrong with creating new user...");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        User user = userMapper.convertToUser(userDTO);
        if ((user = userService.update(id, user)) != null) {
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.badRequest().body("Something went wrong with user updating");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
