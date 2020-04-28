package com.luozhe.service.impl;

import com.luozhe.pojo.Type;
import com.luozhe.service.TyepService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TypeServiceImplTest {

    @Autowired
    TyepService tyepService;

    @Test
    void saveType() {
        Type t = new Type();
        t.setName("ss");
        tyepService.saveType(t);
    }

    @Test
    void getType() {
        Type type = tyepService.getType(1l);
        System.out.println(type);
    }

    @Test
    void listType() { System.out.println(tyepService.listType());
    }

    @Test
    void updateType() {
    }

    @Test
    void deleteType() {
    }
}