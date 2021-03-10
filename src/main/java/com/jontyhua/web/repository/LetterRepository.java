package com.jontyhua.web.repository;

import com.jontyhua.web.domain.Letter;
import com.jontyhua.web.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 私信博主 仓库类
 */
public interface LetterRepository extends JpaRepository<Letter,Long> {

    /**
     * 查询某用户的所有私信
     * @param user
     * @param pageable
     * @return
     */
    Page<Letter> findAllByUser(User user, Pageable pageable);

}
