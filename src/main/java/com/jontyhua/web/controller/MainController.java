package com.jontyhua.web.controller;

import com.jontyhua.web.domain.*;
import com.jontyhua.web.service.*;
import com.jontyhua.web.util.RandomTool;
import com.jontyhua.web.util.SendEmailUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MainController
 * @Author: JONTY HUA
 * @Date: 2019/3/29 22:57
 * @Description: 实现主页跳转等操作
 */
@Controller
public class MainController {

    private final Log log = LogFactory.getLog(MainController.class);

    /** 用户初始角色：普通用户 */
    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    /** 未激活状态 */
    private static final int NON_ACTIVATED = 0;

    /** 激活状态 */
    private static final int ACTIVATED_STATE = 1;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private SendEmailUtil sendEmailUtil;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 根目录，返回index首页
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
            @RequestParam(value="async",required=false) boolean async,
            @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
            @RequestParam(value="pageSize",required=false,defaultValue="5") int pageSize,
            @RequestParam(value="ctgName",required=false,defaultValue="") String ctgName,
            @RequestParam(value="tagName",required=false,defaultValue="") String tagName,
            Model model) {
        // 根据创建时间逆序分页展示博客内容 - 最新博客列表
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        List<Blog> blogList = null;
        Page<Blog> page = null;
        boolean isEmpty = true; // 系统初始化时，没有博客数据
        // 没有搜索关键字 则返回全部的博客
        if(keyword.equals("") && ctgName.equals("") && tagName.equals("")){
            page = blogService.listBlogsByOnlineState(pageable);
            blogList = page.getContent();
            isEmpty = false;
        }else if(keyword.equals("") && !ctgName.equals("")){    // 根据分类查询博客
            page = blogService.listBlogsByCategory(categoryService.getCategoryByName(ctgName),pageable);
            blogList = page.getContent();
            isEmpty = false;
        } else if(keyword.equals("") && !tagName.equals("")){   //  根据标签查询博客
            page = blogService.listBlogsByTag(tagService.getTagByName(tagName),pageable);
            blogList = page.getContent();
            isEmpty = false;
        } else{
            // %abc% 做模糊查询处理，否则查找不到数据
            page = blogService.listBlogsByKeyWord("%"+keyword+"%",pageable);
            blogList = page.getContent();
            isEmpty = false;
        }
        model.addAttribute("page", page);
        model.addAttribute("blogList", blogList);
        // 首次访问页面才加载
        if (!async && !isEmpty) {
            // 获得发布的公告内容
            List<Notice> noticeList = noticeService.listNoticesByOnlineState();
            List<Tag> tagList = tagService.listTags();
            List<Category> categoryList = categoryService.listCategories();
            List<Blog> hotBlogs = blogService.listHot10Blog();
            List<FriendLink> friendLinkList = friendLinkService.listFriendLink();
            model.addAttribute("noticeList", noticeList);
            model.addAttribute("tagList", tagList);
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("hotBlogs", hotBlogs);
            model.addAttribute("friendLinkList", friendLinkList);
        }
        return new ModelAndView(async==true?"/index :: #mainContainerRepleace":"/index","indexModel",model);
    }


    @GetMapping("/search")
    public ModelAndView listBlogsByTag(@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
                              @RequestParam(value="async",required=false) boolean async,
                              @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                              @RequestParam(value="pageSize",required=false,defaultValue="5") int pageSize,
                              @RequestParam(value="pageSize",required=false,defaultValue="5") String name,
                              Model model) {
        // 根据创建时间逆序分页展示博客内容 - 最新博客列表
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        List<Blog> blogList = null;
        Page<Blog> page = null;
        boolean isEmpty = true; // 系统初始化时，没有博客数据
        // 没有搜索关键字 则返回全部的博客
        if(keyword.equals("")){
            page = blogService.listBlogsByOnlineState(pageable);
            blogList = page.getContent();
            isEmpty = false;
        }else{
            // %abc% 做模糊查询处理，否则查找不到数据
            page = blogService.listBlogsByKeyWord("%"+keyword+"%",pageable);
            blogList = page.getContent();
            isEmpty = false;
        }
        model.addAttribute("page", page);
        model.addAttribute("blogList", blogList);
        // 首次访问页面才加载
        if (!async && !isEmpty) {
            // 获得发布的公告内容
            List<Notice> noticeList = noticeService.listNoticesByOnlineState();
            List<Tag> tagList = tagService.listTags();
            List<Category> categoryList = categoryService.listCategories();
            List<Blog> hotBlogs = blogService.listHot10Blog();
            List<FriendLink> friendLinkList = friendLinkService.listFriendLink();
            model.addAttribute("noticeList", noticeList);
            model.addAttribute("tagList", tagList);
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("hotBlogs", hotBlogs);
            model.addAttribute("friendLinkList", friendLinkList);
        }
        return new ModelAndView(async==true?"/index :: #mainContainerRepleace":"/index","indexModel",model);
    }


    /**
     * 返回index页面
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "redirect:/";
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 后台管理员登录入口
     * @return
     */
    @GetMapping("/adminlogin")
    public String adminLogin(){
        return "admin/login";
    }

    /**
     * 返回注册页面
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 注册操作
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String registerUser(User user) {
        String code = RandomTool.randomCode();
        user.setCode(code);
        log.info("注册者邮箱："+user.getEmail());
        log.info("验证码："+user.getCode());
        try {
            sendEmailUtil.sendRegisterMail(user.getUsername(),user.getEmail(),user.getCode());
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));     //默认普通用户
            user.setAuthorities(authorities);   //设置用户角色
            user.setState(NON_ACTIVATED);   //初始化 未激活状态
            userService.saveUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/activation/{email}/{code}")
    public String checkUserMail(@PathVariable(name = "email") String email, @PathVariable(name = "code") String code){
        System.out.println("已经来到了激活controller");
        User user = userService.findByEmailAndCode(email, code);
        if(user != null){
            user.setState(ACTIVATED_STATE);
            user.setCode(null);
            userService.updateUser(user);
            System.out.println("设置成为激活状态");
            return "redirect:/login";
        }else{
            System.out.println("失败");
            return "false";
        }
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
        return "login";
    }

    @GetMapping("/blog")
    public String theBlog() {
        return "blog";
    }

    @GetMapping("/UploadImg")
    public String UploadImg() {
        return "UploadImg";
    }

    @GetMapping("/blog/{id}")
    public String getBlogById(@PathVariable("id") Long id, Model model) {
        User principal = null;
        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        Blog blog = blogService.getBlogById(id);
        System.out.println("来到了博客页面，id为："+blog.getId());
//        List<Comment> commentList = c
        // 每次读取，简单的可以认为阅读量增加1次
        blogService.readingIncrease(id);
        // 每次读取，简单的可以认为阅读量增加1次
//        blogService.readingIncrease(id);

        // 判断操作用户的点赞情况
//        List<Vote> votes = blog.getVotes();
//        Vote currentVote = null; // 当前用户的点赞情况

//        if (principal !=null) {
//            for (Vote vote : votes) {
//                vote.getUser().getUsername().equals(principal.getUsername());
//                currentVote = vote;
//                break;
//            }
//        }

//        model.addAttribute("isBlogOwner", isBlogOwner);
        // 判断操作用户的点赞情况
        List<Vote> votes = blog.getVotes();
        Vote currentVote = null; // 当前用户的点赞情况

        if (principal !=null) {
            for (Vote vote : votes) {
                vote.getUser().getUsername().equals(principal.getUsername());
                currentVote = vote;
                break;
            }
        }



        List<Comment> comments = commentService.getCommentsByBlogId(id);
        model.addAttribute("blog",blog);
        model.addAttribute("comments",comments);
        model.addAttribute("currentVote",currentVote);
        return "blog";
    }


    /**
     * 分类页
     * @param model
     * @return
     */
    @GetMapping("/categories")
    public ModelAndView listCategory(@RequestParam(value="async",required=false) boolean async,
                                     @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                                     @RequestParam(value="pageSize",required=false,defaultValue="5") int pageSize,
                                     @RequestParam(value="ctgName",required=false,defaultValue="") String ctgName,
                                     Model model){
        System.out.println("拿到分类名"+ctgName);
        List<Category> categories = categoryService.listCategories();
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
        List<Blog> blogList = null;
        Page<Blog> page = null;
        if(ctgName !=null && !ctgName.equals("")){
            Category category = categoryService.getCategoryByName(ctgName);
            page = blogService.listBlogsByCategory(category,pageable);
            blogList = page.getContent();	// 当前所在页面数据列表
        }else{
            page = blogService.listBlogs(pageable);
            blogList = page.getContent();	// 当前所在页面数据列表
        }
        model.addAttribute("page", page);
        model.addAttribute("categories", categories);
        model.addAttribute("blogList", blogList);
        return new ModelAndView(async==true?"categories :: #mainContainerRepleace":"categories","categoryModel",model);
    }

