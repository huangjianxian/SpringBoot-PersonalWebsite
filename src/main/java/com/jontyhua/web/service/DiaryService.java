package com.jontyhua.web.service;

import com.jontyhua.web.domain.Diary;
import com.jontyhua.web.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 日记服务类
 */
public interface DiaryService {

    /**
     * 增删改查
     */

    /**
     * 新增日记
     * @param diary
     * @return
     */
    Diary saveDiary(Diary diary);

    /**
     * 更新日记
     * @param diary
     * @return
     */
    Diary updateDiary(Diary diary);

    /**
     * 根据id 删除日记
     * @param id
     */
    void deleteDiary(Long id);

    /**
     * 根据id查找日记实体
     * @param id
     * @return
     */
    Diary findDiaryById(Long id);

    /**
     * 查询所有上线 用于前端首页展示
     * @return
     */
    List<Diary> listDiariesByOnlineState();

    /**
     * 下线日记
     * @param id
     * @return
     */
    Diary offlineDiary(Long id);

    /**
     * 上线日记
     * @param id
     * @return
     */
    Diary onlineDiary(Long id);

    /**
     * 分页展示所有日记
     * @param pageable
     * @return
     */
    Page<Diary> listDiary(Pageable pageable);

}
