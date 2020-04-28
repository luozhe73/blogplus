package com.luozhe.service;

import com.luozhe.pojo.Type;

import java.util.List;

public interface TypeService {

    void saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> listType();

    void updateType(Long id,Type type);

    void deleteType(Long id);
}
