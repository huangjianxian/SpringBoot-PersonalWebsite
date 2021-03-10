package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Blog;
import com.jontyhua.web.domain.Comment;
import com.jontyhua.web.domain.User;
import com.jontyhua.web.repository.BlogRepository;
import com.jontyhua.web.repository.CommentRepository;
import com.jontyhua.web.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Author: JontyHua
 * @Date: 2019/4/29
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 根据博客id查找评论列表
     *
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> getCommentsByBlogId(Long blogId) {
//        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Sort sort = new Sort("createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentRepository.findOne(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        return commentRepository.save(comment);
    }

    /**
     * 根据id获取 Comment
     *
     * @param id
     * @return
     */
    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findOne(id);
    }

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }

    /**
     * 计算博客的评论数量
     *
     * @param blog
     * @return
     */
    @Override
    public Integer countCommentSizeByBlog(Blog blog) {
        return commentRepository.countByBlog(blog);
    }

    /**
     * 添加评论
     *
     * @param blogId
     * @param commentContent
     */
    @Override
    public Comment createComment(Long blogId, String commentContent) {
        Blog blog = blogRepository.findOne(blogId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("创建评论的User是"+user.getUsername());
        Comment comment = new Comment(user, blog, commentContent);
        return commentRepository.save(comment);
    }

    /**
     * 回复评论
     *
     * @param blogId
     * @param commentId
     * @param replyId
     * @param commentContent
     */
    @Override
    public Comment replyComment(Long blogId, Long commentId, Long replyId, String commentContent) {
//        Blog blog = blogRepository.findOne(blogId);
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Comment comment = new Comment(commentId, commentContent,user, blog);
//        //评论回复，需要加上@用户
//        if (replyId != null) {
//            Comment replyComment = commentRepository.findOne(replyId);
//            comment.setReplyUser(replyComment.getUser());
//        }
//        //添加评论
//        return commentRepository.save(comment);
        return null;
    }

    /**
     * 获取某用户所有评论列表
     *
     * @param user
     * @return
     */
    @Override
    public List<Comment> listCommentsByUser(User user) {
        return commentRepository.findByUser(user);
    }

    /**
     * 查找该用户的顶级列表
     *
     * @param user
     * @return
     */
    @Override
    public List<Comment> findByUserAndParentCommentNull(User user) {
        return commentRepository.findByUserAndParentCommentNull(user);
    }


    // 处理评论层级
    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }




}
