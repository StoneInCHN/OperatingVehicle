package com.ov.lucene;

import org.hibernate.search.bridge.StringBridge;

import com.ov.entity.DeviceInfo;

public class VehicleDeviceBridgeImpl implements StringBridge
{

  @Override
  public String objectToString (Object object)
  {
    if (object == null)
    {
      return "null";
    }else {
      return ((DeviceInfo)object).getDeviceNo ();
    }
    
  }


}
