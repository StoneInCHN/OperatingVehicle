package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.VehicleSchedulingDao;
import com.ov.entity.VehicleScheduling;
import com.ov.framework.dao.impl.BaseDaoImpl;

@Repository("vehicleSchedulingDaoImpl")
public class VehicleSchedulingDaoImpl extends BaseDaoImpl<VehicleScheduling, Long> implements VehicleSchedulingDao{

}
