package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Authority;
import com.jontyhua.web.domain.User;
import com.jontyhua.web.repository.UserRepository;
import com.jontyhua.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author: JONTY HUA
 * @Date: 2019/3/27 22:10
 * @Description: 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired  //自动装配用户仓库
    private UserRepository userRepository;

    @Transactional  //事务
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listUserByUsername(Collection<String> username) {
        return userRepository.findByUsernameIn(username);
    }

    @Override
    public Page<User> listUsersByUsernameLike(String username, Pageable pageable) {
        // 模糊查询
        username = "%" + username + "%";
        Page<User> users = userRepository.findByUsernameLike(username, pageable);
        return users;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmailAndCode(String email, String code) {
        return userRepository.findByEmailAndCode(email,code);
    }

    @Override
    public User findByCode(String code) {
        return userRepository.findByCode(code);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }
}
