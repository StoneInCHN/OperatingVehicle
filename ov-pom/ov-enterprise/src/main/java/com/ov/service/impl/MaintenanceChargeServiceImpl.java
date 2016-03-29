package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.MaintenanceCharge;
import com.ov.dao.MaintenanceChargeDao;
import com.ov.service.MaintenanceChargeService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("maintenanceChargeServiceImpl")
public class MaintenanceChargeServiceImpl extends BaseServiceImpl<MaintenanceCharge,Long> implements MaintenanceChargeService {

      @Resource(name="maintenanceChargeDaoImpl")
      public void setBaseDao(MaintenanceChargeDao maintenanceChargeDao) {
         super.setBaseDao(maintenanceChargeDao);
  }
}