package com.nixon.myreads.controller;

import com.nixon.myreads.client.BigBooksService;
import com.nixon.myreads.dto.response.AuthorsResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myreads/books")
@RequiredArgsConstructor
public class BigBooksController {

    private final BigBooksService booksService;

    @GetMapping("/search/{query}")
    ResponseEntity<List<BookResponseDTO>> searchBooks(@PathVariable String query) {
        return ResponseEntity.ok(booksService.searchBooks(query));
    }

    @GetMapping("/authors/{query}")
    ResponseEntity<List<AuthorsResponseDTO>> searchAuthors(@PathVariable String query) {
        return ResponseEntity.ok(booksService.searchAuthors(query));
    }

    @GetMapping("/similar/{id}")
    ResponseEntity<List<BookResponseDTO>> searchAuthors(@PathVariable int id) {
        return ResponseEntity.ok(booksService.getSimilarBooks(id));
    }
}
