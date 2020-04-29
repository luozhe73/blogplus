package com.luozhe.dao;

import com.luozhe.pojo.Tag;
import com.luozhe.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {

    void addTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag(Long id);

    Tag getById(Long id);

    Tag getByName(String name);

    List<Tag> getAllTag();
}
