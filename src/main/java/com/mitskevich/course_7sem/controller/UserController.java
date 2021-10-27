package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.service.interfaces.UserService;
import com.mitskevich.course_7sem.service.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllDoctors() {
        return userService.findAll().stream().map(userMapper::convertToUserDTO).collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.convertToUser(userDTO);
        if (userService.save(user) != null) {
            return ResponseEntity.ok("User created successfully!");
        }
        else {
            return ResponseEntity.badRequest().body("Something went wrong with creating new user...");
        }
    }
}
