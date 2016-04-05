package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.UpkeepTotalReportDao;
import com.ov.entity.UpkeepTotalReport;
import com.ov.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 车辆维修费报表(总)
 * @author luzhang
 *
 */
@Repository("upkeepTotalReportDaoImpl")
public class UpkeepTotalReportDaoImpl extends BaseDaoImpl<UpkeepTotalReport, Long> implements UpkeepTotalReportDao {

}
