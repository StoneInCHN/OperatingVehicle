package com.ov.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.Vehicle;
import com.ov.entity.base.BaseEntity;

@Entity
@Table(name = "ov_vehicle_offline_log")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_vehicle_offline_log_sequence")
public class VehicleOffLineLog extends BaseEntity {
  
  private static final long serialVersionUID = 1379438127423990703L;
  /**
   * 车辆
   */
  private Vehicle vehicle;
  /**
   * 绑定设备
   */
  private String deviceNo;

  /**
   * 离线时间
   */
  private Date offlineDate;
  
  @ManyToOne(fetch=FetchType.LAZY)
  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
  @JsonProperty
  public Date getOfflineDate() {
    return offlineDate;
  }

  public void setOfflineDate(Date offlineDate) {
    this.offlineDate = offlineDate;
  }
  @JsonProperty
  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }
  
  
}
