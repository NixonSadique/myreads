package com.nixon.myreads.service;

import com.nixon.myreads.dto.response.AuthorsResponseDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorsResponseDTO> getAuthors();
}
