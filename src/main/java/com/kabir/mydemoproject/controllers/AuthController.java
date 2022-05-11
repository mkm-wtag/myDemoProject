package com.kabir.mydemoproject.controllers;

import com.kabir.mydemoproject.dto.JwtResponse;
import com.kabir.mydemoproject.dto.LoginRequest;
import com.kabir.mydemoproject.dto.UserDto;
import com.kabir.mydemoproject.models.User;
import com.kabir.mydemoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.saveUser(userDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse jwtResponse = userService.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

}
