package com.example.ecommerceapplication.repositories;


import com.example.ecommerceapplication.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

	Address findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(String country, String state, String city,
																		   String pincode, String street, String buildingName);

}