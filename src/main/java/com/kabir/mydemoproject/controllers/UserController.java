package com.kabir.mydemoproject.controllers;

import com.kabir.mydemoproject.dto.*;
import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.models.User;
import com.kabir.mydemoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.saveUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PatchMapping("{userId}")
    public ResponseEntity<User> changePassword(@PathVariable("userId") Long id, @Valid @RequestBody Password password) {
        return new ResponseEntity<>(userService.updateUser(id, password), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("{userId}")
    public ResponseEntity<MyResponse> deleteUser(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("{userId}/roles")
    public ResponseEntity<MyResponse> makeAdmin(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(userService.makeAdmin(id));
    }


}
