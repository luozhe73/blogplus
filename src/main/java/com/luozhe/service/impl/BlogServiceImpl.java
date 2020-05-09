package com.luozhe.service.impl;

import com.luozhe.dao.BlogDao;
import com.luozhe.dao.TagDao;
import com.luozhe.dao.TypeDao;
import com.luozhe.dto.BlogAndTag;
import com.luozhe.dto.BlogQuery;
import com.luozhe.exception.NotFoundException;
import com.luozhe.pojo.Blog;
import com.luozhe.pojo.Tag;
import com.luozhe.service.BlogService;
import com.luozhe.service.TagService;
import com.luozhe.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        if (tags.length() > 1) {
            tags = tags.substring(0, tags.length() - 1);
        }
        Blog blog = blogDao.queryById(id);
        blog.setTagIds(tags);

        blog.setViews(blog.getViews() + 1);
        blogDao.update(blog);
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
    public List<Blog> getRecomendBlog(Integer size) {
        return blogDao.queryRecomendBlog(size);
    }

    @Override
    public void saveBlog(Blog blog) {

        blog.setUpdateTime(new Date());
        blog.setRecommend(blog.getRecommend() == null ? false : blog.getRecommend());
        blog.setAppreciation(blog.getAppreciation() == null ? false : blog.getAppreciation());
        blog.setShareStatement(blog.getShareStatement() == null ? false : blog.getShareStatement());
        blog.setCommentabled(blog.getCommentabled() == null ? false : blog.getCommentabled());
        blog.setPublished(blog.getPublished() == null ? false : blog.getPublished());
        blog.setUserId(blog.getUser().getId());
        if (blog.getId() != null) {
            blogDao.update(blog);
            blogDao.deleteBlogAndTag(blog.getId());
            for (String tagId : blog.getTagIds().split(",")) {
                BlogAndTag blogAndTag = new BlogAndTag(blog.getId(), Long.parseLong(tagId));
                blogDao.saveBlogAndTag(blogAndTag);
            }
        } else {
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

    @Override
    public List<Blog> queryBySearch(String query) {
        return blogDao.searchBlogs(query);
    }

    @Override
    public Blog getAndConvert(Long id) {
        List<BlogAndTag> blogAndTags = blogDao.queryBlogAndTag(id);
        List<Tag> tags = new ArrayList<>();

        Blog blog = blogDao.queryById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在！");
        }
        Blog b = new Blog();

        BeanUtils.copyProperties(blog, b);

        String content = b.getContent();
        MarkdownUtils.markdownToHtmlExtensions(content);
        b.setContent(content);
        for (BlogAndTag blogAndTag : blogAndTags) {
            tags.add(tagDao.getById(blogAndTag.getTagsId()));
        }

        b.setViews(b.getViews() + 1);
        b.setTags(tags);
        blogDao.update(b);
        return b;
    }

    @Override
    public List<Blog> queryByTagId(Long tagId) {
        return blogDao.queryByTagId(tagId);
    }

    @Override
    public Map archiveBlog() {
        Map map = new HashMap();
        List<String> years = blogDao.queryAllYears();
        for (String year : years) {
            List<Blog> blogs = blogDao.queryBlogByYear(year);
            map.put(year,blogs);
        }
        return map;
    }

    @Override
    public Integer countBlog() {
        List<Blog> blogs = blogDao.queryAll(null);
        return blogs.size();
    }


}
