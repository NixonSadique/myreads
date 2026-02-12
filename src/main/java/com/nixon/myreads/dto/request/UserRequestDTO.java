package com.nixon.myreads.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "The Username must not be Blank")
        @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters long." ) String username,
        @Email(message = "Email not valid") String email,
        @Size(min = 8, message = "The password must have at least 8 characters!") String password) {
}
