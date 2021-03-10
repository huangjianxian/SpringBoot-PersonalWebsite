package com.jontyhua.web.domain;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  自增长策略
    private Long id;    //主键id

    @Column(nullable = false)
    private String name;    //名称

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

    @Override
    public String getAuthority() {
        return name;
    }
}
