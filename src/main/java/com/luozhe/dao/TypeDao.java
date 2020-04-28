package com.luozhe.dao;

import com.luozhe.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    void addType(Type type);

    void updateType(Type type);

    void deleteType();

    Type getById(Long id);

    List<Type> getAllType();
}
