package com.ov.dao.impl; 

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ov.dao.VehicleDao;
import com.ov.entity.Vehicle;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
@Repository("vehicleDaoImpl")
public class VehicleDaoImpl extends  BaseDaoImpl<Vehicle,Long> implements VehicleDao {

  @Override
  public Page<Vehicle> listUnBuindVehicle (String vehiclePlateSearch,
      String motorcadeSearch, String vehicleFullBrandSearch, Pageable pageable)
  {
    
    Map<String, String> paramMap  = new HashMap<String, String> ();
    
    String jpql =
        "select vehicle from DeviceInfo deviceInfo right join  deviceInfo.vehicle vehicle"
        + " where deviceInfo.id is null";
    if (vehiclePlateSearch != null)
    {
      jpql= jpql+ " and vehicle.plate like :plate";
      paramMap.put ("plate", "%"+vehiclePlateSearch+"%");
    }

    return findPageCustomized (pageable, jpql, paramMap);
        
  }

}