package com.luozhe.service;

import com.luozhe.pojo.Type;

import java.util.List;

public interface TyepService {

    void saveType(Type type);

    Type getType(Long id);

    List<Type> listType();

    void updateType(Long id,Type type);

    void deleteType(Long id);
}
