package com.example.ecommerceapplication.JwtConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil
{
    @Value("${jwt_secret}")
    private String secretKey;

    public String generateToken(String email)
    {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email",email)
                .withIssuedAt(new Date())
                .withIssuer("Event Handler")
                .sign(Algorithm.HMAC256(secretKey));
    }

}
