package com.jontyhua.web.repository;

import com.jontyhua.web.domain.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 日记 仓库类
 */
public interface DiaryRepository extends JpaRepository<Diary,Long> {

    /**
     * 根据state查询所有的公告 查询发布上线的公告 ONLINE
     * @param state
     * @return
     */
    List<Diary> findAllByStateEquals(int state);

}
