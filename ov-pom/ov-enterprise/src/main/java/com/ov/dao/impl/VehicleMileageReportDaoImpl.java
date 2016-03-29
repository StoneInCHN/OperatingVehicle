package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.VehicleMileageReport;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.VehicleMileageReportDao;
@Repository("vehicleMileageReportDaoImpl")
public class VehicleMileageReportDaoImpl extends  BaseDaoImpl<VehicleMileageReport,Long> implements VehicleMileageReportDao {

}