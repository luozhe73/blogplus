package com.luozhe.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luozhe.pojo.Blog;
import com.luozhe.pojo.Tag;
import com.luozhe.service.BlogService;
import com.luozhe.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                       @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTopTag(1000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        Blog b = new Blog();
        PageHelper.startPage(pageNum, 8);
        List<Blog> blogs = blogService.queryByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);

        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
