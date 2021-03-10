package com.jontyhua.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName FriendLink
 * @Author: JontyHua
 * @Date: 2019/5/13
 * @Description: 类型实体
 */
@Entity
public class FriendLink implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 唯一标识

    @NotEmpty(message = "名称不能为空")
    @Column(nullable = false,unique = true)
    private String name;

    @NotEmpty(message = "链接不能为空")
    @Column(nullable = false,unique = true)
    private String url;

    protected FriendLink(){

    }

    public FriendLink(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
