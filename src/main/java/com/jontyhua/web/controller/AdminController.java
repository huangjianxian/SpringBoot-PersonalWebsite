package com.jontyhua.web.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName AdminController
 * @Author: JONTY HUA
 * @Date: 2019/3/30 20:44
 * @Description: 后台管理控制器
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")  // 指定角色权限才能操作方法
public class AdminController {

    /**
     * 获取后台管理页面
     * @return
     */
    @GetMapping
    public ModelAndView admin() {
        return new ModelAndView("admin/index");
    }




}
