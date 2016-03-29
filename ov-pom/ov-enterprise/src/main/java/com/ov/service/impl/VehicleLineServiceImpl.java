package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.VehicleLine;
import com.ov.dao.VehicleLineDao;
import com.ov.service.VehicleLineService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("vehicleLineServiceImpl")
public class VehicleLineServiceImpl extends BaseServiceImpl<VehicleLine,Long> implements VehicleLineService {

      @Resource(name="vehicleLineDaoImpl")
      public void setBaseDao(VehicleLineDao vehicleLineDao) {
         super.setBaseDao(vehicleLineDao);
  }
}