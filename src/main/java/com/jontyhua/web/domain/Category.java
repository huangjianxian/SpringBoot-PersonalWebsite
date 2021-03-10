package com.jontyhua.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName category
 * @Author: JontyHua
 * @Date: 2019/4/17
 * @Description: 分类实体
 */
@Entity
public class Category implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 唯一标识

    @NotEmpty(message = "名称不能为空")
    @Size(max=20)
    @Column(nullable = false,unique = true)
    private String name;

    protected Category(){

    }

    public Category(String name) {
        this.name = name;
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
}
