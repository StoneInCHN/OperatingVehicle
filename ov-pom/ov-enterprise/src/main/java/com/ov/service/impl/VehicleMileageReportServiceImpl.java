package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.VehicleMileageReport;
import com.ov.dao.VehicleMileageReportDao;
import com.ov.service.VehicleMileageReportService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("vehicleMileageReportServiceImpl")
public class VehicleMileageReportServiceImpl extends BaseServiceImpl<VehicleMileageReport,Long> implements VehicleMileageReportService {

      @Resource(name="vehicleMileageReportDaoImpl")
      public void setBaseDao(VehicleMileageReportDao vehicleMileageReportDao) {
         super.setBaseDao(vehicleMileageReportDao);
  }
}