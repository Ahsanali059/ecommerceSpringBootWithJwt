package com.example.ecommerceapplication.JwtConfig;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ecommerceapplication.jwtServices.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JWTFilter extends OncePerRequestFilter
{
    @Autowired
    private JWTUtil jwtUtil;


    @Autowired
    public UserDetailsServiceImpl userDetailsServiceImpl;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
          //first get Header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer "))
        {
            String jwtToken = authHeader.substring(7);

            if(jwtToken == null || jwtToken.isBlank())
            {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid JWT token");
            }
            else
            {
                //if not empty
                try
                {
                    String email = jwtUtil.validateTokenAndRetieveSubject(jwtToken);

                    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,userDetails.getPassword(),userDetails.getAuthorities());

                    if (SecurityContextHolder.getContext().getAuthentication() == null)
                    {
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    }

                }
                catch(JWTVerificationException e)
                {
                   response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid JWT token");

                }
            }

        }

        filterChain.doFilter(request,response);

    }
}
