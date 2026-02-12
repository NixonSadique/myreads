package com.nixon.myreads.client;

import com.nixon.myreads.dto.response.AuthorsResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;

import java.util.List;

public interface BigBooksService {
    List<BookResponseDTO> searchBooks(String query);
    List<AuthorsResponseDTO> searchAuthors(String query);
    List<BookResponseDTO> getSimilarBooks(Long id);


}
