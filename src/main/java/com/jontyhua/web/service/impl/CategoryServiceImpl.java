package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Category;
import com.jontyhua.web.repository.CategoryRepository;
import com.jontyhua.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName categoryServiceImpl
 * @Author: JontyHua
 * @Date: 2019/4/17
 * @Description: 分类服务实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 保存Category
     *
     * @param Category
     * @return
     */
    @Transactional
    @Override
    public Category saveCategory(Category Category) {
        // 判断重复
        Category category = categoryRepository.findByName(Category.getName());
        if(category !=null) {
            throw new IllegalArgumentException("该分类已经存在了");
        }else{
            return categoryRepository.save(Category);
        }
    }

    /**
     * 删除Category
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public void removeCategory(Long id) {
        categoryRepository.delete(id);
    }

    /**
     * 根据id获取Category
     *
     * @param id
     * @return
     */
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findOne(id);
    }

    /**
     * 根据name获取category
     *
     * @param name
     * @return
     */
    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }


    /**
     * 获取Category列表
     *
     * @return
     */
    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    /**
     * 判断是否存在
     *
     * @param category
     * @return
     */
    @Override
    public Boolean isExistCategory(Category category) {
        // 判断重复
        Category ctg = categoryRepository.findByName(category.getName());
        if(ctg !=null) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 展示博客数量比较多的分类
     *
     * @param size
     * @return
     */
    @Override
    public List<Category> listTopCategory(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return categoryRepository.findTopCategory(pageable);
    }


}
