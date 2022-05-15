package com.kabir.mydemoproject.controllers;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.models.User;
import com.kabir.mydemoproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/createSeats/{numberOfSeats}")
    public ResponseEntity<List<Seat>> createHall(@PathVariable("numberOfSeats") int numberOfSeats) {
        return  ResponseEntity.ok(adminService.createHall(numberOfSeats));
    }

    @PostMapping("{userId}")
    public ResponseEntity<MyResponse> makeAdmin(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(adminService.makeAdmin(id));
    }

    @GetMapping("allusers")
    public ResponseEntity<List<User>> getAllUsers() {
        return  ResponseEntity.ok(adminService.getAllUsers());
    }

}
