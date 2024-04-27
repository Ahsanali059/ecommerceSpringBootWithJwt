package com.example.ecommerceapplication.controllers;
import com.example.ecommerceapplication.JwtConfig.JWTUtil;
import com.example.ecommerceapplication.dto.LoginCredentials;
import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.exceptions.UserNotFoundException;
import com.example.ecommerceapplication.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class AuthController
{
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerHandler(@Valid @RequestBody UserDTO user) throws UserNotFoundException
    {
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        UserDTO userDTO = userService.registerUser(user);

        String token = jwtUtil.generateToken(userDTO.getEmail());

        return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("jwt-token", token),
                HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@Valid @RequestBody LoginCredentials credentials) {

        UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(), credentials.getPassword());

        authenticationManager.authenticate(authCredentials);

        String token = jwtUtil.generateToken(credentials.getEmail());

        return Collections.singletonMap("jwt-token", token);
    }
}
