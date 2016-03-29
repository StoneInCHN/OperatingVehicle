package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.OilChargeReport;
import com.ov.dao.OilChargeReportDao;
import com.ov.service.OilChargeReportService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("oilChargeReportServiceImpl")
public class OilChargeReportServiceImpl extends BaseServiceImpl<OilChargeReport,Long> implements OilChargeReportService {

      @Resource(name="oilChargeReportDaoImpl")
      public void setBaseDao(OilChargeReportDao oilChargeReportDao) {
         super.setBaseDao(oilChargeReportDao);
  }
}