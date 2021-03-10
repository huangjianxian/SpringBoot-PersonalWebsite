package com.jontyhua.web.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName Flag
 * @Author: JontyHua
 * @Date: 2019/4/17
 * @Description: 类型实体
 */
@Entity
public class Flag implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 唯一标识

    @NotEmpty(message = "名称不能为空")
    @Size(max=20)
    @Column(nullable = false,unique = true)
    private String name;

    protected Flag(){

    }

    public Flag(String name) {
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
