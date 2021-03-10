package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.LeaveMessage;
import com.jontyhua.web.domain.User;
import com.jontyhua.web.repository.LeaveMessageRepository;
import com.jontyhua.web.service.LeaveMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeaveMessageServiceImpl
 * @Author: JontyHua
 * @Date: 2019/5/5
 * @Description:
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageRepository leaveMessageRepository;

    /**
     * 获取所有留言
     *
     * @return
     */
    @Override
    public List<LeaveMessage> getLeaveMessages() {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        List<LeaveMessage> leaveMessages = leaveMessageRepository.findByParentLeaveMessageNull(sort);
        return eachLeaveMessage(leaveMessages);
    }

    /**
     * 保存留言
     *
     * @param leaveMessage
     * @return
     */
    @Transactional
    @Override
    public LeaveMessage saveLeaveMessage(LeaveMessage leaveMessage) {
        Long parentLeaveMessageId = leaveMessage.getParentLeaveMessage().getId();
        if(parentLeaveMessageId != -1){
            leaveMessage.setParentLeaveMessage(leaveMessageRepository.findOne(parentLeaveMessageId));
        }else{
            leaveMessage.setParentLeaveMessage(null);
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        leaveMessage.setUser(user);
        return leaveMessageRepository.save(leaveMessage);
    }

    /**
     * 根据id获取 LeaveMessage
     *
     * @param id
     * @return
     */
    @Override
    public LeaveMessage getLeaveMessageById(Long id) {
        return leaveMessageRepository.findOne(id);
    }

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    @Override
    public void deleteLeaveMessage(Long id) {
        leaveMessageRepository.delete(id);
    }

    /**
     * 计算留言数目
     *
     * @return
     */
    @Override
    public Integer countLeaveMessageSize() {
//        return leaveMessageRepository.countAllByParentLeaveMessage();
        return null;
    }

    /**
     * 回复留言
     *
     * @param leaveMessageId
     * @param replyId
     * @param leaveMessageContent
     * @return
     */
    @Override
    public LeaveMessage replyLeaveMessage(Long leaveMessageId, Long replyId, String leaveMessageContent) {
        return null;
    }

    /**
     * 查询某用户的所有留言
     *
     * @param user
     * @param pageable
     * @return
     */
    @Override
    public Page<LeaveMessage> listLeaveMessagesByUser(User user, Pageable pageable) {
        return leaveMessageRepository.findAllByUser(user,pageable);
    }

    /**
     * 获取某个用户的顶级评论
     *
     * @param user
     * @return
     */
    @Override
    public List<LeaveMessage> listLeaveMessageByUserAndParentNull(User user) {
        return leaveMessageRepository.findAllByUserAndParentLeaveMessageNull(user);
    }


    // 处理评论层级
    /**
     * 循环每个顶级的评论节点
     * @param leaveMessages
     * @return
     */
    private List<LeaveMessage> eachLeaveMessage(List<LeaveMessage> leaveMessages) {
        List<LeaveMessage> leaveMessagesView = new ArrayList<>();
        for (LeaveMessage leaveMessage : leaveMessages) {
            LeaveMessage c = new LeaveMessage();
            BeanUtils.copyProperties(leaveMessage,c);
            leaveMessagesView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(leaveMessagesView);
        return leaveMessagesView;
    }

    /**
     *
     * @param leaveMessages root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<LeaveMessage> leaveMessages) {

        for (LeaveMessage leaveMessage : leaveMessages) {
            List<LeaveMessage> replys1 = leaveMessage.getReplyLeaveMessages();
            for(LeaveMessage reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            leaveMessage.setReplyLeaveMessages(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<LeaveMessage> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     * @param leaveMessage 被迭代的对象
     * @return
     */
    private void recursively(LeaveMessage leaveMessage) {
        tempReplys.add(leaveMessage);//顶节点添加到临时存放集合
        if (leaveMessage.getReplyLeaveMessages().size()>0) {
            List<LeaveMessage> replys = leaveMessage.getReplyLeaveMessages();
            for (LeaveMessage reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyLeaveMessages().size()>0) {
                    recursively(reply);
                }
            }
        }
    }

}
