package com.jontyhua.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeaveMessage
 * @Author: JontyHua
 * @Date: 2019/4/18
 * @Description: 留言实体类
 */
@Entity
public class LeaveMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //主键

    @NotEmpty(message = "评论内容不能为空")
    @Size(max = 500, message = "评论内容不能多于500个字符")
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    @ManyToOne
    private LeaveMessage parentLeaveMessage;

    // 回复的
    @OneToMany(mappedBy = "parentLeaveMessage",cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private List<LeaveMessage> replyLeaveMessages = new ArrayList<>();

    public LeaveMessage() {
    }

    public LeaveMessage(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public LeaveMessage getParentLeaveMessage() {
        return parentLeaveMessage;
    }

    public void setParentLeaveMessage(LeaveMessage parentLeaveMessage) {
        this.parentLeaveMessage = parentLeaveMessage;
    }

    public List<LeaveMessage> getReplyLeaveMessages() {
        return replyLeaveMessages;
    }

    public void setReplyLeaveMessages(List<LeaveMessage> replyLeaveMessages) {
        this.replyLeaveMessages = replyLeaveMessages;
    }
}
