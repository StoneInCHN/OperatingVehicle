package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.OilCharge;
import com.ov.dao.OilChargeDao;
import com.ov.service.OilChargeService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("oilChargeServiceImpl")
public class OilChargeServiceImpl extends BaseServiceImpl<OilCharge,Long> implements OilChargeService {

      @Resource(name="oilChargeDaoImpl")
      public void setBaseDao(OilChargeDao oilChargeDao) {
         super.setBaseDao(oilChargeDao);
  }
}