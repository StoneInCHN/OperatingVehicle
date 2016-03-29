package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.VehicleMileage;
import com.ov.dao.VehicleMileageDao;
import com.ov.service.VehicleMileageService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("vehicleMileageServiceImpl")
public class VehicleMileageServiceImpl extends BaseServiceImpl<VehicleMileage,Long> implements VehicleMileageService {

      @Resource(name="vehicleMileageDaoImpl")
      public void setBaseDao(VehicleMileageDao vehicleMileageDao) {
         super.setBaseDao(vehicleMileageDao);
  }
}