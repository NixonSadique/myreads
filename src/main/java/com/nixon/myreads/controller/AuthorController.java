package com.nixon.myreads.controller;

import com.nixon.myreads.client.BigBooksService;
import com.nixon.myreads.dto.response.AuthorsResponseDTO;
import com.nixon.myreads.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myreads/author")
@RequiredArgsConstructor
@Tag(name = "4.Author Controller", description = "Contains all the endpoints regarding the Authors")
public class AuthorController {

    private final BigBooksService bigBooksService;
    private final AuthorService authorService;


    @GetMapping("/search/{query}")
    ResponseEntity<List<AuthorsResponseDTO>> searchAuthors(@PathVariable String query) {
        return ResponseEntity.ok(bigBooksService.searchAuthors(query));
    }

    @GetMapping("/get")
    ResponseEntity<List<AuthorsResponseDTO>> getAuthor() {
        return ResponseEntity.ok(authorService.getAuthors());
    }
}
