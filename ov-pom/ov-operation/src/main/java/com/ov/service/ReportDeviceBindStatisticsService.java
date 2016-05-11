package com.ov.service; 

import java.util.Date;
import java.util.List;

import com.ov.entity.ReportDeviceBindStatistics;
import com.ov.framework.service.BaseService;

public interface ReportDeviceBindStatisticsService extends BaseService<ReportDeviceBindStatistics,Long>{
  List<ReportDeviceBindStatistics> findList(Date startDate,Date endDate);
}