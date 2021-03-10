package com.jontyhua.web.domain;

import com.github.rjeschke.txtmark.Processor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Blog
 * @Author: JontyHua
 * @Date: 2019/4/9
 * @Description: 博客实体
 */
@Entity // 实体
public class  Blog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @NotEmpty(message = "标题不能为空")
    @Size(min=2, max=50)
    @Column(nullable = false, length = 50) // 映射为字段，值不能为空
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min=2, max=300)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String summary;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min=2)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min=2)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String htmlContent; // 将 md 转为 html

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    @Column
    private Timestamp updateTime;   // 更新时间

    @Column(name="readSize")
    private Integer readSize = 0; // 访问量、阅读量

    @Column(name="commentSize")
    private Integer commentSize = 0;  // 评论量

    @Column(name="voteSize")
    private Integer voteSize = 0;  // 点赞量

    @Column(name="copyrightState")
    private Boolean copyrightState; //版权状态，是否开启版权信息

    @Column(name="commentState")
    private Boolean commentState; // 是否开启评论

    @Column(name="state")
    private int state; // 是否发布，设置保存为草稿还是发布出去 "1" 为发布状态 "0" 为下线状态

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="category_id") // 指定外键列 避免产生另外一张关联表
    private Category category;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="flag_id") // 指定外键列 避免产生另外一张关联表
    private Flag flag;  //标记，是原创、转载还是翻译等

    // 多对多关系
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "blog_tag", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    // 博客与评论 一对多 文章删除后 相应的评论也会一并删除
    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private List<Comment> comments = new ArrayList<>();

    // 点赞
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "blog_vote", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private List<Vote> votes;

    // 不存入数据库
    @Transient
    private String tagIds;

//    protected Blog(){
//
//    }

    public Blog(){

    }

    public Blog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Blog(String title, String summary, String content, Category category) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content)  {
        this.content = content;
        this.htmlContent = Processor.process(content);
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Boolean getCopyrightState() {
        return copyrightState;
    }

    public void setCopyrightState(Boolean copyrightState) {
        this.copyrightState = copyrightState;
    }

    public Boolean getCommentState() {
        return commentState;
    }

    public void setCommentState(Boolean commentState) {
        this.commentState = commentState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        // 同时修改评论数量
        this.commentSize = this.comments.size();
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
        this.voteSize = this.votes.size();
    }

    /**
     * 点赞
     * @param vote
     * @return
     */
    public boolean addVote(Vote vote) {
        boolean isExist = false;
        // 判断重复
        for (int index=0; index < this.votes.size(); index ++ ) {
            if (this.votes.get(index).getUser().getId() == vote.getUser().getId()) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            this.votes.add(vote);
            this.voteSize = this.votes.size();
        }

        return isExist;
    }
    /**
     * 取消点赞
     * @param voteId
     */
    public void removeVote(Long voteId) {
        for (int index=0; index < this.votes.size(); index ++ ) {
            if (this.votes.get(index).getId() == voteId) {
                this.votes.remove(index);
                break;
            }
        }

        this.voteSize = this.votes.size();
    }
}
