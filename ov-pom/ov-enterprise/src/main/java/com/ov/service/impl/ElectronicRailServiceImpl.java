package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.impl.ElectronicRailDaoImpl;
import com.ov.entity.ElectronicRail;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.ElectronicRailService;

@Service("electronicRailServiceImpl")
public class ElectronicRailServiceImpl extends BaseServiceImpl<ElectronicRail, Long> implements ElectronicRailService{
	
	@Resource(name = "electronicRailDaoImpl")
	public void setBaseDao(ElectronicRailDaoImpl electronicRailDaoImpl){
		super.setBaseDao(electronicRailDaoImpl);
	}
	
}
