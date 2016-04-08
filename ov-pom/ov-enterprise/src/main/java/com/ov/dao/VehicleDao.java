package com.ov.dao; 
import com.ov.entity.Vehicle;
import com.ov.framework.dao.BaseDao;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;

public interface VehicleDao extends  BaseDao<Vehicle,Long>{

  Page<Vehicle> listUnBuindVehicle (String vehiclePlateSearch,
      String motorcadeSearch, String vehicleFullBrandSearch, Pageable pageable);

}