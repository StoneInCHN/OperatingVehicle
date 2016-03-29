package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.VehicleBrand;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.VehicleBrandDao;
@Repository("vehicleBrandDaoImpl")
public class VehicleBrandDaoImpl extends  BaseDaoImpl<VehicleBrand,Long> implements VehicleBrandDao {

}