//    @GetMapping("/categories/{id}")
//    public ModelAndView getBlogsByCategoryId(@PathVariable("id") Long id,Model model){
//        Category category = categoryService.getCategoryById(id);
//        // 根据时间逆序分页展示博客内容
//        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
//        Pageable pageable = new PageRequest(0, 7, sort);
//        Page<Blog> blogList = blogService.listBlogsByCategory(category,pageable);
//        model.addAttribute("blogList", blogList);
//        return new ModelAndView("categories","categoryModel",model);
//    }

    /**
     * 归档
     * @param model
     * @return
     */
    @GetMapping("/archives")
    public String listBlogsByYear(Model model){
        model.addAttribute("archiveMap",blogService.listBlogsByArchive());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }

    /**
     * 日记
     * @param model
     * @return
     */
    @GetMapping("/diary")
    public String listDiary(Model model){
        model.addAttribute("diaryList",diaryService.listDiariesByOnlineState());
        return "diary";
    }

    @GetMapping("/about")
    public String about(Model model) {
        List<LeaveMessage> leaveMessages = leaveMessageService.getLeaveMessages();
        model.addAttribute("leaveMessages",leaveMessages);
        return "about";
    }

    @GetMapping("/test")
    public String test() {
        return "fragments/header";
    }

}
