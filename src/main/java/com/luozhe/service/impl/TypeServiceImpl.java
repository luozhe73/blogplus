package com.luozhe.service.impl;

import com.luozhe.dao.TypeDao;
import com.luozhe.exception.NotFoundException;
import com.luozhe.pojo.Type;
import com.luozhe.service.TyepService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TyepService {

    @Autowired
    TypeDao typeDao;

    @Override
    public void saveType(Type type) {
         typeDao.addType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getById(id);
    }

    @Override
    public List<Type> listType() {
        return typeDao.getAllType();
    }

    @Override
    public void updateType(Long id, Type type) {
        Type type1=typeDao.getById(id);
        if (type1==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,type1);
         typeDao.updateType(type1);
    }

    @Override
    public void deleteType(Long id) {
         typeDao.deleteType();
    }
}
