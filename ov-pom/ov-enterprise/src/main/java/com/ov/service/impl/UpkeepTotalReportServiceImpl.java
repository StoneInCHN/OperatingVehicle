package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.service.UpkeepTotalReportService;
import com.ov.dao.UpkeepTotalReportDao;
import com.ov.entity.UpkeepTotalReport;
import com.ov.framework.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 车辆维修费报表(总)
 * @author luzhang
 *
 */
@Service ("upkeepTotalReportServiceImpl")
public class UpkeepTotalReportServiceImpl extends BaseServiceImpl<UpkeepTotalReport, Long> 
    implements UpkeepTotalReportService{

  @Resource (name = "upkeepTotalReportDaoImpl")
  private void setBaseDao (UpkeepTotalReportDao upkeepTotalReportDao)
  {
    super.setBaseDao (upkeepTotalReportDao);
  }
}
