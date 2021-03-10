package com.jontyhua.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName Feedback
 * @Author: JontyHua
 * @Date: 2019/5/14
 * @Description: 反馈实体类
 */
@Entity
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //主键

    @NotEmpty(message = "反馈内容不能为空")
    @Size(max = 500, message = "评论内容不能多于500个字符")
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;


    public Feedback() {
    }

    public Feedback(String content, User user) {
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

}
