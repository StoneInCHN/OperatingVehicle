package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.VehicleBrandDetail;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.VehicleBrandDetailDao;
@Repository("vehicleBrandDetailDaoImpl")
public class VehicleBrandDetailDaoImpl extends  BaseDaoImpl<VehicleBrandDetail,Long> implements VehicleBrandDetailDao {

}