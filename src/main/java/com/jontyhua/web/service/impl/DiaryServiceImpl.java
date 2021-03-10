package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Diary;
import com.jontyhua.web.domain.Notice;
import com.jontyhua.web.repository.DiaryRepository;
import com.jontyhua.web.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName DiaryServiceImpl
 * @Author: JontyHua
 * @Date: 2019/4/30
 * @Description: 日记服务 实现类
 */
@Service
public class DiaryServiceImpl implements DiaryService {

    /** 发布状态 */
    private static final int ONLINE = 1;

    /** 下线状态 */
    private static final int OFFLINE = 0;

    @Autowired
    private DiaryRepository diaryRepository;

    /**
     * 新增日记
     *
     * @param diary
     * @return
     */
    @Override
    public Diary saveDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    /**
     * 更新日记
     *
     * @param diary
     * @return
     */
    @Override
    public Diary updateDiary(Diary diary) {
        // todo
        return diaryRepository.save(diary);
    }

    /**
     * 根据id 删除日记
     *
     * @param id
     */
    @Override
    public void deleteDiary(Long id) {
        diaryRepository.delete(id);
    }

    /**
     * 根据id查找日记实体
     *
     * @param id
     * @return
     */
    @Override
    public Diary findDiaryById(Long id) {
        return diaryRepository.findOne(id);
    }

    /**
     * 查询所有上线 用于前端首页展示
     *
     * @return
     */
    @Override
    public List<Diary> listDiariesByOnlineState() {

        return diaryRepository.findAllByStateEquals(ONLINE);
    }

    /**
     * 下线日记
     *
     * @param id
     * @return
     */
    @Override
    public Diary offlineDiary(Long id) {
        Diary diary = diaryRepository.findOne(id);
        diary.setState(OFFLINE);
        return diaryRepository.save(diary);
    }

    /**
     * 上线日记
     *
     * @param id
     * @return
     */
    @Override
    public Diary onlineDiary(Long id) {
        Diary diary = diaryRepository.findOne(id);
        diary.setState(ONLINE);
        return diaryRepository.save(diary);
    }

    /**
     * 分页展示所有日记
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Diary> listDiary(Pageable pageable) {
        return diaryRepository.findAll(pageable);
    }


}
