package com.jontyhua.web.controller;



import com.jontyhua.web.domain.Tag;
import com.jontyhua.web.service.TagService;
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
 * @ClassName tagController
 * @Author: JontyHua
 * @Date: 2019/4/17
 * @Description: 标签控制器
 */
@Controller
@RequestMapping("/admin/tags")
public class TagController {

    /** 刷新页面 URL */
    private static final String RefreshUrl = "/admin/tags";

    @Autowired
    private TagService tagService;

    /**
     * 后台获得所有标签
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listTags(Model model){
        List<Tag> tags = tagService.listTags();
        model.addAttribute("tags", tags);
        return new ModelAndView("admin/tag/listTags","tagModel",model);
    }

    /**
     * 新增标签
     * @param tag
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Tag tag) {
        try {
            tagService.saveTag(tag);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) { //如果分类重复，在这里捕获异常
            return ResponseEntity.ok().body(new Response(false, "该标签已存在"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", RefreshUrl));
    }

    /**
     * 根据id获取修改标签的页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView getTagById(@PathVariable("id") Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag",tag);
        return new ModelAndView("/admin/tag/editTag","tagModel",model);
    }

    /**
     * 删除分类
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteTag(@PathVariable("id") Long id) {
        try {
            tagService.removeTag(id);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "该标签下仍然有文章，不安全操作！"));
        }
        return ResponseEntity.ok().body(new Response(true, "删除标签成功", RefreshUrl));
    }
}
