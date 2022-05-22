package com.kabir.mydemoproject.controllers;

import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/seats")

public class SeatController {

    @Autowired
    private SeatService seatService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<List<Seat>> viewAllSeat() {
        return new ResponseEntity<>(seatService.getAllSeats(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping
    public ResponseEntity<List<Seat>> makeAllSeatsAvailable() {
        return new ResponseEntity<>(seatService.makeAllSeatsAvailable(), HttpStatus.OK);
    }

}
