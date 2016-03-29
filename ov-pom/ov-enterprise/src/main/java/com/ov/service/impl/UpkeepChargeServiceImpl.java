package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.UpkeepCharge;
import com.ov.dao.UpkeepChargeDao;
import com.ov.service.UpkeepChargeService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("upkeepChargeServiceImpl")
public class UpkeepChargeServiceImpl extends BaseServiceImpl<UpkeepCharge,Long> implements UpkeepChargeService {

      @Resource(name="upkeepChargeDaoImpl")
      public void setBaseDao(UpkeepChargeDao upkeepChargeDao) {
         super.setBaseDao(upkeepChargeDao);
  }
}