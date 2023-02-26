package com.team.project.auth.service;

import com.team.project.auth.dto.TokenDTO;
import com.team.project.auth.security.jwt.JwtUtils;
import com.team.project.user.dto.UserDTO;
import com.team.project.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Value("${secretPassword}")
    String secretPassword;

    public TokenDTO login(UserDTO user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setValue(jwt);
        return tokenDTO;
    }
}
