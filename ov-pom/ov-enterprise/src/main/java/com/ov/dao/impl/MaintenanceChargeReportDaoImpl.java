package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.MaintenanceChargeReportDao;
import com.ov.entity.MaintenanceChargeReport;
import com.ov.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 车辆保养费报表
 * @author luzhang
 *
 */
@Repository("MaintenanceChargeReportDaoImpl")
public class MaintenanceChargeReportDaoImpl extends BaseDaoImpl<MaintenanceChargeReport, Long> implements MaintenanceChargeReportDao {

}
