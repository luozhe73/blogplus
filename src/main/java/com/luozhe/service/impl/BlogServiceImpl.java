package com.luozhe.service.impl;

import com.luozhe.dao.BlogDao;
import com.luozhe.dao.TagDao;
import com.luozhe.dao.TypeDao;
import com.luozhe.dto.BlogAndTag;
import com.luozhe.dto.BlogQuery;
import com.luozhe.exception.NotFoundException;
import com.luozhe.pojo.Blog;
import com.luozhe.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    TypeDao typeDao;

    @Autowired
    TagDao tagDao;

    @Override
    public Blog getBlogById(Long id) {
        List<BlogAndTag> blogAndTags = blogDao.queryBlogAndTag(id);
        String tags = "";
        for (BlogAndTag blogAndTag : blogAndTags) {
            tags += blogAndTag.getTagsId() + ",";
        }
        if (tags.length()>1){
            tags = tags.substring(0, tags.length() - 1);
        }
        Blog blog = blogDao.queryById(id);
        blog.setTagIds(tags);
        return blog;
    }

    @Override
    public List<Blog> getBlog(Blog blog) {

        return blogDao.queryAll(blog);
    }

    @Override
    public List<Blog> getBlogByBlogQuery(BlogQuery blogQuery) {

        return blogDao.queryByBlogQuery(blogQuery);
    }

    @Override
    public void saveBlog(Blog blog) {

        blog.setUpdateTime(new Date());
        if (blog.getId()!=null){
            blogDao.update(blog);
            blogDao.deleteBlogAndTag(blog.getId());
            for (String tagId : blog.getTagIds().split(",")) {
                BlogAndTag blogAndTag = new BlogAndTag(blog.getId(), Long.parseLong(tagId));
                blogDao.saveBlogAndTag(blogAndTag);
            }
        }else{
            blog.setCreateTime(new Date());
            blog.setViews(0);
            blogDao.insert(blog);
            for (String tagId : blog.getTagIds().split(",")) {
                BlogAndTag blogAndTag = new BlogAndTag(blog.getId(), Long.parseLong(tagId));
                blogDao.saveBlogAndTag(blogAndTag);
            }
        }


    }

    @Override
    public void updateBlog(Long id, Blog blog) {
        Blog blog1 = blogDao.queryById(id);
        if (null == blog1) {
            throw new NotFoundException("该博客不存在!");
        }
        BeanUtils.copyProperties(blog, blog1);
        blog1.setCreateTime(new Date());
        blog1.setUpdateTime(new Date());
        blogDao.update(blog1);
        blogDao.deleteBlogAndTag(blog.getId());
        for (String tagId : blog.getTagIds().split(",")) {
            BlogAndTag blogAndTag = new BlogAndTag(blog.getId(), Long.parseLong(tagId));
            blogDao.saveBlogAndTag(blogAndTag);
        }
    }

    @Override
    public void deleteBlog(Long id) {
        //删除tags
        blogDao.deleteBlogAndTag(id);
        blogDao.deleteById(id);
    }
}
