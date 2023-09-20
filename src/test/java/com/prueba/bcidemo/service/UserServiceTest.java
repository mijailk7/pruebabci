package com.prueba.bcidemo.service;

import com.prueba.bcidemo.model.entity.Phones;
import com.prueba.bcidemo.model.request.PhoneRequest;
import com.prueba.bcidemo.model.request.UserLoginRequest;
import com.prueba.bcidemo.model.request.UserRequest;
import com.prueba.bcidemo.service.impl.UserServiceImplement;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void createUser(){

        List<PhoneRequest> phonesList = Arrays.asList(PhoneRequest.builder().number("9123456").citycode("11").build());
        UserRequest userRequest = UserRequest.builder()
            .name("prueba")
            .email("asdas@asda.com")
            .password("12345")
            .phones(phonesList)
            .username("prueba")
            .build();


        userService.createUser(userRequest);

        Assertions.assertTrue(true);
    }

    @Test
    void loginUser(){

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername("1234");
        userLoginRequest.setPassword("1234");

        userService.loginUser(userLoginRequest);

        Assertions.assertTrue(true);
    }
}
