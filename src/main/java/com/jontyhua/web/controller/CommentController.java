package com.jontyhua.web.controller;

import com.jontyhua.web.domain.Blog;
import com.jontyhua.web.domain.Comment;
import com.jontyhua.web.domain.User;
import com.jontyhua.web.service.BlogService;
import com.jontyhua.web.service.CommentService;
import com.jontyhua.web.util.ConstraintViolationExceptionHandler;
import com.jontyhua.web.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @ClassName CommentController
 * @Author: JontyHua
 * @Date: 2019/4/29
 * @Description:
 */
@Controller
public class CommentController {
    
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/comments/{blogId}")
    public String getComments(@PathVariable("blogId") Long blogId,Model model){
        List<Comment> comments = commentService.getCommentsByBlogId(blogId);
        model.addAttribute("comments",comments);
        System.out.println("comments的列表"+comments.size());
        return "blog :: mainContainer";
    }


    @PostMapping("/comments")
    public ResponseEntity<Response> createComment(Comment comment){
        try {
            Long blogId = comment.getBlog().getId();
            comment.setBlog(blogService.getBlogById(blogId));
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            comment.setUser(user);
            commentService.saveComment(comment);
        }catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "成功评论"));
    }


//    /**
//     * 获取评论列表
//     * @param blogId
//     * @param model
//     * @return
//     */
//    @GetMapping
//    public String listComments(@RequestParam(value="blogId",required=true) Long blogId, Model model) {
//        System.out.println("blogId来到评论"+blogId);
//        Blog blog = blogService.getBlogById(blogId);
//        List<Comment> comments = blog.getComments();
//        System.out.println("comments.size(): "+comments.size());
//        for (int i = 0; i < comments.size(); i++) {
//            System.out.println("comments"+comments.get(i).getContent());
//        }
//        // 判断操作用户是否是评论的所有者
//        String commentOwner = "";
//        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
//                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
//            User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            if (principal !=null) {
//                commentOwner = principal.getUsername();
//            }
//        }
//        model.addAttribute("commentOwner", commentOwner);
//        model.addAttribute("comments", comments);
//        return "blog :: #mainContainerRepleace";
//    }
//    /**
//     * 发表评论
//     * @param blogId
//     * @param commentContent
//     * @return
//     */
//    @PostMapping
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
//    public ResponseEntity<Response> createComment(@RequestParam Long blogId, @RequestParam String commentContent) {
//        System.out.println("来到了这里");
//        try {
////            blogService.createComment(blogId, commentContent);
//        } catch (ConstraintViolationException e)  {
//            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
//        }
//
//        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
//    }

//    /**
//     * 发表评论
//     *
//     * @param blogId
//     * @param commentId
//     * @param commentContent
//     * @return
//     */
//    @PostMapping
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
//    public ResponseEntity<Response> createComment(Long blogId, Long replyId, Long commentId, String commentContent) {
//        System.out.println("提交评论：blogId: "+blogId+" replyId: "+replyId+" commentId: "+commentId+" commentContent: "+commentContent);
//        try {
//            blogService.getBlogById(blogId);
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(new Response(false, "文章不存在!"));
//        }
//        try {
//            //1、评论
//            if (commentId == null) {
//                commentService.createComment(blogId, commentContent);
//            } else {
//                //回复
//                commentService.replyComment(blogId, commentId, replyId, commentContent);
//            }
//            //2、修改文章的评论数目
//            Blog originalBlog = blogService.getBlogById(blogId);
//            originalBlog.setCommentSize(commentService.countCommentSizeByBlog(originalBlog));
//            blogService.saveBlog(originalBlog);
//        } catch (ConstraintViolationException e) {
//            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
//        }
//        return ResponseEntity.ok().body(new Response(true, "处理成功!", null));
//    }

//    /**
//     * 删除评论
//     * @return
//     */
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
//    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Long blogId) {
//        boolean isOwner = false;
//        User user = commentService.getCommentById(id).getUser();
//        // 判断操作用户是否是评论的所有者
//        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
//                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
//            User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            if (principal !=null && user.getUsername().equals(principal.getUsername())) {
//                isOwner = true;
//            }
//        }
//        if (!isOwner) {
//            return ResponseEntity.ok().body(new Response(false, "没有操作权限"));
//        }
//        try {
////            blogService.deleteComment(blogId, id);
//            commentService.deleteComment(id);
//        } catch (ConstraintViolationException e)  {
//            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
//        }
//        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
//    }


    /**
     * 删除评论
     *
     * @return
     */
    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
    public ResponseEntity<Response> deleteComment(@PathVariable("id") Long id) {
        boolean isOwner = false;
        User user;
        try {
            user = commentService.getCommentById(id).getUser();
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "评论不存在！"));
        }
        // 判断操作用户是否是评论的所有者
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && user.getUsername().equals(principal.getUsername())) {
                isOwner = true;
            }
        }
        if (!isOwner) {
            return ResponseEntity.ok().body(new Response(false, "没有操作权限！"));
        }
        try {
            Comment originalComment = commentService.getCommentById(id);
            //1、先删除子评论
//            List<Comment> commentList = originalComment.getCommentList();
//            if (commentList != null && commentList.size() != 0) {
//                for (int i = 0; i < commentList.size(); i++) {
//                    Comment comment = commentList.get(i);
//                    commentService.deleteComment(comment.getId());
//                }
//            }
            //2、删除该评论
            commentService.deleteComment(id);
            //3、修改文章的评论数目
            Blog originalBlog = originalComment.getBlog();
            originalBlog.setCommentSize(commentService.countCommentSizeByBlog(originalBlog));
            blogService.saveBlog(originalBlog);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }
}
