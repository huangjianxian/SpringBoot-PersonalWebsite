package com.jontyhua.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Comment
 * @Author: JontyHua
 * @Date: 2019/4/18
 * @Description: 评论实体
 */
@Entity
public class Comment implements Serializable {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;//所属文章

    @ManyToOne
    private Comment parentComment;

    // 回复的
    @OneToMany(mappedBy = "parentComment",cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private List<Comment> replyComments = new ArrayList<>();

//    protected Comment() {
//        // TODO Auto-generated constructor stub
//    }

    public Comment(){

    }

    public Comment(User user, String content) {
        this.content = content;
        this.user = user;
    }

    public Comment(User user, Blog blog, String content) {
        this.content = content;
        this.user = user;
        this.blog = blog;
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

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }
}
