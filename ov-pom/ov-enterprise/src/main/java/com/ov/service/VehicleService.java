package com.ov.service; 

import com.ov.entity.Vehicle;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.BaseService;

public interface VehicleService extends BaseService<Vehicle,Long>{

  Page<Vehicle> searchPageByFilter(String vehiclePlateSearch,String motorcadeSearch,String vehicleFullBrandSearch, Pageable pageable);

  Page<Vehicle> listUnBuindVehicle (String vehiclePlateSearch,
      String motorcadeSearch, String vehicleFullBrandSearch, Pageable pageable);

}