package com.kabir.mydemoproject.service;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.models.Seat;

import java.util.List;

public interface SeatService {
    void create(Seat seat);

    List<Seat> getAllSeats();

    MyResponse update(Long id);

    Seat getSeat(Long id);
}
