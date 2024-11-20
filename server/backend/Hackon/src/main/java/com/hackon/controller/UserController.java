package com.hackon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackon.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hackon.dto.UserDto;
import com.hackon.dto.request.CreateUserDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto createUserDto) throws Exception {  
        System.out.println(createUserDto);      
        return userService.create(createUserDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id) throws Exception {
        return userService.findUserById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allUsers() {
        return userService.listUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody UserDto userDto) throws Exception {
        return userService.update(id,userDto);
    }
    
    
}
