package com.nixon.myreads.service.impl;

import com.nixon.myreads.client.response.AuthorsResponse;
import com.nixon.myreads.dto.response.BookResponseDTO;
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
                book -> new BookResponseDTO(book.getId(), book.getTitle(), book.getImage(),
                        book.getAuthor().stream().map(
                                author -> new AuthorsResponse(author.getId(), author.getName())).toList()
                )
        ).toList();
    }

    @Override
    public List<BookResponseDTO> getBooksByAuthor(Long id) {
        return repository.findByAuthorId(id).stream().map(
                book -> new BookResponseDTO(book.getId(), book.getTitle(), book.getImage(),
                        book.getAuthor().stream().map(
                                author -> new AuthorsResponse(author.getId(), author.getName())).toList()
                )
        ).toList();
    }
}
