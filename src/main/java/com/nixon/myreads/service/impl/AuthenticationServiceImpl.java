package com.nixon.myreads.service.impl;

import com.nixon.myreads.dto.request.AuthenticationRequest;
import com.nixon.myreads.dto.response.TokenResponse;
import com.nixon.myreads.dto.response.UserResponseDTO;
import com.nixon.myreads.entity.Token;
import com.nixon.myreads.entity.User;
import com.nixon.myreads.exception.EntityNotFoundException;
import com.nixon.myreads.repository.TokenRepository;
import com.nixon.myreads.repository.UserRepository;
import com.nixon.myreads.security.JwtTokenService;
import com.nixon.myreads.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenRepository tokenRepository;
    private final JwtTokenService tokenService;
    private final UserRepository userRepository;
    private final AuthenticationManager manager;

    @Override
    public TokenResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(
                () -> new EntityNotFoundException("User not found with email: " + request.email())
        );

        manager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        String accessToken = tokenService.createToken(user);

        Token token = new Token();
        token.setToken(accessToken);
        token.setUser(user);
        token.setCreatedAt(new Date(System.currentTimeMillis()));
        token.setExpiresAt(new Date(System.currentTimeMillis() + 18000000));

        tokenRepository.save(token);

        return new TokenResponse(accessToken, token.getExpiresAt(), new UserResponseDTO(
                token.getUser().getId(),
                token.getUser().getEmail(),
                token.getUser().getUsername(),
                token.getUser().getRole().name()
        ));
    }
}
