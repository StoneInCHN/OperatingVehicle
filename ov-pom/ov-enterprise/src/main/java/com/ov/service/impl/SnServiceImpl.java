package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ov.dao.SnDao;
import com.ov.entity.Sn.Type;
import com.ov.service.SnService;

/**
 * Service - 序列号
 * 
 * @author 
 * @version 
 */
@Service("snServiceImpl")
public class SnServiceImpl implements SnService {

	@Resource(name = "snDaoImpl")
	private SnDao snDao;

	@Transactional
	public String generate(Type type) {
		return snDao.generate(type);
	}

}