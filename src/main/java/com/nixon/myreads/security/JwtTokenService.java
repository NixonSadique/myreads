package com.nixon.myreads.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nixon.myreads.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    @Value("$/{jwt.secret}")
    private String secret;

    public String createToken(User user){
        String email = user.getEmail();
        var expiresAt = new Date(System.currentTimeMillis() + 18000000);
        var createdAt = new  Date(System.currentTimeMillis());

        return JWT.create()
                .withIssuer("myreads-api")
                .withSubject(email)
                .withExpiresAt(expiresAt)
                .withIssuedAt(createdAt)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateToken(String token){
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();
    }
}
