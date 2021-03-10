package com.jontyhua.web.controller;

import com.jontyhua.web.domain.Category;
import com.jontyhua.web.service.CategoryService;
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
 * @ClassName categoryController
 * @Author: JontyHua
 * @Date: 2019/4/17
 * @Description: 分类控制器
 */
@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private static final String RefreshUrl = "/admin/categories";

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表查询分类
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listCategory(Model model){
        List<Category> categories = categoryService.listCategories();
        model.addAttribute("categories", categories);
        return new ModelAndView("admin/category/listCategories","categoryModel",model);
    }

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Category category) {
        try {
            categoryService.saveCategory(category);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) { //如果分类重复，在这里捕获异常
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", RefreshUrl));
    }

    /**
     * 根据 Id 获取分类信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView getCategoryById(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("Category",category);
        return new ModelAndView("/admin/category/editCategory","categoryModel",model);
    }

    /**
     * 删除分类
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable("id") Long id) {
        try {
            categoryService.removeCategory(id);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "该分类下仍然有文章，不安全操作！"));
        }
        return ResponseEntity.ok().body(new Response(true, "删除分类成功", RefreshUrl));
    }
}
