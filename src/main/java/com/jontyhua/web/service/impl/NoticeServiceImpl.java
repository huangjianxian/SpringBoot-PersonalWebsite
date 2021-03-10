package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Notice;
import com.jontyhua.web.repository.NoticeRepository;
import com.jontyhua.web.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName NoticeServiceImpl
 * @Author: JontyHua
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    /** 发布状态 */
    private static final int ONLINE = 1;

    /** 下线状态 */
    private static final int OFFLINE = 0;

    @Autowired
    private NoticeRepository noticeRepository;

    /**
     * 保存公告
     *
     * @param notice
     * @return
     */
    @Transactional
    @Override
    public Notice saveNotice(Notice notice) {
        notice.setState(ONLINE);
        return noticeRepository.save(notice);
    }

    /**
     * 查询所有的公告
     * 根据时间逆序排序
     *
     * @return
     */
    @Override
    public List<Notice> listNotices() {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return noticeRepository.findAll(sort);
    }

    /**
     * 查询所有上线公告 用于前端首页展示
     *
     * @return
     */
    @Override
    public List<Notice> listNoticesByOnlineState() {
        return noticeRepository.findAllByStateEquals(ONLINE);
    }

    /**
     * 下线公告信息
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Notice offlineNotice(Long id) {
        Notice notice = noticeRepository.findOne(id);
        notice.setState(OFFLINE);
        return noticeRepository.save(notice);
    }

    /**
     * 上线公告信息
     *
     * @param id
     * @return
     */
    @Override
    public Notice onlineNotice(Long id) {
        Notice notice = noticeRepository.findOne(id);
        notice.setState(ONLINE);
        return noticeRepository.save(notice);
    }

    /**
     * 删除公告
     *
     * @param id
     */
    @Override
    public void deleteNotice(Long id) {
        noticeRepository.delete(id);
    }
}
