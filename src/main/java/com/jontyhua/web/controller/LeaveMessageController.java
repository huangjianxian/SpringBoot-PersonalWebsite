package com.jontyhua.web.controller;

import com.jontyhua.web.domain.Blog;
import com.jontyhua.web.domain.Comment;
import com.jontyhua.web.domain.LeaveMessage;
import com.jontyhua.web.domain.User;
import com.jontyhua.web.service.BlogService;
import com.jontyhua.web.service.CommentService;
import com.jontyhua.web.service.LeaveMessageService;
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
 * @ClassName LeaveMessageController
 * @Author: JontyHua
 * @Date: 2019/5/5
 * @Description: LeaveMessageController 控制器
 */
@Controller
public class LeaveMessageController {
    
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LeaveMessageService leaveMessageService;

    /**
     * 获取所有的留言
     * @param model
     * @return
     */
    @GetMapping("/leaveMessages")
    public String getLeaveMessage(Model model){
        List<LeaveMessage> leaveMessages = leaveMessageService.getLeaveMessages();
        model.addAttribute("leaveMessages",leaveMessages);
        return "about :: mainContainer";
    }


    /**
     * 提交留言
     * @param leaveMessage
     * @return
     */
//    @PostMapping("/leaveMessages")
//    public String createLeaveMessage(LeaveMessage leaveMessage){
//        leaveMessageService.saveLeaveMessage(leaveMessage);
//        return "redirect:/leaveMessage";
//    }


    /**
     * 保存博客
     * @param blog
     * @return
     */
    @PostMapping("/leaveMessages")
    public ResponseEntity<Response> createLeaveMessage(LeaveMessage leaveMessage) {
        try {
            leaveMessageService.saveLeaveMessage(leaveMessage);
        }catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/leaveMessages";
        return ResponseEntity.ok().body(new Response(true, "留言成功", redirectUrl));
    }


    /**
     * 删除评论
     *
     * @return
     */
    @DeleteMapping("/leaveMessage/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
    public ResponseEntity<Response> deleteLeaveMessage(@PathVariable("id") Long id) {
        boolean isOwner = false;
        User user;
        try {
            user = leaveMessageService.getLeaveMessageById(id).getUser();
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "留言不存在！"));
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
            leaveMessageService.deleteLeaveMessage(id);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }
}
