package com.ov.dao;

import com.ov.entity.DeviceInfo;
import com.ov.framework.dao.BaseDao;

public interface DeviceInfoDao extends BaseDao<DeviceInfo, Long> {

  /**
   * 根据设备号查找
   * 
   * @param deviceNo
   * @return
   */
  public DeviceInfo findByDeviceNo(String deviceNo);

}
