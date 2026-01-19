package com.nixon.myreads.controller;

import com.nixon.myreads.client.BigBooksService;
import com.nixon.myreads.dto.response.AuthorsResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;
import com.nixon.myreads.service.BooksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myreads/books")
@RequiredArgsConstructor
@Tag(name = "3.Books Controller", description = "Contains all the endpoints regarding the Books")
public class BooksController {

    private final BigBooksService bigBooksService;
    private final BooksService booksService;

    @GetMapping("/search/{query}")
    ResponseEntity<List<BookResponseDTO>> searchBooks(@PathVariable String query) {
        return ResponseEntity.ok(bigBooksService.searchBooks(query));
    }

    @GetMapping("/authors/{query}")
    ResponseEntity<List<AuthorsResponseDTO>> searchAuthors(@PathVariable String query) {
        return ResponseEntity.ok(bigBooksService.searchAuthors(query));
    }

    @GetMapping("/similar/{id}")
    ResponseEntity<List<BookResponseDTO>> searchAuthors(@PathVariable int id) {
        return ResponseEntity.ok(bigBooksService.getSimilarBooks(id));
    }

    @GetMapping("/get/{title}")
    ResponseEntity<List<BookResponseDTO>> getBook(@PathVariable String title) {
        return new ResponseEntity<>(booksService.getBook(title), HttpStatus.OK);
    }


}
