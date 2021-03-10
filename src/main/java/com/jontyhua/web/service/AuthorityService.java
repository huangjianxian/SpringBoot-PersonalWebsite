package com.jontyhua.web.service;

import com.jontyhua.web.domain.Authority;


public interface AuthorityService {

	/**
	 * 根据id获取 Authority
	 * @param id
	 * @return
	 */
	Authority getAuthorityById(Long id);
}
