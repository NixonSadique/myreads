package com.nixon.myreads.controller;

import com.nixon.myreads.dto.request.BookProgressRequestDTO;
import com.nixon.myreads.dto.response.BookProgressResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;
import com.nixon.myreads.dto.response.UserResponseDTO;
import com.nixon.myreads.service.BookProgressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myreads/progress")
@RequiredArgsConstructor
@Tag(name = "4.Progress Controller", description = "Contains all the endpoints regarding the Progress on the books")
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

    @PutMapping("/update/{id}{completion}")
    ResponseEntity<String> updateProgress(@RequestParam Long id, @RequestParam double completion){
        return new ResponseEntity<>(service.updateCompletion(id, completion),  HttpStatus.OK);
    }

    @GetMapping("/get/user/{id}")
    ResponseEntity<List<BookProgressResponseDTO>> getUserProgresses(@PathVariable Long id){
        return new ResponseEntity<>(service.getUserProgresses(id), HttpStatus.OK);
    }
}
