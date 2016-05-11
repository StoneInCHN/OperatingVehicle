package com.ov.dao; 
import java.util.Date;
import java.util.List;

import com.ov.entity.ReportDeviceBindStatistics;
import com.ov.entity.ReportUserRegStatistics;
import com.ov.framework.dao.BaseDao;

public interface ReportDeviceBindStatisticsDao extends  BaseDao<ReportDeviceBindStatistics,Long>{
  List<ReportDeviceBindStatistics> findList(Date startDate,Date endDate);
}