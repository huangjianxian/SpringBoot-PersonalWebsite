package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Tag;
import com.jontyhua.web.repository.TagRepository;
import com.jontyhua.web.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TagServiceImpl
 * @Author: JontyHua
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * 保存Tag
     *
     * @param tag
     * @return
     */
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * 删除Tag
     *
     * @param id
     * @return
     */
    @Override
    public void removeTag(Long id) {
        tagRepository.delete(id);
    }

    /**
     * 根据id获取Tag
     *
     * @param id
     * @return
     */
    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findOne(id);
    }

    /**
     * 根据name获取Tag
     *
     * @param name
     * @return
     */
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    /**
     * 获取Tag列表
     *
     * @return
     */
    @Override
    public List<Tag> listTags() {
        return tagRepository.findAll();
    }

    /**
     * 根据Ids获取Tag列表 用于前端添加博客的时候 添加多个标签
     *
     * @param ids
     * @return
     */
    @Override
    public List<Tag> listTagsByIds(String ids) {    //a,b,c 获取这个字符串，然后转换成ids
        return tagRepository.findAll(convertToList(ids));
    }

    /**
     * 由字符串转换成为List<Long>
     * @param ids
     * @return
     */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");  //有","分割
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    /**
     * 判断是否存在
     *
     * @param tag
     * @return
     */
    @Override
    public Boolean isExistTag(Tag tag) {
        Tag t = tagRepository.findByName(tag.getName());
        if(t != null){
            return true;
        }else{
            return false;
        }
    }
}
