package com.kabir.mydemoproject.service;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.exception.ErrorMessage;
import com.kabir.mydemoproject.exception.ResourceNotFoundException;
import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;


    @Override
    public void create(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    public List<Seat> getAllSeats() {
        List<Seat> seatList = new ArrayList<>();
        seatRepository.findAll().forEach(seatList::add);
        return seatList;
    }

    @Override
    public MyResponse update(Long id) {
        Optional<Seat> optionalSeat=seatRepository.findById(id);
        if(optionalSeat.isPresent()){
            Seat seat= optionalSeat.get();
            if(seat.isBooked()){
                seat.setBooked(false);
                seatRepository.save(seat);
                return new MyResponse("The Seat is freed.");
            }
            else{
                seat.setBooked(true);
                seatRepository.save(seat);
                return new MyResponse("The Seat is booked");
            }
        }
        else{
            throw new ResourceNotFoundException(new ErrorMessage("Not Seat found with id "+id));
        }
    }


    @Override
    public Seat getSeat(Long id) {
        Optional<Seat> optionalSeat=seatRepository.findById(id);
        if(optionalSeat.isPresent()){
            return optionalSeat.get();
        }
        else{
            throw new ResourceNotFoundException(new ErrorMessage("Not Seat found with id "+id));
        }
    }
}
