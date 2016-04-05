package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.MaintenanceTotalReportDao;
import com.ov.entity.MaintenanceTotalReport;
import com.ov.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 车辆保养费报表(总)
 * @author luzhang
 *
 */
@Repository("MaintenanceTotalReportDaoImpl")
public class MaintenanceTotalReportDaoImpl extends BaseDaoImpl<MaintenanceTotalReport, Long> implements MaintenanceTotalReportDao {

}
