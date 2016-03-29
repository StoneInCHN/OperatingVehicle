package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.OilCharge;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.OilChargeDao;
@Repository("oilChargeDaoImpl")
public class OilChargeDaoImpl extends  BaseDaoImpl<OilCharge,Long> implements OilChargeDao {

}