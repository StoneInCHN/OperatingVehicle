package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.VehicleBrandDetail;
import com.ov.dao.VehicleBrandDetailDao;
import com.ov.service.VehicleBrandDetailService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("vehicleBrandDetailServiceImpl")
public class VehicleBrandDetailServiceImpl extends BaseServiceImpl<VehicleBrandDetail,Long> implements VehicleBrandDetailService {

      @Resource(name="vehicleBrandDetailDaoImpl")
      public void setBaseDao(VehicleBrandDetailDao vehicleBrandDetailDao) {
         super.setBaseDao(vehicleBrandDetailDao);
  }
}