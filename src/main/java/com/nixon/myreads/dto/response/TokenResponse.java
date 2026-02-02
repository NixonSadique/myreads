package com.nixon.myreads.dto.response;

public record TokenResponse(String token, java.util.Date expiresAt, UserResponseDTO user) {
}
