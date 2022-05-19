package com.kabir.mydemoproject.repository;

import com.kabir.mydemoproject.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateReadDeleteUser() {
        User userDto = new User();
        userDto.setUsername("milton");
        userDto.setEmail("sanzam@welldev.io");
        userDto.setPassword("123456");
        userRepository.save(userDto);
    }

    @Test
    public void testReadAllUser() {
        Iterable<User> employees = userRepository.findAll();
        Assertions.assertThat(employees).extracting(User::getUsername).contains("sanzam");

//        userRepository.deleteAll();
//        Assertions.assertThat(userRepository.findAll()).isEmpty();
    }


    @Test
    void findByEmail() {
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void existsByUsername() {
    }
}