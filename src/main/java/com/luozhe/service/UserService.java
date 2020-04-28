package com.luozhe.service;

import com.luozhe.pojo.User;

public interface UserService {

    /**
     * 检查用户信息
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username,String password);
}
