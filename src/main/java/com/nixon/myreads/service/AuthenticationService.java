package com.nixon.myreads.service;

import com.nixon.myreads.dto.request.AuthenticationRequest;
import com.nixon.myreads.dto.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse authenticate(AuthenticationRequest request);
}
