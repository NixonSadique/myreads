package com.nixon.myreads.service;

import com.nixon.myreads.dto.response.BookResponseDTO;

import java.util.List;

public interface BooksService {
    List<BookResponseDTO> getBook(String word);

    List<BookResponseDTO> getBooksByAuthor(Long id);
}
