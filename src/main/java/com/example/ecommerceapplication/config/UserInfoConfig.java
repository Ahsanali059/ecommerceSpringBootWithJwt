package com.example.ecommerceapplication.config;


import com.example.ecommerceapplication.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoConfig implements UserDetails
{
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;


    public UserInfoConfig(User user)
    {
        this.email = email;
        this.password = password;
        this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
    /*
     1-stream->In summary, streams provide a modern and efficient way to process collections of data in Java,
     offering benefits such as readability, functional programming style, laziness, parallelism, and interoperability with other language features. They have become an essential part of Java programming, especially in modern Java development.

     2-map:map work in intermediate operation that transform function to each element of the stram
     and produce a new stream

     3-collect:It collects the elements of the stream into a mutable result container, such as a List, Set, Map, or any other custom collection.


     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
       return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
