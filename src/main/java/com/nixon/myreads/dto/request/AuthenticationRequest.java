package com.nixon.myreads.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest
        (@Email(message = "Email not valid") String email,
         @Size(min = 8, message = "The password contains at least 8 characters") String password ) {
}
