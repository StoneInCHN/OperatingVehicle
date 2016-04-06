package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.TenantClearingRecordDao;
import com.ov.entity.TenantClearingRecord;
import com.ov.framework.dao.impl.BaseDaoImpl;

@Repository("tenantClearingRecordDaoImpl")
public class TenantClearingRecordDaoImpl extends BaseDaoImpl<TenantClearingRecord, Long> implements TenantClearingRecordDao{

}
