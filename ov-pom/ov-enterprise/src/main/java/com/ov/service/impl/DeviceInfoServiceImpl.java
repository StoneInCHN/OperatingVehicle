package com.ov.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.DeviceInfoDao;
import com.ov.entity.DeviceInfo;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.DeviceInfoService;

@Service("deviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo,Long> implements DeviceInfoService {

      @Resource(name="deviceInfoDaoImpl")
      public void setBaseDao(DeviceInfoDao deviceInfoDao) {
         super.setBaseDao(deviceInfoDao);
  }
}