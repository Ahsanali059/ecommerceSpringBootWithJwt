package com.example.ecommerceapplication.services;

import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.dto.UserResponse;
import com.example.ecommerceapplication.repositories.AddressRepo;
import com.example.ecommerceapplication.repositories.RoleRepo;
import com.example.ecommerceapplication.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ecommerceapplication.services.CartService;

@Transactional
@Service
public class userServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        return null;
    }

    @Override
    public String deleteUser(Long userId) {
        return "";
    }


    /*
     modal mapper is java library that is used to map object from one place to another

     */




}
