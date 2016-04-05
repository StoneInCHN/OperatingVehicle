package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.OilTotalReport;
import com.ov.dao.OilTotalReportDao;
import com.ov.service.OilTotalReportService;
import com.ov.framework.service.impl.BaseServiceImpl;
/**
 * ServiceImpl - 加油报表(总)
 * @author luzhang
 *
 */
@Service("oilTotalReportServiceImpl")
public class OilTotalReportServiceImpl extends BaseServiceImpl<OilTotalReport,Long> implements OilTotalReportService {

      @Resource(name="oilTotalReportDaoImpl")
      public void setBaseDao(OilTotalReportDao oilTotalReportDao) {
         super.setBaseDao(oilTotalReportDao);
  }
}