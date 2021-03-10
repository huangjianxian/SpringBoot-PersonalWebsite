package com.jontyhua.web.service;

import com.jontyhua.web.domain.LeaveMessage;
import com.jontyhua.web.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LeaveMessageService {

    /**
     * 获取所有留言
     * @return
     */
    List<LeaveMessage> getLeaveMessages();

    /**
     * 保存留言
     * @param leaveMessage
     * @return
     */
    LeaveMessage saveLeaveMessage(LeaveMessage leaveMessage);

    /**
     * 根据id获取 LeaveMessage
     * @param id
     * @return
     */
    LeaveMessage getLeaveMessageById(Long id);

    /**
     * 删除留言
     * @param id
     * @return
     */
    void deleteLeaveMessage(Long id);

    /**
     * 计算留言数目
     * @return
     */
    Integer countLeaveMessageSize();

    /**
     * 回复留言
     * @param leaveMessageId
     * @param replyId
     * @param leaveMessageContent
     * @return
     */
    LeaveMessage replyLeaveMessage(Long leaveMessageId, Long replyId, String leaveMessageContent);

    /**
     * 查询某用户的所有留言
     * @param user
     * @param pageable
     * @return
     */
    Page<LeaveMessage> listLeaveMessagesByUser(User user, Pageable pageable);

    /**
     * 获取某个用户的顶级评论
     * @param user
     * @return
     */
    List<LeaveMessage> listLeaveMessageByUserAndParentNull(User user);
}
