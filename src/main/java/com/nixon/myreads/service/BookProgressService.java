package com.nixon.myreads.service;

import com.nixon.myreads.dto.request.BookProgressRequestDTO;
import com.nixon.myreads.dto.response.BookProgressResponseDTO;
import com.nixon.myreads.dto.response.BookResponseDTO;
import com.nixon.myreads.dto.response.UserResponseDTO;

public interface BookProgressService {
    public String createProgress(BookProgressRequestDTO progressRequestDTO);

    public String updateCompletion(Long id,Double completion);

    public BookProgressResponseDTO getBookProgressById(Long id);

        public UserResponseDTO getUserByProgressId(Long id);

    public BookResponseDTO getBookByProgressId(Long id);
}
