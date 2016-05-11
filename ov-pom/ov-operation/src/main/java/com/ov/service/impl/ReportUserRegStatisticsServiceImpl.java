package com.ov.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.ReportUserRegStatisticsDao;
import com.ov.entity.ReportUserRegStatistics;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.ReportUserRegStatisticsService;
import com.ov.utils.DateUtils;

@Service("reportUserRegStatisticsServiceImpl")
public class ReportUserRegStatisticsServiceImpl extends
    BaseServiceImpl<ReportUserRegStatistics, Long> implements ReportUserRegStatisticsService {

  @Resource(name = "reportUserRegStatisticsDaoImpl")
  private ReportUserRegStatisticsDao reportUserRegStatisticsDao;
  
  @Resource(name = "reportUserRegStatisticsDaoImpl")
  public void setBaseDao(ReportUserRegStatisticsDao reportUserRegStatisticsDao) {
    super.setBaseDao(reportUserRegStatisticsDao);
  }

  @Override
  public List<ReportUserRegStatistics> findList(Date startDate, Date endDate) {
    
    return reportUserRegStatisticsDao.findList(startDate, endDate);
  }
}
