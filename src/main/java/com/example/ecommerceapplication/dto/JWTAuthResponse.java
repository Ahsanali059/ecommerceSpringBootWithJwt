package com.example.ecommerceapplication.dto;

import lombok.Data;

@Data
public class JWTAuthResponse {
	private String token;
	private UserDTO user;
}