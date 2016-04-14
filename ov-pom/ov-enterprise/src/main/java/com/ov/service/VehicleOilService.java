package com.ov.service; 

import java.math.BigDecimal;

import com.ov.entity.VehicleOil;
import com.ov.entity.commonenum.CommonEnum.OilType;
import com.ov.framework.service.BaseService;
import com.ov.json.response.OilBean;

public interface VehicleOilService extends BaseService<VehicleOil,Long>{

  void updateOilInfo(OilBean oil);
  
  BigDecimal getOidPrice (OilType oilType, String shortPlate);
  
}