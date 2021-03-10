package com.jontyhua.web.service;

import com.jontyhua.web.domain.Notice;
import java.util.List;

public interface NoticeService {
    /*
     * 因为是公告，所以没有修改公告信息的必要，直接删除或者下线即可
     * 增、删、查、下线 四个功能
     */

    /**
     * 保存公告
     * @param notice
     * @return
     */
    Notice saveNotice(Notice notice);

    /**
     * 查询所有的公告
     * @return
     */
    List<Notice> listNotices();

    /**
     * 查询所有上线公告 用于前端首页展示
     * @return
     */
    List<Notice> listNoticesByOnlineState();

    /**
     * 下线公告信息
     * @param id
     * @return
     */
    Notice offlineNotice(Long id);

    /**
     * 上线公告信息
     * @param id
     * @return
     */
    Notice onlineNotice(Long id);

    /**
     * 删除公告
     * @param id
     */
    void deleteNotice(Long id);

}
