package com.nixon.myreads.service;

import com.nixon.myreads.dto.request.UserRequestDTO;
import com.nixon.myreads.dto.response.StatsResponse;
import com.nixon.myreads.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    String createUser(UserRequestDTO requestDTO);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserByID(Long id);

    String deleteById(Long id);

    String userToAdmin(Long id);

    StatsResponse getStats();
}
