package com.ov.dao.impl; 

import org.springframework.stereotype.Repository;

import com.ov.dao.DeviceTypeDao;
import com.ov.entity.DeviceType;
import com.ov.framework.dao.impl.BaseDaoImpl;
@Repository("deviceTypeDaoImpl")
public class DeviceTypeDaoImpl extends  BaseDaoImpl<DeviceType,Long> implements DeviceTypeDao {

}