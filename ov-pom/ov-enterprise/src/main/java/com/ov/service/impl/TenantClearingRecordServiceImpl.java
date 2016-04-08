package com.ov.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ov.dao.impl.TenantClearingRecordDaoImpl;
import com.ov.entity.Sn.Type;
import com.ov.entity.TenantClearingRecord;
import com.ov.entity.TenantInfo;
import com.ov.entity.VehicleScheduling;
import com.ov.entity.commonenum.CommonEnum.ClearingStatus;
import com.ov.entity.commonenum.CommonEnum.VehicleSchedulingStatus;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.SnService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantClearingRecordService;
import com.ov.service.TenantInfoService;
import com.ov.service.VehicleSchedulingService;
import com.ov.utils.DateTimeUtils;

@Service("tenantClearingRecordServiceImpl")
public class TenantClearingRecordServiceImpl extends BaseServiceImpl<TenantClearingRecord, Long> implements TenantClearingRecordService{

	@Autowired
	private TenantAccountService tenantAccountService;
	
	@Autowired
	private TenantInfoService tenantInfoService;
	
	@Autowired
	private SnService snService;
	
	@Autowired
	private VehicleSchedulingService vehicleSchedulingService;
	
	@Resource(name = "tenantClearingRecordDaoImpl")
	public void setBaseDao(TenantClearingRecordDaoImpl tenantClearingRecordDaoImpl){
		super.setBaseDao(tenantClearingRecordDaoImpl);
	}

	@Transactional
	@Override
	public void saveClearingRecord(TenantClearingRecord clearingRecord,
			Long branchBusinessId, String startDate, String endDate) throws ParseException {
		// TODO Auto-generated method stub
		TenantInfo branchBusiness = tenantInfoService.find(branchBusinessId);
		clearingRecord.setChild(branchBusiness);
		clearingRecord.setParent(tenantAccountService.getCurrentTenantInfo());
		clearingRecord.setClearingSn(snService.generate(Type.clearing));
		clearingRecord.setClearingStatus(ClearingStatus.SUCCESS);
		
		List<Filter> filters = new ArrayList<Filter>();
	    Filter filter = new Filter("requestBusiness", Operator.eq, branchBusiness);
	    filters.add(filter);
	    filter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
	    filters.add(filter);
	    filter = new Filter("status", Operator.eq, VehicleSchedulingStatus.FINISHED);
	    filters.add(filter);
		if ( !StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			filter = new Filter("startDate", Operator.gt, DateTimeUtils.convertStringToDate(startDate, null));
		    filters.add(filter);
		    filter = new Filter("startDate", Operator.lt, DateTimeUtils.convertStringToDate(endDate, null));
		    filters.add(filter);
		}
		List<VehicleScheduling> vehicleSchedulings = vehicleSchedulingService.findList(null, filters, null);
		BigDecimal totalDistance = new BigDecimal(0);
		for (VehicleScheduling vehicleScheduling : vehicleSchedulings) {
			totalDistance = totalDistance.add(vehicleScheduling.getTotalDistance());
		}
		clearingRecord.setVehicleSchedulings(vehicleSchedulings);
		
		BigDecimal amountOfCurrent = totalDistance.multiply(clearingRecord.getUnitPrice());
		clearingRecord.setTotalDistance(totalDistance);
		clearingRecord.setAmountOfCurrent(amountOfCurrent);

		save(clearingRecord);
		
		for (VehicleScheduling vehicleScheduling : vehicleSchedulings) {
			totalDistance = totalDistance.add(vehicleScheduling.getTotalDistance());
			vehicleScheduling.setStatus(VehicleSchedulingStatus.CLEARED);
			vehicleScheduling.setClearingRecord(clearingRecord);
			vehicleSchedulingService.update(vehicleScheduling);
		}
	}
	
}
