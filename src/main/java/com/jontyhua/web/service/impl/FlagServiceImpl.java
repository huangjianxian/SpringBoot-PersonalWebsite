package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Flag;
import com.jontyhua.web.repository.FlagRepository;
import com.jontyhua.web.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FlagServiceImpl
 * @Author: JontyHua
 * @Date: 2019/5/1
 * @Description: 文章类型
 */
@Service
public class FlagServiceImpl implements FlagService {

    @Autowired
    private FlagRepository flagRepository;

    /**
     * Flag
     *
     * @return
     */
    @Override
    public List<Flag> listFlags() {
        return flagRepository.findAll();
    }
}
