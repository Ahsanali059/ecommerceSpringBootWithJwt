package com.example.ecommerceapplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 5, max = 20, message = "First Name must be between 5 and 30 characters long")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must not contain numbers or special characters")
    private String firstName;

    @Size(min = 5, max = 20, message = "Last Name must be between 5 and 30 characters long")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name must not contain numbers or special characters")
    private String lastName;

    @Size(min = 10, max = 10, message = "Mobile Number must be exactly 10 digits long")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number must contain only Numbers")
    private String mobileNumber;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private Cart cart;

}

 /*
    start relationship
    1-CascadeType.PERSIST:In the context of database operations and object-relational mapping (ORM) frameworks like Java Persistence API (JPA), "cascade" refers to the behavior where certain operations performed on one entity (or object) result in corresponding operations being applied to associated entities (or objects) automatically.
    CascadeType.when a new entity is persist saved to the database using EntityManager.persist()
    this cascade is specified this operation is also applied to associated entities
    In other words, if Entity A is persisted, any associated Entity B should also be persisted.
   2- CascadeType.MERGE:when entity is merge means updated in the database using EntityManager.merge()
    this cascade type secifies that the operation should also be applied to associated entities
    if detected entity is merge/updated in persistance context any changes made to the associated entities should also be merged
   3-CascadeType.REMOVE:When entity is removed from database EntityManager.remove()
   this cascade type specifies that the operation should also be applied to associated entities. If Entity A is removed, any associated Entity B should also be removed.

   4-CascadeType.REFRESH:When an entity is refreshed (i.e., reloaded from the database) using EntityManager.refresh(), this cascade type specifies that the operation should also be applied to associated entities.

   5-CascadeType.ALL: This cascade type specifies that all operations (persist, merge, remove, refresh) should be cascaded to associated entities.

   FetchType.EAGER: conjunction with associations such as @OneToOne, @OneToMany, and @ManyToOne.
   When  FetchType.EAGER is used it indicate that when feching s used, it indicates that the associated entities should be loaded eagerly along with the owning entity.
   This means that when an entity is retrieved from the database, JPA will automatically fetch its associated entities in the same database query.
     */
