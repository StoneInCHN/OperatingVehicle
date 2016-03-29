package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.service.UpkeepChargeReportService;
import com.ov.dao.UpkeepChargeReportDao;
import com.ov.entity.UpkeepChargeReport;
import com.ov.framework.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 车辆维修费报表
 * @author luzhang
 *
 */
@Service ("upkeepChargeReportServiceImpl")
public class UpkeepChargeReportServiceImpl extends BaseServiceImpl<UpkeepChargeReport, Long> 
    implements UpkeepChargeReportService{

  @Resource (name = "upkeepChargeReportDaoImpl")
  private void setBaseDao (UpkeepChargeReportDao upkeepChargeReportDao)
  {
    super.setBaseDao (upkeepChargeReportDao);
  }
}
