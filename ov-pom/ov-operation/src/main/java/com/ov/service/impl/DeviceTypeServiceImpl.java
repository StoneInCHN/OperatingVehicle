package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.DeviceType;
import com.ov.dao.DeviceTypeDao;
import com.ov.service.DeviceTypeService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("deviceTypeServiceImpl")
public class DeviceTypeServiceImpl extends BaseServiceImpl<DeviceType,Long> implements DeviceTypeService {

      @Resource(name="deviceTypeDaoImpl")
      public void setBaseDao(DeviceTypeDao deviceTypeDao) {
         super.setBaseDao(deviceTypeDao);
  }
}