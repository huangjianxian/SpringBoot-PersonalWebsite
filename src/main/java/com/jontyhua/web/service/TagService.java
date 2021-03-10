package com.jontyhua.web.service;

import com.jontyhua.web.domain.Tag;
import java.util.List;

public interface TagService {
    /**
     * 保存Tag
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 删除Tag
     * @param id
     * @return
     */
    void removeTag(Long id);

    /**
     * 根据id获取Tag
     * @param id
     * @return
     */
    Tag getTagById(Long id);

    /**
     * 根据name获取Tag
     * @param name
     * @return
     */
    Tag getTagByName(String name);


    /**
     * 获取Tag列表
     * @return
     */
    List<Tag> listTags();

    /**
     * 根据Ids获取Tag列表 用于前端添加博客的时候 添加多个标签
     * @return
     */
    List<Tag> listTagsByIds(String ids);

    /**
     * 判断是否存在
     * @param tag
     * @return
     */
    Boolean isExistTag(Tag tag);

}
