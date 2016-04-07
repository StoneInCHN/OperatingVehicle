package com.ov.dao.impl; 

import org.springframework.stereotype.Repository;

import com.ov.dao.DeviceInfoDao;
import com.ov.entity.DeviceInfo;
import com.ov.framework.dao.impl.BaseDaoImpl;
@Repository("deviceInfoDaoImpl")
public class DeviceInfoDaoImpl extends  BaseDaoImpl<DeviceInfo,Long> implements DeviceInfoDao {

}