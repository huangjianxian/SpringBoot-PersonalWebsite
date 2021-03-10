package com.jontyhua.web.controller;


import com.jontyhua.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ArchivesController
 * @Author: JontyHua
 * @Date: 2019/4/21
 * @Description: 归档文章
 */
@Controller
public class ArchivesController {

    @Autowired
    private BlogService blogService;

//    @GetMapping("/archives")
//    public String listBlogsByMonth(Model model){
//        model.addAttribute("archiveMap",blogService.listBlogsByArchive());
//        model.addAttribute("blogCount",blogService.countBlog());
//        return "archives";
//    }

}
