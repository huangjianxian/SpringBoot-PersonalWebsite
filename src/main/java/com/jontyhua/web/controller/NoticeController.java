package com.jontyhua.web.controller;

import com.jontyhua.web.domain.Notice;
import com.jontyhua.web.service.NoticeService;
import com.jontyhua.web.util.ConstraintViolationExceptionHandler;
import com.jontyhua.web.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @ClassName NoticeController
 * @Author: JontyHua
 * @Date: 2019/4/28
 * @Description: 公告控制器
 */
@Controller
@RequestMapping("/admin/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public String listNotices(Model model){
        List<Notice> list = noticeService.listNotices();    //里面已经实现了根据时间逆序排序
        model.addAttribute("noticesList",list);
        return "admin/notice/listNotices";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteNotice(@PathVariable("id") Long id){
        try{
            noticeService.deleteNotice(id);
        }catch (Exception e){
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/admin/notices";
        return ResponseEntity.ok().body(new Response(true, "成功删除公告", redirectUrl));
    }

    // 新增公告
    @PostMapping
    public ResponseEntity<Response> saveNotice(@RequestBody Notice notice){
        try{
            noticeService.saveNotice(notice);
        }catch (ConstraintViolationException e){
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/admin/notices";
        return ResponseEntity.ok().body(new Response(true, "新增公告成功", redirectUrl));
    }

    // 下线公告
    @PostMapping("/offline/{id}")
    public ResponseEntity<Response> offlineNotice(@PathVariable("id") Long id){
        try{
            noticeService.offlineNotice(id);
        }catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/admin/notices";
        return ResponseEntity.ok().body(new Response(true, "下线公告", redirectUrl));
    }

    // 上线公告
    @PostMapping("/online/{id}")
    public ResponseEntity<Response> onlineNotice(@PathVariable("id") Long id){
        try{
            noticeService.onlineNotice(id);
        }catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/admin/notices";
        return ResponseEntity.ok().body(new Response(true, "上线公告", redirectUrl));
    }

}
