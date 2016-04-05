package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.VehicleMileageTotalReport;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.VehicleMileageTotalReportDao;
/**
 * DaoImpl - 车辆行程报表(总)
 * @author luzhang
 *
 */
@Repository("vehicleMileageTotalReportDaoImpl")
public class VehicleMileageTotalReportDaoImpl extends  BaseDaoImpl<VehicleMileageTotalReport,Long> implements VehicleMileageTotalReportDao {

}