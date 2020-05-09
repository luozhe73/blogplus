package com.luozhe.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luozhe.pojo.Blog;
import com.luozhe.service.BlogService;
import com.luozhe.service.TagService;
import com.luozhe.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        PageHelper.offsetPage(pageNum, 8);
        Blog blog = new Blog();
        blog.setPublished(true);
        List<Blog> blogList = blogService.getBlog(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.listTopType(6));
        model.addAttribute("tags", tagService.listTopTag(10));
        model.addAttribute("recommendBlogs", blogService.getRecomendBlog(5));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(Model model, @PathVariable("id") Long id) {

        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.offsetPage(pageNum, 8);
        List<Blog> blogs = blogService.queryBySearch(query);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }


}
