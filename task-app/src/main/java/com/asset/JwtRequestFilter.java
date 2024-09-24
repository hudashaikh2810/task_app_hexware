package com.asset;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.asset.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
/*Once the token is created then after that every request that user make the token gets added in header in authorization so we get header*/
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
/*if token is present authorization will not be null and we also check that this header starts with bearer*/
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        	/*Then we extract the token */
            jwt = authorizationHeader.substring(7);
            /*in jwtUtil class we have a method to extract username from this token*/
            username = jwtUtil.extractUsername(jwt);
        }
//*The we check this username is null or not also we check whether there is a currently authenticated user i.e there should be no currently authenticated users*/
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
/*From username get UserInfo and store it in UserDetails class which contains information of user*/
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
/*Validate token whether username is token is same as username we get from above*/
            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
/*If token is valid we create UserNamePasswordAuthentication token with userdetails and roles etc*/
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                /*Set the currently authenticated uses to UsernamePassWordAuthentication*/
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        /*process the other filters as there can be many filter available*/
        chain.doFilter(request, response);
    }
}