package com.nixon.myreads.service;

import com.nixon.myreads.dto.request.BookProgressRequestDTO;
import com.nixon.myreads.dto.request.ProgressUpdateRequestDTO;
import com.nixon.myreads.dto.response.BookProgressResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;
import com.nixon.myreads.dto.response.UserResponseDTO;

import java.util.List;

public interface BookProgressService {
    public String createProgress(BookProgressRequestDTO progressRequestDTO);

    public String updateCompletion(ProgressUpdateRequestDTO request);

    public BookProgressResponseDTO getBookProgressById(Long id);

    List<BookProgressResponseDTO> getUserProgresses(Long id);
}
