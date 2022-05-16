package com.kabir.mydemoproject.service;

import com.kabir.mydemoproject.dto.*;
import com.kabir.mydemoproject.exception.CustomerLoginException;
import com.kabir.mydemoproject.exception.ErrorMessage;
import com.kabir.mydemoproject.exception.ResourceNotFoundException;
import com.kabir.mydemoproject.models.ERole;
import com.kabir.mydemoproject.models.Role;
import com.kabir.mydemoproject.models.Seat;
import com.kabir.mydemoproject.models.User;
import com.kabir.mydemoproject.repository.RoleRepository;
import com.kabir.mydemoproject.repository.UserRepository;
import com.kabir.mydemoproject.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeatService seatService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User getUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long idFromJwt = Long.parseLong(String.valueOf(authentication.getPrincipal()));
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!idFromJwt.equals(id)&&!roles.contains("ROLE_ADMIN")) {
            throw new AccessDeniedException("You are not supposed to do this.");
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ResourceNotFoundException(new ErrorMessage("No user found with id " + id));
        }
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User saveUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new CustomerLoginException(new ErrorMessage("Email Already used"));
        }
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new CustomerLoginException(new ErrorMessage("Username already exists"));
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
        if (!userRole.isPresent()) {
            throw new ResourceNotFoundException(new ErrorMessage("No Role Found"));
        }
        roles.add(userRole.get());
        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }

    @Override
    public MyResponse deleteUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long idFromJwt = Long.parseLong(String.valueOf(authentication.getPrincipal()));
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!idFromJwt.equals(id)&&!roles.contains("ROLE_ADMIN")) {
            throw new AccessDeniedException("You are not supposed to do this.");
        }


        Optional<User> optionalUser = userRepository.findById(id);
        if (!idFromJwt.equals(id) || !optionalUser.isPresent()) {
            throw new AccessDeniedException("You are not supposed to do this.");
        }
        userRepository.deleteById(id);
        return new MyResponse("Successfully deleted user");
    }

    @Override
    public User updateUser(Long id, Password password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long idFromJwt = Long.parseLong(String.valueOf(authentication.getPrincipal()));
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (!idFromJwt.equals(id)&&!roles.contains("ROLE_ADMIN")) {
            throw new AccessDeniedException("You are not supposed to do this.");
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(encoder.encode(password.getUserPassword()));
            return userRepository.save(user);
        } else {
            throw new ResourceNotFoundException(new ErrorMessage("User doesn't exist."));
        }
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new JwtResponse(jwt, "Bearer");
    }

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
        User user = userRepository.getById(id);
        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        if(!userRole.isPresent()){
            throw new ResourceNotFoundException(new ErrorMessage("No Role Found"));
        }
        Set<Role> userRoles=user.getRoles();
        userRoles.add(userRole.get());
        user.setRoles(userRoles);
        userRepository.save(user);
        return new MyResponse(user.getUsername()+" has been made an admin.");
    }
}
