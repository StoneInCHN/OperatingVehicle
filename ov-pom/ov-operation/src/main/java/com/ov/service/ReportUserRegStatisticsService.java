package com.ov.service; 

import java.util.Date;
import java.util.List;

import com.ov.entity.ReportUserRegStatistics;
import com.ov.framework.service.BaseService;

public interface ReportUserRegStatisticsService extends BaseService<ReportUserRegStatistics,Long>{
  
  List<ReportUserRegStatistics> findList(Date startDate,Date endDate);
  
}