package com.luozhe.service.impl;

import com.luozhe.pojo.User;
import com.luozhe.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void checkUser() {
        User user = userService.checkUser("luozhe73", "5588");
        System.out.println(user);
    }
}