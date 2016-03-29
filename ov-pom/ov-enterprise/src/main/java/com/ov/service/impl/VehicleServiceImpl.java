package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.Vehicle;
import com.ov.dao.VehicleDao;
import com.ov.service.VehicleService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle,Long> implements VehicleService {

      @Resource(name="vehicleDaoImpl")
      public void setBaseDao(VehicleDao vehicleDao) {
         super.setBaseDao(vehicleDao);
  }
}