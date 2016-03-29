package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.Vehicle;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.VehicleDao;
@Repository("vehicleDaoImpl")
public class VehicleDaoImpl extends  BaseDaoImpl<Vehicle,Long> implements VehicleDao {

}