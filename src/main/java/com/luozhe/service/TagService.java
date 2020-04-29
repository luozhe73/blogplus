package com.luozhe.service;

import com.luozhe.pojo.Tag;

import java.util.List;

public interface TagService {

    void saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    void updateTag(Long id,Tag tag);

    void deleteTag(Long id);
}
