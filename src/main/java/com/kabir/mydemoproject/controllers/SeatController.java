package com.kabir.mydemoproject.controllers;

import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/seats")
@PreAuthorize("hasRole('USER')")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> viewAllSeat(){
        return seatService.getAllSeats();
    }
}
