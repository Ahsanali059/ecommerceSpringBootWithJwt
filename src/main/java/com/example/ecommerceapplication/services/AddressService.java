package com.example.ecommerceapplication.services;

import com.example.ecommerceapplication.dto.AddressDTO;
import com.example.ecommerceapplication.entities.Address;

import java.util.List;

public interface AddressService {

    AddressDTO createAddress(AddressDTO addressDTO);

    List<AddressDTO> getAddresses();

    AddressDTO getAddress(Long addressId);

    AddressDTO updateAddress(Long addressId, Address address);

    String deleteAddress(Long addressId);
}
