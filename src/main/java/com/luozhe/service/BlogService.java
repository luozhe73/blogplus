package com.luozhe.service;

import com.luozhe.dto.BlogQuery;
import com.luozhe.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlogById(Long id);

    List<Blog> getBlog(Blog blog);

    List<Blog> getBlogByBlogQuery(BlogQuery blogQuery);

    List<Blog> getRecomendBlog(Integer size);

    void saveBlog(Blog blog);

    void updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    List<Blog> queryBySearch(String query);

    Blog getAndConvert(Long id);

    List<Blog> queryByTagId(Long tagId);

    Map archiveBlog();

    Integer countBlog();

}
