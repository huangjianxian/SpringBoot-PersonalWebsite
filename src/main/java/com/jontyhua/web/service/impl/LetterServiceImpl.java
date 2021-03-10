package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Letter;
import com.jontyhua.web.repository.LetterRepository;
import com.jontyhua.web.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @ClassName LetterServiceImpl
 * @Author: JontyHua
 * @Date: 2019/5/14
 * @Description:
 */
@Service
public class LetterServiceImpl implements LetterService {

    @Autowired
    private LetterRepository letterRepository;

    /**
     * 保存私信
     *
     * @param letter
     * @return
     */
    @Override
    public Letter saveLetter(Letter letter) {
        return letterRepository.save(letter);
    }

    /**
     * 根据id删除私信
     *
     * @param id
     */
    @Override
    public void deleteLetter(Long id) {
        letterRepository.delete(id);
    }

    /**
     * 分页查询所有用户的所有私信
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Letter> listLetters(Pageable pageable) {
        return letterRepository.findAll(pageable);
    }
}
