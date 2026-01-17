package com.nixon.myreads.controller;

import com.nixon.myreads.dto.request.BookProgressRequestDTO;
import com.nixon.myreads.dto.response.BookProgressResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;
import com.nixon.myreads.dto.response.UserResponseDTO;
import com.nixon.myreads.service.BookProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/myreads/progress")
@RequiredArgsConstructor
public class BookProgressController {
    private final BookProgressService service;

    @PostMapping("/create")
    ResponseEntity<String> createProgress(@RequestBody BookProgressRequestDTO requestDTO){
        return new ResponseEntity<>(service.createProgress(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<BookProgressResponseDTO> getBookProgressById(@PathVariable Long id){
        return new ResponseEntity<>(service.getBookProgressById(id), HttpStatus.OK);
    }

    @GetMapping("/get/book/{id}")
    ResponseEntity<BookResponseDTO> getBookByProgressId(@PathVariable Long id){
        return new ResponseEntity<>(service.getBookByProgressId(id), HttpStatus.OK);
    }

    @GetMapping("/get/user/{id}")
    ResponseEntity<UserResponseDTO> getUserByProgressId(@PathVariable Long id){
        return new ResponseEntity<>(service.getUserByProgressId(id), HttpStatus.OK);
    }
}
