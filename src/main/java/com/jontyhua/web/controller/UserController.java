package com.jontyhua.web.controller;

import com.jontyhua.web.domain.Authority;
import com.jontyhua.web.domain.User;
import com.jontyhua.web.service.AuthorityService;
import com.jontyhua.web.service.UserService;
import com.jontyhua.web.util.ConstraintViolationExceptionHandler;
import com.jontyhua.web.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserController
 * @Author: JONTY HUA
 * @Date: 2019/3/27 23:00
 * @Description: 用户控制器
 */
@RestController
@RequestMapping("/admin/users")
public class UserController {

    /** 返回 */
    private static final String RefreshUrl = "/admin/users";

    @Autowired
    private UserService userService;    //要在UserServiceImpl添加注解@Service，否则报错

    @Autowired
    private AuthorityService authorityService;

    /**
     * 查询所有的用户&根据用户名查询
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param username
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="username",required=false,defaultValue="") String username,
                             Model model) {
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<User> page = userService.listUsersByUsernameLike(username, pageable);
        List<User> list = page.getContent();	// 当前所在页面数据列表
        model.addAttribute("page", page);
        model.addAttribute("userList", list);
        return new ModelAndView(async==true?"admin/user/listUsers :: #mainContainerRepleace":"admin/user/listUsers", "userModel", model);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping
    public ResponseEntity<Response> updateUser(@RequestBody User user) {
        try {
            User originalUser = userService.getUserById(user.getId());
            originalUser.setPassword(user.getPassword());
            originalUser.setEmail(user.getEmail());
            originalUser.setNickname(user.getNickname());
            originalUser.setBirth(user.getBirth());
            userService.saveUser(originalUser);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(true, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) { //如果分类重复，在这里捕获异常
            return ResponseEntity.ok().body(new Response(true, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "更新成功", RefreshUrl));
    }

    /**
     * 删除用户
     * @param id
     * @param model
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
        }
        return  ResponseEntity.ok().body( new Response(true, "删除成功",RefreshUrl));
    }

    /**
     * 获取用户修改界面和回显数据
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("admin/user/editUser", "userModel", model);
    }

    public ResponseEntity<Response> saveOrUpdateUser(User user,Long authorityId) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        try {

        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", user));
    }



}
