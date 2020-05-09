package com.luozhe.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luozhe.dto.BlogQuery;
import com.luozhe.pojo.Blog;
import com.luozhe.pojo.Type;
import com.luozhe.service.BlogService;
import com.luozhe.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        @PathVariable Long id, Model model) {
        List<Type> types = typeService.listTopType(10000);
        if (id == -1) {
           id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        PageHelper.startPage(pageNum, 8);
        List<Blog> blogs = blogService.getBlogByBlogQuery(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);

        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
