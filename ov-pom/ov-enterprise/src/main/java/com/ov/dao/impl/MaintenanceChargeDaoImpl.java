package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.MaintenanceCharge;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.MaintenanceChargeDao;
@Repository("maintenanceChargeDaoImpl")
public class MaintenanceChargeDaoImpl extends  BaseDaoImpl<MaintenanceCharge,Long> implements MaintenanceChargeDao {

}