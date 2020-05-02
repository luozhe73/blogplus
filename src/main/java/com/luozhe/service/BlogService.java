package com.luozhe.service;

import com.luozhe.dto.BlogQuery;
import com.luozhe.pojo.Blog;

import java.util.List;

public interface BlogService {

    Blog getBlogById(Long id);

    List<Blog> getBlog(Blog blog);

    List<Blog> getBlogByBlogQuery(BlogQuery blogQuery);

    void saveBlog(Blog blog);

    void updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
