package com.kabir.mydemoproject.controllers;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.dto.Password;
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
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PatchMapping("{userId}")
    public ResponseEntity<User> changePassword(@PathVariable("userId") Long id, @Valid @RequestBody Password password) {
        return new ResponseEntity<>(userService.updateUser(id, password), HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<MyResponse> deleteUser(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

}