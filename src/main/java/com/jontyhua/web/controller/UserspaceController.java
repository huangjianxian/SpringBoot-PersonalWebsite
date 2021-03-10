package com.jontyhua.web.controller;

import com.jontyhua.web.domain.*;
import com.jontyhua.web.service.*;
import com.jontyhua.web.util.ConstraintViolationExceptionHandler;
import com.jontyhua.web.vo.Menu;
import com.jontyhua.web.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserspaceController
 * @Author: JontyHua
 * @Date: 2019/4/10
 * @Description: 用户主页控制器
 */
@Controller
@RequestMapping("/u")
public class UserspaceController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Autowired
    private LetterService letterService;

    @Autowired
    private AuthorityService authorityService;

    /**
     * 根据用户名返回对应的用户主页
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}")
    @PreAuthorize("authentication.name.equals(#username)")  //只有对应的用户才有权限访问
    public ModelAndView userSpace(@PathVariable("username") String username, Model model) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        List<Menu> list = new ArrayList<>();
        list.add(new Menu("个人信息", "/u/"+username+"/profile"));
        list.add(new Menu("我的点赞", "/u/"+username+"/votes"));
        list.add(new Menu("评论管理", "/u/"+username+"/comments"));
        list.add(new Menu("留言管理", "/u/"+username+"/messages"));
        list.add(new Menu("私信博主","/u/"+username+"/letter"));
        model.addAttribute("list", list);
        return new ModelAndView("/userspace/index", "userModel", model);
    }

    /**
     * 获得用户个人信息
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username") String username, Model model) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/profile", "userModel", model);
    }

    /**
     * 保存个人信息设置
     * @param username
     * @return
     */
    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> updateProfile(@PathVariable("username") String username,@RequestBody User user) {
        try {
            User originalUser = userService.getUserById(user.getId());
            originalUser.setBirth(user.getBirth());
            originalUser.setNickname(user.getNickname());
            originalUser.setGender(user.getGender());
            // 判断密码是否做了变更
            String originalPassword = originalUser.getPassword();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePasswd = encoder.encode(user.getPassword());
            boolean isMatch = encoder.matches(originalPassword,encodePasswd);
            if (!isMatch) {
                originalUser.setEncodePassword(user.getPassword());
            }
            userService.saveUser(originalUser);
        }catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/u/"+username;
        return ResponseEntity.ok().body(new Response(true, "成功更新信息", redirectUrl));
    }

    /**
     * 获取编辑头像的界面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }

    /**
     * 更新用户头像
     * @param username
     * @param user
     * @return
     */
    @PostMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveAvatar(@PathVariable("username") String username, @RequestBody User user) {
        String avatarUrl = user.getAvatarurl();
        User originalUser = userService.getUserById(user.getId());
        originalUser.setAvatarurl(avatarUrl);
        userService.saveUser(originalUser);
        return ResponseEntity.ok().body(new Response(true, "处理成功", avatarUrl));
    }

    /**
     * 评论管理
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/comments")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView listComments(@PathVariable("username") String username,Model model) {
        User user = userService.findByUsername(username);
//        List<Comment> commentList = commentService.listCommentsByUser(user);
        List<Comment> commentList = commentService.findByUserAndParentCommentNull(user);
        model.addAttribute("comments",commentList);
        return new ModelAndView("/userspace/comments","userModel",model);
    }

    @GetMapping("/{username}/votes")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView listVotes(@PathVariable("username") String username,
                                  @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                                  @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                                  Model model) {
        User user = userService.findByUsername(username);
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<Vote> voteList = voteService.listVotesByUser(user,pageable);
        model.addAttribute("votes",voteList);
        return new ModelAndView("/userspace/votes","userModel",model);
    }

    @GetMapping("/{username}/messages")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView listMessages(@PathVariable("username") String username, Model model) {
        User user = userService.findByUsername(username);
        List<LeaveMessage> leaveMessages = leaveMessageService.listLeaveMessageByUserAndParentNull(user);
        model.addAttribute("messages",leaveMessages);
        return new ModelAndView("/userspace/leaveMessages","userModel",model);
    }

    /**
     * 获取私信界面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/letter")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView listMessagesddd(@PathVariable("username") String username, Model model) {
        model.addAttribute("username",username);
        return new ModelAndView("/userspace/letter","userModel",model);
    }

    /**
     * 用户向博主发送私信
     * @param username
     * @param letter
     * @return
     */
    @PostMapping("/{username}/sendLetter")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveLetter(@PathVariable("username") String username, @RequestBody Letter letter) {
        try {
            User user = userService.findByUsername(username);
            letter.setUser(user);
            letterService.saveLetter(letter);
        }catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String url = "/u/"+username;
        return ResponseEntity.ok().body(new Response(true, "发送私信成功", url));
    }

}
