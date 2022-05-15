package com.kabir.mydemoproject.service;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.exception.ErrorMessage;
import com.kabir.mydemoproject.exception.ResourceNotFoundException;
import com.kabir.mydemoproject.models.ERole;
import com.kabir.mydemoproject.models.Role;
import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private SeatService seatService;

    @Autowired UserService userService;

    @Autowired RoleService roleService;

    @Override
    public List<Seat> createHall(int numberOfSeats) {
        for (int i = 0; i < numberOfSeats; i++) {
            Seat seat = new Seat();
            seat.setBooked(false);
            seatService.create(seat);
            for(int j=0;j<100000;j++){
                System.out.println();
            }
        }
        return seatService.getAllSeats();
    }

    @Override
    public MyResponse makeAdmin(Long id) {
        User user = userService.getUser(id);
        Optional<Role> userRole = roleService.findByName(ERole.ROLE_ADMIN);
        if(!userRole.isPresent()){
            throw new ResourceNotFoundException(new ErrorMessage("No Role Found"));
        }
        Set<Role> userRoles=user.getRoles();
        userRoles.add(userRole.get());
        user.setRoles(userRoles);
        userService.save(user);
        return new MyResponse(user.getUsername()+" has been made an admin.");
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getUsers();
    }
}
