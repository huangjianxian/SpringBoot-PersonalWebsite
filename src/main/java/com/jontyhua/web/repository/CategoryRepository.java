package com.jontyhua.web.repository;

import com.jontyhua.web.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * 根据名字查询分类集合
     * @param name
     * @return
     */
    Category findByName(String name);

    /**
     * 查找分类
     * @param pageable
     * @return
     */
    @Query("select ctg from Category ctg")
    List<Category> findTopCategory(Pageable pageable);

}
