package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.service.MaintenanceTotalReportService;
import com.ov.dao.MaintenanceTotalReportDao;
import com.ov.entity.MaintenanceTotalReport;
import com.ov.framework.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 车辆维修费报表(总)
 * @author luzhang
 *
 */
@Service ("MaintenanceTotalReportServiceImpl")
public class MaintenanceTotalReportServiceImpl extends BaseServiceImpl<MaintenanceTotalReport, Long> 
    implements MaintenanceTotalReportService{

  @Resource (name = "MaintenanceTotalReportDaoImpl")
  private void setBaseDao (MaintenanceTotalReportDao MaintenanceTotalReportDao)
  {
    super.setBaseDao (MaintenanceTotalReportDao);
  }
}
