package com.nixon.myreads.service.impl;

import com.nixon.myreads.dto.response.BookResponseDTO;
import com.nixon.myreads.entity.Book;
import com.nixon.myreads.repository.BookRepository;
import com.nixon.myreads.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookRepository repository;

    @Override
    public List<BookResponseDTO> getBook(String word) {
        return repository.findByTitleContainingIgnoreCase(word).stream().map(
                book -> new BookResponseDTO(book.getId(), book.getTitle(),book.getImage(),null)
        ).toList();
    }
}
