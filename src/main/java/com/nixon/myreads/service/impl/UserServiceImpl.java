package com.nixon.myreads.service.impl;

import com.nixon.myreads.dto.request.UserRequestDTO;
import com.nixon.myreads.dto.response.UserResponseDTO;
import com.nixon.myreads.entity.User;
import com.nixon.myreads.exception.BadRequestException;
import com.nixon.myreads.exception.EntityNotFoundException;
import com.nixon.myreads.repository.UserRepository;
import com.nixon.myreads.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String createUser(UserRequestDTO requestDTO) {

        if (repository.existsByEmail(requestDTO.email())) {
            throw new BadRequestException("Email already exists");
        }
        if (repository.existsByUsername(requestDTO.username()))
            throw new BadRequestException("Choose another username");


        User user = new User();
        user.setEmail(requestDTO.email().toLowerCase());
        user.setUsername(requestDTO.username().toLowerCase());
        user.setPassword(passwordEncoder.encode(requestDTO.password()));
        repository.save(user);
        return "User created successfuly!";
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream().map(
                user -> new UserResponseDTO(user.getId(), user.getEmail(), user.getUsername())
        ).toList();
    }

    @Override
    public UserResponseDTO getUserByID(Long id) {
        var user = repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id " + id + " not found")
                );
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getUsername());
    }
}
