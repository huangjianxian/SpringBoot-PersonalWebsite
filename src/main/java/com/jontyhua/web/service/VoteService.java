package com.jontyhua.web.service;

import com.jontyhua.web.domain.User;
import com.jontyhua.web.domain.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VoteService {

	/**
	 * 根据id获取 Vote
	 * @param id
	 * @return
	 */
	Vote getVoteById(Long id);

	/**
	 * 删除Vote
	 * @param id
	 * @return
	 */
	void removeVote(Long id);

	/**
	 * 查询某用户的所有点赞
	 * @param user
	 * @param pageable
	 * @return
	 */
	Page<Vote> listVotesByUser(User user, Pageable pageable);
}
