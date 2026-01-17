package com.nixon.myreads.service;

import com.nixon.myreads.dto.request.UserRequestDTO;
import com.nixon.myreads.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    public String createUser(UserRequestDTO requestDTO);

    public List<UserResponseDTO> getAllUsers();

    public UserResponseDTO getUserByID(Long id);
}
