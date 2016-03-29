package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.UpkeepChargeReportDao;
import com.ov.entity.UpkeepChargeReport;
import com.ov.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 车辆维修费报表
 * @author luzhang
 *
 */
@Repository("upkeepChargeReportDaoImpl")
public class UpkeepChargeReportDaoImpl extends BaseDaoImpl<UpkeepChargeReport, Long> implements UpkeepChargeReportDao {

}
