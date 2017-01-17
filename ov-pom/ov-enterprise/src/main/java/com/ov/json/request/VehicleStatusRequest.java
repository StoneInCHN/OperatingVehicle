package com.ov.json.request;

/**
 * 车辆状态
 *
 */
public class VehicleStatusRequest {
  /**
   * 设备编号(不能为null)
   */
  private String deviceNo;

  /**
   * 警告信息
   */
  private String wainingInfo;

  /**
   * 故障码
   */
  private String faultCode;

  /**
   * 绑定的设备是否在线("0":离线;"1":在线.不能为null)
   */
  private String isOnline;

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getWainingInfo() {
    return wainingInfo;
  }

  public void setWainingInfo(String wainingInfo) {
    this.wainingInfo = wainingInfo;
  }

  public String getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  public String getIsOnline() {
    return isOnline;
  }

  public void setIsOnline(String isOnline) {
    this.isOnline = isOnline;
  }



}
