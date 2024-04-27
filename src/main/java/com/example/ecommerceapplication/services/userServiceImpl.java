package com.example.ecommerceapplication.services;

import com.example.ecommerceapplication.repositories.AddressRepo;
import com.example.ecommerceapplication.repositories.RoleRepo;
import com.example.ecommerceapplication.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ecommerceapplication.services.CartService;

@Service
public class userServiceImpl
{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AddressRepo addressRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;


    /*
     modal mapper is java library that is used to map object from one place to another

     */




}
