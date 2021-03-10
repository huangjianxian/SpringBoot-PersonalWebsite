package com.jontyhua.web.service;

import com.jontyhua.web.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Interface UserService
 * @Author: JONTY HUA
 * @Date: 2019/3/27 22:02
 * @Description: 用户服务接口
 */
public interface UserService {

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * 删除id用户
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUser();

    /**
     * 根据用户名列表查询用户
     * @param username
     * @return
     */
    List<User> listUserByUsername(Collection<String> username);

    /**
     * 根据用户用户名进行分页模糊查询
     * @param username
     * @param pageable
     * @return
     */
    Page<User> listUsersByUsernameLike(String username, Pageable pageable);

    /**
     * 保存用户
     * @param user
     */
    User saveUser(User user);

    /**
     * 根据email and code 查找用户
     * @param email
     * @param code
     * @return
     */
    User findByEmailAndCode(String email,String code);

    /**
     * 根据验证码来查询用户
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据邮箱查找用户
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
