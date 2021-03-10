package com.jontyhua.web.service;

import com.jontyhua.web.domain.Blog;
import com.jontyhua.web.domain.Category;
import com.jontyhua.web.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
 * 博客/文章 服务类
 */
public interface BlogService {

    /**
     * 保存博客
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    void deleteBlog(Long id);

    /**
     * 根据id查找博客
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    Blog updateBlog(Blog blog);

    /**
     * 根据文章标题模糊分页查询博客
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleLike(String title, Pageable pageable);

    /**
     * 查询所有在线博客 用于首页显示
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByOnlineState(Pageable pageable);

    /**
     * 根据文章标题和文章分类查询博客
     * @param title
     * @param category
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleAndCategory(String title,Category category,Pageable pageable);

    /**
     * 根据分类查询博客列表
     * @param category
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByCategory(Category category, Pageable pageable);

    /**
     * 根据标签查博客
     * @param tag
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTag(Tag tag, Pageable pageable);

    /**
     * 归档文章 按照年份
     * @return
     */
    Map<String, List<Blog>> listBlogsByArchive();

    /**
     * 计算博客总数
     * @return
     */
    Long countBlog();

    /**
     * 阅读量递增
     * @param id
     */
    void readingIncrease(Long id);

    /**
     * 查找所有博客
     *
     * @param pageable
     * @return
     */
    Page<Blog> listBlogs(Pageable pageable);

    /**
     * 根据关键字查询博客 用于首页搜索
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByKeyWord(String keyword,Pageable pageable);

    /**
     * 查询前10热门的文章
     * @return
     */
    List<Blog> listHot10Blog();

    /**
     * 下线博客
     * @param id
     * @return
     */
    Blog offlineBlog(Long id);

    /**
     * 上线博客
     * @param id
     * @return
     */
    Blog onlineBlog(Long id);

    /**
     * 点赞
     * @param blogId
     * @return
     */
    Blog createVote(Long blogId);

    /**
     * 取消点赞
     * @param blogId
     * @param voteId
     * @return
     */
    void removeVote(Long blogId, Long voteId);


}

