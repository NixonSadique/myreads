package com.nixon.myreads.service.impl;

import com.nixon.myreads.dto.response.AuthorsResponseDTO;
import com.nixon.myreads.repository.AuthorRepository;
import com.nixon.myreads.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorsResponseDTO> getAuthors() {
        return authorRepository.findAll().stream().map(
                author -> new AuthorsResponseDTO(author.getId(), author.getName())
        ).toList();
    }
}
