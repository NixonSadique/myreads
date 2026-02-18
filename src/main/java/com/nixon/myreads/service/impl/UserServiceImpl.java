package com.nixon.myreads.service.impl;

import com.nixon.myreads.dto.request.UserRequestDTO;
import com.nixon.myreads.dto.response.StatsResponse;
import com.nixon.myreads.dto.response.UserResponseDTO;
import com.nixon.myreads.entity.User;
import com.nixon.myreads.entity.enums.Role;
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
        user.setRole(Role.USER);
        repository.save(user);
        return "User created successfully!";
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream().map(
                user -> new UserResponseDTO(user.getId(), user.getEmail(), user.getUsername(), user.getRole().name())
        ).toList();
    }

    @Override
    public UserResponseDTO getUserByID(Long id) {
        var user = repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id " + id + " not found")
                );
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getUsername(), user.getRole().name());
    }

    @Override
    public String deleteById(Long id) {
        repository.deleteById(id);
        return "User Deleted Successfully!";
    }

    @Override
    public String userToAdmin(Long id) {
        User user = repository.findById(id).orElseThrow();
        user.setRole(Role.ADMIN);
        repository.save(user);

        return "User to Admin Successfully!";
    }

    @Override
    public StatsResponse getStats() {
        return new StatsResponse(
                repository.count(),
                repository.countByRole(Role.ADMIN),
                repository.countByRole(Role.USER));
    }
}
