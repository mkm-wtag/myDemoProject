package com.kabir.mydemoproject.service;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AdminService {

    List<Seat> createHall(int numberOfSeats);

    MyResponse makeAdmin(Long id);

    List<User> getAllUsers();
}
