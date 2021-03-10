package com.jontyhua.web.repository;

import com.jontyhua.web.domain.Blog;
import com.jontyhua.web.domain.Comment;
import com.jontyhua.web.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    /**
     * 统计文章有多少个评论
     * @param blog
     * @return
     */
    Integer countByBlog(Blog blog);

    /**
     * 根据博客id查找评论列表
     * @return
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    /**
     * 查找某用户的所有评论列表
     * @param user
     * @return
     */
    List<Comment> findByUser(User user);

    /**
     * 查找某用户的所有评论列表 查找顶级评论
     * @param user
     * @return
     */
    List<Comment> findByUserAndParentCommentNull(User user);
}
