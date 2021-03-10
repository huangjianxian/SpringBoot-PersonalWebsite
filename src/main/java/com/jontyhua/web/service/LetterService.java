package com.jontyhua.web.service;

import com.jontyhua.web.domain.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LetterService {

    /**
     * 保存私信
     * @param letter
     * @return
     */
    Letter saveLetter(Letter letter);

    /**
     * 根据id删除私信
     * @param id
     */
    void deleteLetter(Long id);

    /**
     * 分页查询所有用户的所有私信
     * @param pageable
     * @return
     */
    Page<Letter> listLetters(Pageable pageable);

}
