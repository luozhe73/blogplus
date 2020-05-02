package com.luozhe.service.impl;

import com.luozhe.dao.BlogDao;
import com.luozhe.pojo.Blog;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogServiceImplTest {

    @Autowired
    BlogDao blogDao;

    @Test
    void test(){
        blogDao.queryAll(new Blog()).forEach(System.out::println);
    }
}