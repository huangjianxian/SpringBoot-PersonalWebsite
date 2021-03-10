package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.FriendLink;
import com.jontyhua.web.repository.FriendLinkRepository;
import com.jontyhua.web.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FriendLinkServiceImpl
 * @Author: JontyHua
 * @Date: 2019/5/1
 * @Description: 友情链接
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkRepository friendLinkRepository;

    @Override
    public List<FriendLink> listFriendLink() {
        return friendLinkRepository.findAll();
    }

    /**
     * 新增友链
     *
     * @param friendLink
     * @return
     */
    @Override
    public FriendLink save(FriendLink friendLink) {
        return friendLinkRepository.save(friendLink);
    }

    /**
     * 删除友链
     *
     * @param id
     */
    @Override
    public void deleteFriendLink(Long id) {
        friendLinkRepository.delete(id);
    }
}
