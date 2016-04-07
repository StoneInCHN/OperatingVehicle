package com.ov.service; 

import java.util.List;
import java.util.Map;

import com.ov.entity.DeviceType;
import com.ov.framework.service.BaseService;

public interface DeviceTypeService extends BaseService<DeviceType,Long>{

  List<Map<String, Object>> findAllDeviceType ();

}