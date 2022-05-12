package com.kabir.mydemoproject.service;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.exception.ErrorMessage;
import com.kabir.mydemoproject.exception.ResourceNotFoundException;
import com.kabir.mydemoproject.exception.SeatAlreadyBookedException;
import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.models.Ticket;
import com.kabir.mydemoproject.models.User;
import com.kabir.mydemoproject.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private UserService userService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public List<Ticket> getAllTickets(Long userId) {
        User user=userService.getUser(userId);
        return user.getTickets();
    }

    @Override
    public Ticket getTicket(Long userId, Long ticketId) {
        User user=userService.getUser(userId);
        List<Ticket> ticketList=user.getTickets();
        Ticket ticket=null;
        for(Ticket currentTicket:ticketList){
            if(currentTicket.getId().equals(ticketId)){
                ticket=currentTicket;
                break;
            }
        }
        if(ticket==null){
            throw new ResourceNotFoundException(new ErrorMessage("No Ticket Found with id "+ticketId));
        }
        return ticket;
    }

    @Override
    public Ticket create(Long userId, Ticket ticket) {
        User user=userService.getUser(userId);
        Long seatId=ticket.getSeatId();

        Seat seat=seatService.getSeat(seatId);
        if(!seat.isBooked()){
            seat.setBooked(true);
            user.getTickets().add(ticket);
            ticket.setUser(user);
            ticketRepository.save(ticket);
            userService.save(user);
        }
        else{
            throw new SeatAlreadyBookedException(new ErrorMessage("Seat is already booked by someone else"));
        }
        return ticket;
    }

    @Override
    public MyResponse delete(Long userId, Long ticketId) {
        User user=userService.getUser(userId);
        List<Ticket> ticketList=user.getTickets();
        Ticket ticket=null;
        for(Ticket currentTicket:ticketList){
            if(currentTicket.getId().equals(ticketId)){
                ticket=currentTicket;
                break;
            }
        }
        if(ticket==null){
            throw new ResourceNotFoundException(new ErrorMessage("No Ticket Found with id "+ticketId));
        }
        Long seatId=ticket.getSeatId();
        Seat seat=seatService.getSeat(seatId);
        seat.setBooked(false);
        ticketList.remove(ticket);
        user.setTickets(ticketList);
        userService.save(user);
        ticketRepository.delete(ticket);
        return new MyResponse("The ticket with ticketId "+ticketId+" has been deleted");
    }
}
