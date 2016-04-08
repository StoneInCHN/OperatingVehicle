package com.ov.service;

import java.text.ParseException;

import com.ov.entity.TenantClearingRecord;
import com.ov.framework.service.BaseService;

public interface TenantClearingRecordService extends BaseService<TenantClearingRecord, Long>{

	void saveClearingRecord(TenantClearingRecord clearingRecord, Long branchBusinessId,
			String startDate, String endDate)  throws ParseException;
	
}
