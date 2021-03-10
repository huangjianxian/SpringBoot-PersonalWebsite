package com.jontyhua.web.service;

import com.jontyhua.web.domain.Blog;
import com.jontyhua.web.domain.Comment;
import com.jontyhua.web.domain.User;

import java.util.List;

public interface CommentService {

    /**
     * 根据博客id查找评论列表
     * @param blogId
     * @return
     */
    List<Comment> getCommentsByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);

    /**
     * 根据id获取 Comment
     * @param id
     * @return
     */
    Comment getCommentById(Long id);

    /**
     * 删除评论
     * @param id
     * @return
     */
    void deleteComment(Long id);

    /**
     * 计算博客的评论数量
     * @param blog
     * @return
     */
    Integer countCommentSizeByBlog(Blog blog);

    /**
     * 添加评论
     * @param blogId
     * @param commentContent
     */
    Comment createComment(Long blogId, String commentContent);

    /**
     * 回复评论
     * @param blogId
     * @param commentId
     * @param replyId
     * @param commentContent
     */
    Comment replyComment(Long blogId, Long commentId, Long replyId, String commentContent);

    /**
     * 获取某用户所有评论列表
     * @param user
     * @return
     */
    List<Comment> listCommentsByUser(User user);

    /**
     * 查找该用户的顶级列表
     * @param user
     * @return
     */
    List<Comment> findByUserAndParentCommentNull(User user);

}
