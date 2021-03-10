package com.jontyhua.web.repository;

import com.jontyhua.web.domain.LeaveMessage;
import com.jontyhua.web.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveMessageRepository extends JpaRepository<LeaveMessage,Long> {

    List<LeaveMessage> findByParentLeaveMessageNull(Sort sort);

    /**
     * 查询某用户的所有留言
     * @param user
     * @param pageable
     * @return
     */
    Page<LeaveMessage> findAllByUser(User user, Pageable pageable);

    /**
     * 获取用户的顶级留言
     * @param user
     * @return
     */
    List<LeaveMessage> findAllByUserAndParentLeaveMessageNull(User user);
}
