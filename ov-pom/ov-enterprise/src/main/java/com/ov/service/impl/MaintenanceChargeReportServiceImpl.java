package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.service.MaintenanceChargeReportService;
import com.ov.dao.MaintenanceChargeReportDao;
import com.ov.entity.MaintenanceChargeReport;
import com.ov.framework.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 车辆维修费报表
 * @author luzhang
 *
 */
@Service ("MaintenanceChargeReportServiceImpl")
public class MaintenanceChargeReportServiceImpl extends BaseServiceImpl<MaintenanceChargeReport, Long> 
    implements MaintenanceChargeReportService{

  @Resource (name = "MaintenanceChargeReportDaoImpl")
  private void setBaseDao (MaintenanceChargeReportDao MaintenanceChargeReportDao)
  {
    super.setBaseDao (MaintenanceChargeReportDao);
  }
}
