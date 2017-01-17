package com.ov.service; 

import com.ov.entity.DeviceInfo;
import com.ov.framework.service.BaseService;

public interface DeviceInfoService extends BaseService<DeviceInfo,Long>{
  /**
   * 根据设备号查找
   * @param deviceNo
   * @return
   */
  public DeviceInfo findByDeviceNo(String deviceNo);

}