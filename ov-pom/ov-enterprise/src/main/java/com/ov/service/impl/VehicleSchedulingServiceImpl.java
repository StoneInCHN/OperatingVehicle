package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.impl.VehicleSchedulingDaoImpl;
import com.ov.entity.VehicleScheduling;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.VehicleSchedulingService;

@Service("vehicleSchedulingServiceImpl")
public class VehicleSchedulingServiceImpl extends BaseServiceImpl<VehicleScheduling, Long> implements VehicleSchedulingService{

	@Resource(name = "vehicleSchedulingDaoImpl")
	public void setBaseDao(VehicleSchedulingDaoImpl vehicleSchedulingDaoImpl){
		super.setBaseDao(vehicleSchedulingDaoImpl);
	}
	
}
