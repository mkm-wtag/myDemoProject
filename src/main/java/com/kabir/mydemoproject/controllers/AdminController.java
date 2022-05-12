package com.kabir.mydemoproject.controllers;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.exception.ErrorMessage;
import com.kabir.mydemoproject.exception.ResourceNotFoundException;
import com.kabir.mydemoproject.models.ERole;
import com.kabir.mydemoproject.models.Role;
import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.models.User;
import com.kabir.mydemoproject.repository.SeatRepository;
import com.kabir.mydemoproject.service.RoleService;
import com.kabir.mydemoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/createSeats/{numberOfSeats}")
    public ResponseEntity<List<Seat>> createHall(@PathVariable("numberOfSeats") int numberOfSeats) {
        List<Seat> seatList = new ArrayList<>();
        for (int i = 0; i < numberOfSeats; i++) {
            Seat seat = new Seat();
            seat.setBooked(false);
            seatList.add(seat);
        }
        seatRepository.saveAll(seatList);
        return ResponseEntity.ok(seatList);
    }

    @PostMapping("{userId}")
    public ResponseEntity<MyResponse> makeAdmin(@PathVariable("userId") Long id) {
        User user = userService.getUser(id);
        Optional<Role> userRole = roleService.findByName(ERole.ROLE_ADMIN);
        if(!userRole.isPresent()){
            throw new ResourceNotFoundException(new ErrorMessage("No Role Found"));
        }
        Set<Role> userRoles=user.getRoles();
        userRoles.add(userRole.get());
        user.setRoles(userRoles);
        userService.save(user);
        return ResponseEntity.ok(new MyResponse(user.getUsername()+" has been made an admin."));
    }

}
