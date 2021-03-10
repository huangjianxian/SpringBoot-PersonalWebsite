package com.jontyhua.web.controller;


import com.jontyhua.web.domain.FriendLink;
import com.jontyhua.web.service.FriendLinkService;
import com.jontyhua.web.util.ConstraintViolationExceptionHandler;
import com.jontyhua.web.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @ClassName FriendLinkController
 * @Author: JontyHua
 * @Date: 2019/5/13
 * @Description: 友链控制器
 */
@Controller
@RequestMapping("/admin/friendlinks")
public class FriendLinkController {

    /** 刷新页面 URL */
    private static final String RefreshUrl = "/admin/friendlinks";

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 后台获得所有标签
     * @param model
     * @return
     */
    /**
     * 后台获取所有友链列表
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listFriendLinks(Model model){
        List<FriendLink> friendLinks = friendLinkService.listFriendLink();
        model.addAttribute("friendLinks", friendLinks);
        return new ModelAndView("admin/friendlink/listFriendLinks","friendLinkModel",model);
    }

    /**
     * 新增标签
     * @param tag
     * @return
     */
    /**
     * 新增友情链接
     * @param friendLink
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(@RequestBody FriendLink friendLink) {
        try {
            friendLinkService.save(friendLink);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) { //如果重复，在这里捕获异常
            return ResponseEntity.ok().body(new Response(false, "该友链已存在"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", RefreshUrl));
    }


    /**
     * 删除分类
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteFriendLink(@PathVariable("id") Long id) {
        try {
            friendLinkService.deleteFriendLink(id);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "不安全操作！"));
        }
        return ResponseEntity.ok().body(new Response(true, "删除友链成功", RefreshUrl));
    }
}
