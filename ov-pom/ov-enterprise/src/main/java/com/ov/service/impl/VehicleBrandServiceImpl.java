package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.VehicleBrand;
import com.ov.dao.VehicleBrandDao;
import com.ov.service.VehicleBrandService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("vehicleBrandServiceImpl")
public class VehicleBrandServiceImpl extends BaseServiceImpl<VehicleBrand,Long> implements VehicleBrandService {

      @Resource(name="vehicleBrandDaoImpl")
      public void setBaseDao(VehicleBrandDao vehicleBrandDao) {
         super.setBaseDao(vehicleBrandDao);
  }
}