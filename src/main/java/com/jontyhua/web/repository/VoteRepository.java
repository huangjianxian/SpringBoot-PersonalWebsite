package com.jontyhua.web.repository;

import com.jontyhua.web.domain.User;
import com.jontyhua.web.domain.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>{

    /**
     * 查询某个用户的点赞相关
     * @param user
     * @return
     */
    Page<Vote> findAllByUser(User user, Pageable pageable);

}
