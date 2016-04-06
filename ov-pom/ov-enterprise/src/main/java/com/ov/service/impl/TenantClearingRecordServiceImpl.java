package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.impl.TenantClearingRecordDaoImpl;
import com.ov.entity.TenantClearingRecord;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.TenantClearingRecordService;

@Service("tenantClearingRecordServiceImpl")
public class TenantClearingRecordServiceImpl extends BaseServiceImpl<TenantClearingRecord, Long> implements TenantClearingRecordService{

	@Resource(name = "tenantClearingRecordDaoImpl")
	public void setBaseDao(TenantClearingRecordDaoImpl tenantClearingRecordDaoImpl){
		super.setBaseDao(tenantClearingRecordDaoImpl);
	}
	
}
