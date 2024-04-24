package com.example.ecommerceapplication.repositories;

import com.example.ecommerceapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    @Query("SELECT u FROM User u JOIN FETCH u.addresses a WHERE a.addressId = ?1")
    List<User> findByAddress(Long addressId);

    Optional<User> findByEmail(String email);

}
/*

In Java, Optional is a class introduced in Java 8 (in the java.util package) to handle scenarios where a method might return a result or might return nothing (null). It's a container object that may or may not contain a non-null value.
Here's how it works:

If a user with the specified email address is found in the database, the method returns an Optional containing that user entity.
If no user with the specified email address is found, the method returns an empty Optional.
Using Optional in method return types has several advantages:

1-Null-Safety: It eliminates the need to handle null values explicitly in your code. Instead of returning null when no result is found, the method returns an Optional object, which forces the caller to handle the possibility of an empty result.
2-AvoidingNullPointer Exceptions 

 */
