package com.jontyhua.web.repository;

import com.jontyhua.web.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 公告仓库类
 */
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    /**
     * 根据state查询所有的公告
     * 用于查询发布上线的公告 ONLINE
     * @param state
     * @return
     */
    List<Notice> findAllByStateEquals(int state);

}
