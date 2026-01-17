package com.nixon.myreads.dto.response;

public record BookProgressResponseDTO(Long id, Double completion, UserResponseDTO user, BookResponseDTO book) {
}
