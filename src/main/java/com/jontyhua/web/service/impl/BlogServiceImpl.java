package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.*;
import com.jontyhua.web.repository.BlogRepository;
import com.jontyhua.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogServiceImpl
 * @Author: JontyHua
 * @Date: 2019/4/15
 * @Description:
 */
@Service
public class BlogServiceImpl implements BlogService {

    /** 发布状态 */
    private static final int ONLINE = 1;

    /** 下线状态 */
    private static final int OFFLINE = 0;

    /** 刷新页面 URL */
    private static final String RefreshUrl = "/admin/blogs";

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 保存博客
     *
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blog.setUser(user);
        blog.setState(ONLINE);
        return blogRepository.save(blog);
    }

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }

    /**
     * 根据id查找博客
     *
     * @param id
     * @return
     */
    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findOne(id);
    }

    /**
     * 更新博客
     *
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog updateBlog(Blog blog) {
        // 更新时间
        blog.setUpdateTime(new Timestamp(new Date().getTime()));
        return blogRepository.save(blog);
    }

    /**
     * 根据文章标题模糊分页查询博客
     *
     * @param title
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByTitleLike(String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";  //没有做处理，则查询为空
        return blogRepository.findByTitleLike(title, pageable);
    }

    /**
     * 查询所有在线博客 用于首页显示
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByOnlineState(Pageable pageable) {
        return blogRepository.findAllByStateEquals(ONLINE,pageable);
    }

    /**
     * 根据文章标题和文章分类查询博客
     *
     * @param title
     * @param category
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByTitleAndCategory(String title, Category category, Pageable pageable) {
        title = "%" + title + "%";  //没有做处理，则查询为空
        return blogRepository.findByTitleLikeAndCategory(title,category,pageable);
    }

    /**
     * 根据分类查询博客列表
     *
     * @param category
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByCategory(Category category, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByCategory(category, pageable);
        return blogs;
    }

    /**
     * 根据标签查博客
     *
     * @param tag
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByTag(Tag tag, Pageable pageable) {
        return blogRepository.findByTagsIn(tag, pageable);
    }

    /**
     * 根据年份归档博客列表
     * @return
     */
    @Override
    public Map<String, List<Blog>> listBlogsByArchive() {
        List<String> years = blogRepository.findYearByGroup();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years){
            map.put(year,blogRepository.findByYear(year));
        }
        return map;
    }

    /**
     * 计算博客总数
     *
     * @return
     */
    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    /**
     * 阅读量递增
     *
     * @param id
     */
    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.findOne(id);
        blog.setReadSize(blog.getReadSize()+1);
        this.updateBlog(blog);
    }

    /**
     * 查找所有博客
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    /**
     * 根据关键字查询博客 用于首页搜索
     *
     * @param keyword
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByKeyWord(String keyword, Pageable pageable) {
        return blogRepository.findBySearchKeyword(keyword,pageable);
    }

    /**
     * 查询前10热门的文章
     *
     * @return
     */
    @Override
    public List<Blog> listHot10Blog() {
        Pageable pageable = new PageRequest(0, 10);
        Page<Blog> blogs = blogRepository.listHotBlogs(pageable);
        return blogs.getContent();
    }

    /**
     * 下线博客
     *
     * @param id
     * @return
     */
    @Override
    public Blog offlineBlog(Long id) {
        Blog blog = blogRepository.findOne(id);
        blog.setState(OFFLINE);
        return blogRepository.save(blog);
    }

    /**
     * 上线博客
     *
     * @param id
     * @return
     */
    @Override
    public Blog onlineBlog(Long id) {
        Blog blog = blogRepository.findOne(id);
        blog.setState(ONLINE);
        return blogRepository.save(blog);
    }

    /**
     * 点赞
     *
     * @param blogId
     * @return
     */
    @Override
    public Blog createVote(Long blogId) {
        Blog originalBlog = blogRepository.findOne(blogId);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vote vote = new Vote(user,originalBlog);
        boolean isExist = originalBlog.addVote(vote);
        if (isExist) {
            throw new IllegalArgumentException("该用户已经点过赞了");
        }
        return this.saveBlog(originalBlog);
    }

    /**
     * 取消点赞
     *
     * @param blogId
     * @param voteId
     * @return
     */
    @Override
    public void removeVote(Long blogId, Long voteId) {
        Blog originalBlog = blogRepository.findOne(blogId);
        originalBlog.removeVote(voteId);
        this.saveBlog(originalBlog);
    }
}
