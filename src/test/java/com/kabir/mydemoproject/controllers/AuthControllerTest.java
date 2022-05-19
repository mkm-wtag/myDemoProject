package com.kabir.mydemoproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kabir.mydemoproject.dto.LoginRequest;
import com.kabir.mydemoproject.dto.UserDto;
import com.kabir.mydemoproject.models.ERole;
import com.kabir.mydemoproject.models.Role;
import com.kabir.mydemoproject.models.Ticket;
import com.kabir.mydemoproject.models.User;
import com.kabir.mydemoproject.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    User testUser1 = new User(1L, "milton", "milton@welldev.io", "123456",
            new HashSet<Role>() {{
        add(new Role() {{
            setId(1L);
            setName(ERole.ROLE_USER);
        }});
        add(new Role() {{
            setId(2L);
            setName(ERole.ROLE_ADMIN);
        }});
    }},
            new ArrayList<Ticket>());

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("sanzam");
        userDto.setEmail("sanzam@welldev.io");
        userDto.setPassword("123456");

        User user=userService.saveUser(userDto);
//        Assert.assertTrue(user.getUsername("sanzam"));
    }

    @Test()
    void testRegisterUserInvalidRequestBody() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("milton");
        userDto.setEmail("milton@welldev.io");

        mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void testAuthenticateUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest("milton", "123123");
        mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testAuthenticateUserInvalidRequestBody() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("milton");
        mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnprocessableEntity());
    }

}