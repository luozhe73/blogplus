package com.luozhe.service.impl;

import com.luozhe.dao.UserDao;
import com.luozhe.pojo.User;
import com.luozhe.service.UserService;
import com.luozhe.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
