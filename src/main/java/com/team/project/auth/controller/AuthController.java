package com.team.project.auth.controller;

import com.team.project.auth.dto.TokenDTO;

import com.team.project.auth.service.TokenService;
import com.team.project.user.dto.UserDTO;
import com.team.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO userDTO) throws IOException {
        TokenDTO tokenResponse = tokenService.login(userDTO);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        if(userService.existsByEmail(userDTO.getEmail())){
            return ResponseEntity.badRequest().body("Użytkownik z takim emailem już istnieje.");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.register(userDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
