package com.jontyhua.web.repository;

import com.jontyhua.web.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @Interface UserRepository
 * @Author: JONTY HUA
 * @Date: 2019/3/27 21:54
 * @Description: 用户仓库，用来定义操作
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据用户名分页查询用户列表
     * @param username
     * @param pageable
     * @return
     */
    Page<User> findByUsernameLike(String username, Pageable pageable);


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据邮箱和验证码查询用户，用于注册页面
     * @param email
     * @param code
     * @return
     */
    User findByEmailAndCode(String email,String code);

    /**
     * 根据用户名列表查询
     * @param username
     * @return
     */
    List<User> findByUsernameIn(Collection<String> username);

    /**
     * 根据验证码查询用户
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据昵称查找用户
     * @param nickname
     * @return
     */
    User findByNickname(String nickname);
}
