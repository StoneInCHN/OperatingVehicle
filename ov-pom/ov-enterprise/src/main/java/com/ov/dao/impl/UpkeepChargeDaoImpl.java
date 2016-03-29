package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.UpkeepCharge;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.UpkeepChargeDao;
@Repository("upkeepChargeDaoImpl")
public class UpkeepChargeDaoImpl extends  BaseDaoImpl<UpkeepCharge,Long> implements UpkeepChargeDao {

}