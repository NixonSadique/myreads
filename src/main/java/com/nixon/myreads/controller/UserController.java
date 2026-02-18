package com.nixon.myreads.controller;

import com.nixon.myreads.dto.request.UserRequestDTO;
import com.nixon.myreads.dto.response.StatsResponse;
import com.nixon.myreads.dto.response.UserResponseDTO;
import com.nixon.myreads.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myreads/user")
@RequiredArgsConstructor
@Tag(name = "1.User Controller", description = "Contains all the endpoints regarding the user creation")
public class UserController {
    private final UserService service;

    @PostMapping("/create")
    ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDTO request){
        return new ResponseEntity<>(service.createUser(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(service.getUserByID(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get")
    ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/admin/{id}")
    ResponseEntity<String> makeUserAdmin(@PathVariable Long id){
        return new ResponseEntity<>(service.userToAdmin(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stats")
    ResponseEntity<StatsResponse> getStats(){
        return new ResponseEntity<>(service.getStats(), HttpStatus.OK);
    }


}
