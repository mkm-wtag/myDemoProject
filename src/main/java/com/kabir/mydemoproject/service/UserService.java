package com.kabir.mydemoproject.service;


import com.kabir.mydemoproject.dto.*;
import com.kabir.mydemoproject.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> getUsers();

    User getUser(Long id);

    void save(User user);

    User saveUser(UserDto userDto);

    MyResponse deleteUser(Long id);

    User updateUser(Long id, Password password);

    JwtResponse login(LoginRequest loginRequest);
}
