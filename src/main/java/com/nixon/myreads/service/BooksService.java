package com.nixon.myreads.service;

import com.nixon.myreads.dto.response.BookResponseDTO;

import java.util.List;

public interface BooksService {
    public List<BookResponseDTO> getBook(String word);
}
