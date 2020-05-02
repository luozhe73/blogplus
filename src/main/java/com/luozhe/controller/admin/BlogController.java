package com.luozhe.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luozhe.dto.BlogQuery;
import com.luozhe.pojo.Blog;
import com.luozhe.pojo.User;
import com.luozhe.service.BlogService;
import com.luozhe.service.TagService;
import com.luozhe.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model,
                        BlogQuery blogQuery,
                        @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 3);
        List<Blog> blogs = blogService.getBlogByBlogQuery(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
        model.addAttribute(pageInfo);
        model.addAttribute("types", typeService.listType());
        return "admin/blogs";
    }

    @RequestMapping("/blogs/search")
    public String search(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         BlogQuery blogQuery) {
        PageHelper.startPage(pageNum, 3);
        List<Blog> blogs = blogService.getBlogByBlogQuery(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
        model.addAttribute(pageInfo);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog,
                       RedirectAttributes redirectAttributes,
                       HttpSession session) {
        try {
            //设置blog中typeId属性
            blog.setTypeId(blog.getType().getId());
            blog.setUser((User) session.getAttribute("user"));
            blogService.saveBlog(blog);
            redirectAttributes.addFlashAttribute("message", "操作成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "操作失败！");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/input")
    public String edit(Model model,
                       @PathVariable("id") Long id) {
        setTypeAndTag(model);
        Blog blogById = blogService.getBlogById(id);
        model.addAttribute("blog", blogById);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(Model model,
                       RedirectAttributes redirectAttributes,
                       @PathVariable("id") Long id) {

        try {
            blogService.deleteBlog(id);
            redirectAttributes.addFlashAttribute("message", "删除成功！");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "删除失败！");
        }
        return "redirect:/admin/blogs";
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }


}
