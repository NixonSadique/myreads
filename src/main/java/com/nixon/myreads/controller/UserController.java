package com.nixon.myreads.controller;

import com.nixon.myreads.dto.request.UserRequestDTO;
import com.nixon.myreads.dto.response.UserResponseDTO;
import com.nixon.myreads.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myreads/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/create")
    ResponseEntity<String> createUser(@RequestBody UserRequestDTO request){
        return new ResponseEntity<>(service.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(service.getUserByID(id), HttpStatus.OK);
    }

    @GetMapping("/get/")
    ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }


}
