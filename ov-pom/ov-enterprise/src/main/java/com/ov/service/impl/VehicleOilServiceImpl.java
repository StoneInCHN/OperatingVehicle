package com.ov.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.VehicleOilDao;
import com.ov.entity.VehicleOil;
import com.ov.entity.commonenum.CommonEnum.OilType;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.json.response.OilBean;
import com.ov.service.VehicleOilService;
import com.ov.utils.CommonUtils;

@Service("vehicleOilServiceImpl")
public class VehicleOilServiceImpl extends BaseServiceImpl<VehicleOil, Long> implements
    VehicleOilService {

  @Resource(name = "vehicleOilDaoImpl")
  private VehicleOilDao vehicleOilDao;
  
  @Resource(name = "vehicleOilDaoImpl")
  public void setBaseDao(VehicleOilDao vehicleOilDao) {
    super.setBaseDao(vehicleOilDao);
  }

  @Override
  public void updateOilInfo(OilBean oil) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter();
    filter.setProperty("province");
    filter.setOperator(Operator.eq);
    filter.setValue(oil.getProv());
    filters.add(filter);
    List<VehicleOil> vehicleOils = vehicleOilDao.findList(null, null, filters, null);
    
    if (vehicleOils !=null && vehicleOils.size() == 0) {
      //p0
      VehicleOil p0 = new VehicleOil();
      p0.setOilType(OilType.P0);
      p0.setPrice(CommonUtils.getOilPrice(oil.getP0(), oil.getProv()));
      p0.setProvince(oil.getProv());
      p0.setShortPlate(CommonUtils.getShortPlate(oil.getProv()));
      vehicleOilDao.persist(p0);
      
      //p90
      VehicleOil p90 = new VehicleOil();
      p90.setOilType(OilType.P90);
      p90.setPrice(CommonUtils.getOilPrice(oil.getP90(), oil.getProv()));
      p90.setProvince(oil.getProv());
      p90.setShortPlate(CommonUtils.getShortPlate(oil.getProv()));
      vehicleOilDao.persist(p90);
      
      //p93
      VehicleOil p93 = new VehicleOil();
      p93.setOilType(OilType.P93);
      p93.setPrice(CommonUtils.getOilPrice(oil.getP93(), oil.getProv()));
      p93.setProvince(oil.getProv());
      p93.setShortPlate(CommonUtils.getShortPlate(oil.getProv()));
      vehicleOilDao.persist(p93);
      
      //p97
      VehicleOil p97 = new VehicleOil();
      p97.setOilType(OilType.P97);
      p97.setPrice(CommonUtils.getOilPrice(oil.getP97(), oil.getProv()));
      p97.setProvince(oil.getProv());
      p97.setShortPlate(CommonUtils.getShortPlate(oil.getProv()));
      vehicleOilDao.persist(p97);
    }else if(vehicleOils !=null && vehicleOils.size() > 0){
      for (VehicleOil vehicleOil : vehicleOils) {
        if (vehicleOil.getOilType().equals(OilType.P0)) {
          vehicleOil.setPrice(CommonUtils.getOilPrice(oil.getP0(), oil.getProv()));
        }else if(vehicleOil.getOilType().equals(OilType.P90)) {
          vehicleOil.setPrice(CommonUtils.getOilPrice(oil.getP90(), oil.getProv()));
        }else if(vehicleOil.getOilType().equals(OilType.P93)) {
          vehicleOil.setPrice(CommonUtils.getOilPrice(oil.getP93(), oil.getProv()));
        }else if(vehicleOil.getOilType().equals(OilType.P97)) {
          vehicleOil.setPrice(CommonUtils.getOilPrice(oil.getP97(), oil.getProv()));
        }
        vehicleOil.setModifyDate(new Date());
        vehicleOilDao.merge(vehicleOil);
      }
    }

  }

	@Override
	public BigDecimal getOidPrice(OilType oilType, String shortPlate) {
		List<Filter> filters = new ArrayList<Filter>();
        Filter oilTypeFilter = new Filter ("oilType", Operator.eq, oilType);
        Filter plateFilter = new Filter ("shortPlate", Operator.eq, shortPlate);
        
        filters.add (oilTypeFilter);
        filters.add (plateFilter);
        
        List<VehicleOil> vehicleOilList =vehicleOilDao.findList (null,null, filters, null);
        if (vehicleOilList != null && vehicleOilList.size () ==1)
        {
          return vehicleOilList.get (0).getPrice ();
        }else {
          return new BigDecimal (0);
        }
	}
}
