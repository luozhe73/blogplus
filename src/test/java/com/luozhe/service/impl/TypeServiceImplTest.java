package com.luozhe.service.impl;

import com.luozhe.pojo.Type;
import com.luozhe.service.TypeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TypeServiceImplTest {

    @Autowired
    TypeService typeService;

    @Test
    void saveType() {
        Type t = new Type();
        t.setName("ss");
        typeService.saveType(t);
    }

    @Test
    void getType() {
        Type type = typeService.getTypeById(1l);
        System.out.println(type);
    }

    @Test
    void listType() { System.out.println(typeService.listType());
    }

    @Test
    void updateType() {
    }

    @Test
    void deleteType() {
        typeService.deleteType(17l);
    }
}