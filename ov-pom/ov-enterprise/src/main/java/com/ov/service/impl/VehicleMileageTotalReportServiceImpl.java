package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.VehicleMileageTotalReport;
import com.ov.dao.VehicleMileageTotalReportDao;
import com.ov.service.VehicleMileageTotalReportService;
import com.ov.framework.service.impl.BaseServiceImpl;
/**
 * ServiceImpl - 车辆行程报表(总)
 * @author luzhang
 *
 */
@Service("vehicleMileageTotalReportServiceImpl")
public class VehicleMileageTotalReportServiceImpl extends BaseServiceImpl<VehicleMileageTotalReport,Long> implements VehicleMileageTotalReportService {

      @Resource(name="vehicleMileageTotalReportDaoImpl")
      public void setBaseDao(VehicleMileageTotalReportDao vehicleMileageTotalReportDao) {
         super.setBaseDao(vehicleMileageTotalReportDao);
  }
}