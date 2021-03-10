package com.jontyhua.web.controller;

import com.jontyhua.web.domain.Blog;
import com.jontyhua.web.domain.Category;
import com.jontyhua.web.domain.Flag;
import com.jontyhua.web.domain.Tag;
import com.jontyhua.web.service.BlogService;
import com.jontyhua.web.service.CategoryService;
import com.jontyhua.web.service.FlagService;
import com.jontyhua.web.service.TagService;
import com.jontyhua.web.util.ConstraintViolationExceptionHandler;
import com.jontyhua.web.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BlogController
 * @Author: JontyHua
 * @Date: 2019/4/15
 * @Description:
 */

@Controller
@RequestMapping("admin/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private FlagService flagService;

    /** 刷新页面 URL */
    private static final String RefreshUrl = "/admin/blogs";

    @GetMapping("/upImg")
    public String upImg(){
        return "/admin/blog/upImg";
    }

    /**
     * 获得博客列表
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param title
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listBlogs(@RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="title",required=false,defaultValue="") String title,
                             @RequestParam(value="categoryname",required=false,defaultValue="") String categoryname,
                             Model model) {
        List<Category> categoryList = categoryService.listCategories();
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<Blog> page = null;
        List<Blog> list = null;
        if(categoryname !=null && categoryname.equals("")){
            page = blogService.listBlogsByTitleLike(title,pageable);
            list = page.getContent();	// 当前所在页面数据列表
        }else{
            Category category = categoryService.getCategoryByName(categoryname);
            page = blogService.listBlogsByTitleAndCategory(title,category,pageable);
            list = page.getContent();	// 当前所在页面数据列表
        }
        model.addAttribute("page", page);
        model.addAttribute("blogList", list);
        model.addAttribute("categoryList",categoryList);
        return new ModelAndView(async==true?"admin/blog/listBlogs :: #mainContainerRepleace":"admin/blog/listBlogs", "blogModel", model);
    }

    /**
     * 保存博客
     * @param blog
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> saveBlog(@RequestBody Blog blog) {
        try {
            blog.setTags(tagService.listTagsByIds(blog.getTagIds()));
            blogService.saveBlog(blog);
        }catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/admin/blogs";
        return ResponseEntity.ok().body(new Response(true, "添加博客成功", redirectUrl));
    }

    /**
     * 返回添加博客页面
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView addBlog(Model model){
        List<Category> categoryList = categoryService.listCategories();
        List<Tag> tagList = tagService.listTags();
        List<Flag> flagList = flagService.listFlags();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        model.addAttribute("flagList",flagList);
        model.addAttribute("blog", new Blog(null, null, null));
        return new ModelAndView("admin/blog/addBlog", "blogModel", model);
    }

    /**
     * 更新博客
     * @param blog
     * @return
     */
    @ResponseBody
    @PostMapping("/update")
    public ResponseEntity<Response> updateBlog(@RequestBody Blog blog) {
        if(blog.getId() == null){
            return ResponseEntity.ok().body(new Response(true, "未找到博客，出错", null));
        }else{
            System.out.println("blogId"+blog.getId());
            System.out.println("tagsId"+blog.getTagIds());
            Blog originalBlog = blogService.getBlogById(blog.getId());
            originalBlog.setTitle(blog.getTitle());
            originalBlog.setContent(blog.getContent());
            originalBlog.setSummary(blog.getSummary());
            originalBlog.setFlag(blog.getFlag());
            originalBlog.setUpdateTime(new Timestamp(new Date().getTime()));
            originalBlog.setTags(tagService.listTagsByIds(blog.getTagIds()));
            originalBlog.setCategory(blog.getCategory());
            originalBlog.setFlag(blog.getFlag());
            originalBlog.setCopyrightState(blog.getCopyrightState());
            originalBlog.setCommentState(blog.getCommentState());
            blogService.saveBlog(originalBlog);
            return ResponseEntity.ok().body(new Response(true, "成功更新博客", RefreshUrl));
        }

    }

    /**
     * 下线博客
     * @param id
     * @return
     */
    @PostMapping("/offline/{id}")
    public ResponseEntity<Response> offlineBlog(@PathVariable("id") Long id) {
        blogService.offlineBlog(id);
        return ResponseEntity.ok().body(new Response(true, "下线博客成功",RefreshUrl));
    }

    /**
     * 上线博客
     * @param id
     * @return
     */
    @PostMapping("/online/{id}")
    public ResponseEntity<Response> onlineBlog(@PathVariable("id") Long id) {
        blogService.onlineBlog(id);
        return ResponseEntity.ok().body(new Response(true, "成功发布",RefreshUrl));
    }

    /**
     * 获取用户修改界面和回显数据
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        List<Category> categoryList = categoryService.listCategories();
        List<Tag> tagList = tagService.listTags();
        List<Tag> blogTags = blog.getTags();
        String tagsStr = "";
        for(int i=0;i<blogTags.size();i++){
            tagsStr = blogTags.get(i).getId()+ "," +tagsStr;
        }
        List<Flag> flagList = flagService.listFlags();
        System.out.println("标签的值为"+tagsStr);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        model.addAttribute("tagsStr",tagsStr);
        model.addAttribute("blog", blog);
        model.addAttribute("flagList", flagList);
        return new ModelAndView("admin/blog/editBlog", "blogModel", model);
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteBlog(@PathVariable("id") Long id) {
        try {
            blogService.deleteBlog(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        String redirectUrl = "/admin/blogs";
        return ResponseEntity.ok().body(new Response(true, "成功删除博客", redirectUrl));
    }
}
