package com.ov.service; 

import java.util.Date;

import com.ov.entity.Vehicle;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.BaseService;
import com.ov.json.response.VehicleDailyReport;

public interface VehicleService extends BaseService<Vehicle,Long>{

  Page<Vehicle> searchPageByFilter(String vehiclePlateSearch,String motorcadeSearch,String vehicleFullBrandSearch, Pageable pageable);

  Page<Vehicle> listUnBuindVehicle (String vehiclePlateSearch,
      String motorcadeSearch, String vehicleFullBrandSearch, Pageable pageable);
  
  VehicleDailyReport callVehicleDailyData (Date date, Long vehicleId);

}