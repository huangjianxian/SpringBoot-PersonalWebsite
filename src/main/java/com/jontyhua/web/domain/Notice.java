package com.jontyhua.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName Notice
 * @Author: JontyHua
 * @Date: 2019/4/28
 * @Description: 公告实体类
 */
@Entity
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 唯一标识

    @NotEmpty(message = "公告内容不能为空")
    @Size(min=2, max=50)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    @Column(nullable = false)
    private int state;   //公告状态 ‘1’为发布，‘0’为下线

    protected Notice(){

    }

    public Notice(String content, Timestamp createTime) {
        this.content = content;
        this.createTime = createTime;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
