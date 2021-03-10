package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.User;
import com.jontyhua.web.domain.Vote;
import com.jontyhua.web.repository.VoteRepository;
import com.jontyhua.web.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Override
	@Transactional
	public void removeVote(Long id) {
		voteRepository.delete(id);
	}

	/**
	 * 查询某用户的所有点赞
	 *
	 * @param user
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<Vote> listVotesByUser(User user, Pageable pageable) {
		return voteRepository.findAllByUser(user,pageable);
	}

	@Override
	public Vote getVoteById(Long id) {
		return voteRepository.findOne(id);
	}


}
