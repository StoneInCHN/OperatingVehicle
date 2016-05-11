package com.ov.dao; 
import java.util.Date;
import java.util.List;

import com.ov.entity.ReportUserRegStatistics;
import com.ov.framework.dao.BaseDao;

public interface ReportUserRegStatisticsDao extends  BaseDao<ReportUserRegStatistics,Long>{
  List<ReportUserRegStatistics> findList(Date startDate,Date endDate);
}