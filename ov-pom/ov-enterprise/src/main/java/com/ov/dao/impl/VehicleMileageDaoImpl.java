package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.VehicleMileage;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.VehicleMileageDao;
@Repository("vehicleMileageDaoImpl")
public class VehicleMileageDaoImpl extends  BaseDaoImpl<VehicleMileage,Long> implements VehicleMileageDao {

}