package com.jontyhua.web.repository;

import com.jontyhua.web.domain.Blog;
import com.jontyhua.web.domain.Category;
import com.jontyhua.web.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName BlogRepository
 * @Author: JontyHua
 * @Date: 2019/4/15
 * @Description: 文章/博客仓库
 */
public interface BlogRepository extends JpaRepository<Blog,Long> {

    /**
     * 根据博客标题逆序查找博客
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByTitleLikeOrderByCreateTimeDesc(String title, Pageable pageable);

    /**
     * 根据博客标题分页查找博客
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByTitleLike(String title, Pageable pageable);

    /**
     * 根据博客标题和分类查找博客
     * @param title
     * @param category
     * @param pageable
     * @return
     */

    Page<Blog> findByTitleLikeAndCategory(String title, Category category, Pageable pageable);

    /**
     * 根据分类查询博客列表
     * @param category
     * @param pageable
     * @return
     */
    Page<Blog> findByCategory(Category category, Pageable pageable);

    /**
     * 根据标签查询博客
     * @param tag
     * @param pageable
     * @return
     */
    Page<Blog> findByTagsIn(Tag tag, Pageable pageable);


    /**
     * 查找所有年份集合 逆序排序
     * @return
     */
    @Query("select function('date_format',b.createTime,'%Y') as year from Blog b " +
            "group by function('date_format',b.createTime,'%Y') order by year desc ")
    List<String> findYearByGroup();

    /**
     * 自定义实现 根据年份查询
     * @param year
     * @return
     */
    @Query("select b from Blog b where function('date_format',b.createTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    /**
     * 根据关键字分页查询博客
     * 包含 标题 内容 简介
     * @param keyword
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1 or b.summary like ?1 and b.state = 1")
    Page<Blog> findBySearchKeyword(String keyword, Pageable pageable);

    /**
     * 自定义策略分页查询热门博客
     * 阅读*1 + 点赞数*5 + 评论数*10
     * @return
     */
    @Query("select b from Blog b order by (b.readSize*1+b.voteSize*5+b.commentSize*10) DESC")
    Page<Blog> listHotBlogs(Pageable pageable);

    /**
     * 根据state查询所有的博客 查询发布上线的博客 ONLINE
     * @param state
     * @return
     */
    Page<Blog> findAllByStateEquals(int state,Pageable pageable);
}
