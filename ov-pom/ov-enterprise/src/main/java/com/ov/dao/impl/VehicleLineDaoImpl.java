package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.VehicleLine;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.VehicleLineDao;
@Repository("vehicleLineDaoImpl")
public class VehicleLineDaoImpl extends  BaseDaoImpl<VehicleLine,Long> implements VehicleLineDao {

}