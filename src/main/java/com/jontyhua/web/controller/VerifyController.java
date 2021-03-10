package com.jontyhua.web.controller;

import com.jontyhua.web.domain.Category;
import com.jontyhua.web.domain.User;
import com.jontyhua.web.service.CategoryService;
import com.jontyhua.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName VerifyController
 * @Author: JontyHua
 * @Date: 2019/4/14
 * @Description: 验证控制器
 */
@Controller
@RequestMapping("/verify")
public class VerifyController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    /** 未激活状态 */
    private static final int NON_ACTIVATED = 0;

    /** 激活状态 */
    private static final int ACTIVATED_STATE = 1;

    /**
     * 验证用户名是否已经被使用
     * @param username
     * @return
     */
    @GetMapping("/username")
    @ResponseBody
    public Boolean verifyUsername(@RequestParam(value="username") String username){
        User user = userService.findByUsername(username);
        if(user != null ){  //查找到了该用户，说明用户名已经被占用
            return false;
        }else{  //用户名未被占用
            return true;
        }
    }

    /**
     * 验证邮箱是否已经被使用
     * @param email
     * @return
     */
    @GetMapping("/email")
    @ResponseBody
    public Boolean verifyEmail(@RequestParam(value="email") String email){
        User user = userService.findByEmail(email);
        if(user != null ){  //查找到了该用户，说明该邮箱已经被占用
            return false;
        }else{  //邮箱未被占用
            return true;
        }
    }

    /**
     * 验证昵称是否已经被使用
     * @param nickname
     * @return
     */
    @GetMapping("/nickname")
    @ResponseBody
    public Boolean verifyNickname(@RequestParam(value="nickname") String nickname){
        User user = userService.findByNickname(nickname);
        if(user != null ){  //查找到了该用户，说明昵称已经被占用
            return false;
        }else{  //昵称未被占用
            return true;
        }
    }

    /**
     * 验证用户是否已经注册或者已经激活
     * @param username
     * @return
     */
    @GetMapping("/usernameOrState")
    @ResponseBody
    public Boolean verifyLoginState(@RequestParam(value="username") String username){
        User user = userService.findByUsername(username);
        if(user != null ){  //查找到了该用户
            if(user.getState() == NON_ACTIVATED || user.getState() == null){   //判断是否是激活状态
                return false;
            }else{
                return true;
            }
        }else{  //未查到用户
            return false;
        }
    }

    @GetMapping("/category")
    @ResponseBody
    public Boolean verifyCategory(@RequestParam(value="name") String name){
        Category category = categoryService.getCategoryByName(name);
        if(category != null ){  //查找到了该分类
            return false;
        }else{  //未查到用户
            return true;
        }
    }

}
