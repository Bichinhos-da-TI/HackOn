package com.hackon.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackon.dto.UserDto;
import com.hackon.dto.mappers.UserMapper;
import com.hackon.dto.request.CreateUserDto;
import com.hackon.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResponseEntity<UserDto> create (CreateUserDto userDto) throws Exception{
        var newUser = userRepository.save(userMapper.toEntity(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(newUser));
    }

    public ResponseEntity<?> findUserById(Long id) throws Exception{
        var foundUser = userRepository.findById(id).orElseThrow(() -> new Exception("not found"));
        if(foundUser == null)return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User with the id %d not found", id));
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(foundUser));
    }

    public ResponseEntity<?> listUsers(){
        List<UserDto> users = userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(users); 
    }

    public ResponseEntity<String> delete(Long id) throws Exception{
        var foundUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        if(foundUser == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted Successfully");
    }
    
    public ResponseEntity<String> update(Long id,UserDto userDto)throws Exception{
        var foundUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        if(foundUser == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        userRepository.save(userMapper.toEntity(userDto));
        return ResponseEntity.status(HttpStatus.OK).body("Edited Successfully");
    }
}
