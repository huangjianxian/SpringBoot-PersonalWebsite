package com.jontyhua.web.service.impl;

import com.jontyhua.web.domain.Authority;
import com.jontyhua.web.repository.AuthorityRepository;
import com.jontyhua.web.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}

}
