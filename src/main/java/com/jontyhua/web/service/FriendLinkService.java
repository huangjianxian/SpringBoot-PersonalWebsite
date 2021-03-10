package com.jontyhua.web.service;

import com.jontyhua.web.domain.FriendLink;

import java.util.List;

public interface FriendLinkService {

    /**
     * Flag
     * @return
     */
    List<FriendLink> listFriendLink();

    /**
     * 新增友链
     * @param friendLink
     * @return
     */
    FriendLink save(FriendLink friendLink);

    /**
     * 删除友链
     * @param id
     */
    void deleteFriendLink(Long id);
}
