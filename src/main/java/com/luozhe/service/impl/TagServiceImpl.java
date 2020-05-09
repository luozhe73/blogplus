package com.luozhe.service.impl;

import com.luozhe.dao.TagDao;
import com.luozhe.exception.NotFoundException;
import com.luozhe.pojo.Tag;
import com.luozhe.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Override
    public void saveTag(Tag Tag) {
        tagDao.addTag(Tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDao.getById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.getAllTag();
    }

    @Override
    public  List<Tag> listTopTag(Integer size){
        return tagDao.getTopTag(size);
    }

    @Override
    public void updateTag(Long id, Tag tag) {
        Tag tag1 = tagDao.getById(id);
        if(tag1==null){
            throw new NotFoundException("该标签不存在！");
        }
        BeanUtils.copyProperties(tag,tag1);
        tagDao.updateTag(tag1);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }
}
