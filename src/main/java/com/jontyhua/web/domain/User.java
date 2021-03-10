package com.jontyhua.web.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName User
 * @Author: JONTY HUA
 * @Date: 2019/3/27 21:11
 * @Description:    用户实体类
 */
@Entity // 实体，将一个类声明为实体bean
public class User implements UserDetails,Serializable {

    private static final long serialVersionUID = 1L;

    @Id //主键注解
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长策略
    private Long id;    //用户主键

    @NotEmpty(message = "用户名不能为空")  //假设为空，message是提示信息
    @Size(min = 1,max = 20)
    @Column(nullable = false,length = 20,unique = true)   //映射为字段且不能为空，唯一
    private String username;    //用户用户名

    @NotEmpty(message = "昵称不能为空")
    @Size(min = 1,max = 20)
    @Column(nullable = false,length = 20,unique = true)
    private String nickname;    //用户昵称

    @NotEmpty(message = "邮箱不能为空")
    @Size(max = 50)
    @Column(nullable = false,length = 50,unique = true)
    @Email(message = "邮箱格式不对")
    private String email;   //用户邮箱

    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(length = 100)
    private String password;    //密码

    @Column(length = 200)
    private String avatarurl;   //头像地址

    @NotEmpty(message = "性别不能为空")
    @Column(length = 4)
    private String gender;  //性别

    @NotEmpty(message = "出生年份不能为空")
    @Column(length = 10)
    private String birth;   //出生年份

    @Column(length = 2)
    private int state;  //用户状态  1：已经激活  0：未激活

    @Column(length = 40)
    private String code;    //注册时 生成的随机字符串

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    protected User(){   //防止直接使用

    }

    // 构造方法
    public User(String username, String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String nickname, String email, String password, String gender, String birth, int state) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birth = birth;
        this.state = state;
    }

    // 多对多关系
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  将List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
        for(GrantedAuthority authority : this.authorities){
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return simpleAuthorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodePasswd = encoder.encode(password);
        this.password = password;
    }

    /**
     * 加密密码
     * @param password
     */
    public void setEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(password);
        this.password = encodePasswd;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
