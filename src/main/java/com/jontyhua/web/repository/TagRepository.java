package com.jontyhua.web.repository;

import com.jontyhua.web.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {

    /**
     * 根据名称查找标签
     * @param name
     * @return
     */
    Tag findByName(String name);

}
