package com.luozhe.dao;

import com.luozhe.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author luozhe73
 */
@Mapper
@Repository
public interface UserDao {

    User queryByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
