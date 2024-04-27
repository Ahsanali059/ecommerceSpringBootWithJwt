package com.example.ecommerceapplication.services;

import com.example.ecommerceapplication.constants.AppConstants;
import com.example.ecommerceapplication.dto.AddressDTO;
import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.dto.UserResponse;
import com.example.ecommerceapplication.entities.Address;
import com.example.ecommerceapplication.entities.Cart;
import com.example.ecommerceapplication.entities.Role;
import com.example.ecommerceapplication.entities.User;
import com.example.ecommerceapplication.exceptions.APIException;
import com.example.ecommerceapplication.repositories.AddressRepo;
import com.example.ecommerceapplication.repositories.RoleRepo;
import com.example.ecommerceapplication.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ecommerceapplication.services.CartService;

import java.util.List;

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
    private CartService cartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserDTO userDTO)
    {
        try
        {
            User user = modelMapper.map(userDTO, User.class);
            Cart cart = new Cart();
            user.setCart(cart);

            Role role = roleRepo.findById(AppConstants.USER_ID).get();
            user.getRoles().add(role);

            String country = userDTO.getAddress().getCountry();
            String state = userDTO.getAddress().getState();
            String city = userDTO.getAddress().getCity();
            String pincode = userDTO.getAddress().getPincode();
            String street = userDTO.getAddress().getStreet();
            String buildingName = userDTO.getAddress().getBuildingName();

            Address address = addressRepo.findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(country, state,
                    city, pincode, street, buildingName);

            if (address == null) {
                address = new Address(country, state, city, pincode, street, buildingName);

                address = addressRepo.save(address);
            }

            user.setAddresses(List.of(address));
            User savedUser = userRepo.save(user);
            cart.setUser(savedUser);
            userDTO = modelMapper.map(savedUser, UserDTO.class);
            userDTO.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDTO.class));

            return userDTO;

        }
        catch (DataIntegrityViolationException e)
        {
            throw new APIException("User already exists with emailId: " + userDTO.getEmail());
        }

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
