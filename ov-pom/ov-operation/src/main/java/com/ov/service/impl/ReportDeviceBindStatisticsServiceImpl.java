package com.ov.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.ReportDeviceBindStatisticsDao;
import com.ov.entity.ReportDeviceBindStatistics;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.ReportDeviceBindStatisticsService;

@Service("reportDeviceBindStatisticsServiceImpl")
public class ReportDeviceBindStatisticsServiceImpl extends
    BaseServiceImpl<ReportDeviceBindStatistics, Long> implements ReportDeviceBindStatisticsService {

  @Resource(name = "reportDeviceBindStatisticsDaoImpl")
  private ReportDeviceBindStatisticsDao reportDeviceBindStatisticsDao;

  @Resource(name = "reportDeviceBindStatisticsDaoImpl")
  public void setBaseDao(ReportDeviceBindStatisticsDao reportDeviceBindStatisticsDao) {
    super.setBaseDao(reportDeviceBindStatisticsDao);
  }

  @Override
  public List<ReportDeviceBindStatistics> findList(Date startDate, Date endDate) {
    return reportDeviceBindStatisticsDao.findList(startDate, endDate);
  }
}
