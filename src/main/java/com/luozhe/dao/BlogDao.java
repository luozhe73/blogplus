package com.luozhe.dao;

import com.alibaba.druid.support.logging.NoLoggingImpl;
import com.luozhe.dto.BlogAndTag;
import com.luozhe.dto.BlogQuery;
import com.luozhe.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao {

    Blog queryById(@Param("id") Long id);

    List<Blog> queryAll(Blog blog);

    List<Blog> queryByBlogQuery(BlogQuery blogQuery);

    void insert(Blog blog);

    void update(Blog blog);

    void deleteById(@Param("id") Long id);

    void saveBlogAndTag(BlogAndTag blogAndTag );

    List<BlogAndTag> queryBlogAndTag(Long blogsId);

    List<Blog> queryRecomendBlog(Integer size);

    void deleteBlogAndTag(Long blogsId);

    List<Blog> searchBlogs(String query);

    List<Blog> queryByTagId(Long tagId);

    List<String> queryAllYears();

    List<Blog> queryBlogByYear(String year);
}
