package com.jontyhua.web.service;

import com.jontyhua.web.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    /**
     * 保存Category
     * @param category
     * @return
     */
    Category saveCategory(Category category);

    /**
     * 删除Category
     * @param id
     * @return
     */
    void removeCategory(Long id);

    /**
     * 根据id获取Category
     * @param id
     * @return
     */
    Category getCategoryById(Long id);

    /**
     * 根据name获取category
     * @param name
     * @return
     */
    Category getCategoryByName(String name);


    /**
     * 获取Category列表
     * @return
     */
    List<Category> listCategories();

    /**
     * 判断是否存在
     * @param category
     * @return
     */
    Boolean isExistCategory(Category category);

    /**
     * 展示博客数量比较多的分类
     * @param size
     * @return
     */
    List<Category> listTopCategory(Integer size);

}
