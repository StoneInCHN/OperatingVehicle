package com.ov.dao.impl; 

import org.springframework.stereotype.Repository;

import com.ov.dao.VehicleOilDao;
import com.ov.entity.VehicleOil;
import com.ov.framework.dao.impl.BaseDaoImpl;
@Repository("vehicleOilDaoImpl")
public class VehicleOilDaoImpl extends  BaseDaoImpl<VehicleOil,Long> implements VehicleOilDao {

}