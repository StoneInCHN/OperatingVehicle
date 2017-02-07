package com.ov.json.response;

import java.util.Date;

public class VehicleDailyReport {

  /**
   * 每天的行驶公里数
   */
  private Float dailyMileage;

  /**
   * 平均油耗
   */
  private Float averageFuelConsumption;

  /**
   * 当日油耗s
   */
  private Float fuelConsumption;

  /**
   * 当日油费
   */
  private Double cost;

  /**
   * 平均速度
   */
  private Float averageSpeed;

  /**
   * 急刹车次数
   */
  private Integer emergencybrakecount;

  /**
   * 急转弯次数
   */
  private Integer suddenturncount;


  /**
   * 急加速次数
   */
  private Integer rapidlyspeedupcount;

  /**
   * 创建时间
   */
  private Date createdate;
  
  /**
   * 日期顺序
   */
  private Integer day;
  
  private String deviceId;

  public Float getDailyMileage() {
    return dailyMileage;
  }


  public void setDailyMileage(Float dailyMileage) {
    this.dailyMileage = dailyMileage;
  }


  public Float getAverageFuelConsumption() {
    return averageFuelConsumption;
  }


  public void setAverageFuelConsumption(Float averageFuelConsumption) {
    this.averageFuelConsumption = averageFuelConsumption;
  }


  public Float getFuelConsumption() {
    return fuelConsumption;
  }


  public void setFuelConsumption(Float fuelConsumption) {
    this.fuelConsumption = fuelConsumption;
  }


  public Double getCost() {
    return cost;
  }


  public void setCost(Double cost) {
    this.cost = cost;
  }


  public Float getAverageSpeed() {
    return averageSpeed;
  }


  public void setAverageSpeed(Float averageSpeed) {
    this.averageSpeed = averageSpeed;
  }


  public Integer getEmergencybrakecount() {
    return emergencybrakecount;
  }


  public void setEmergencybrakecount(Integer emergencybrakecount) {
    this.emergencybrakecount = emergencybrakecount;
  }


  public Integer getSuddenturncount() {
    return suddenturncount;
  }


  public void setSuddenturncount(Integer suddenturncount) {
    this.suddenturncount = suddenturncount;
  }


  public Integer getRapidlyspeedupcount() {
    return rapidlyspeedupcount;
  }


  public void setRapidlyspeedupcount(Integer rapidlyspeedupcount) {
    this.rapidlyspeedupcount = rapidlyspeedupcount;
  }


  public Date getCreatedate() {
    return createdate;
  }


  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }


  public Integer getDay() {
    return day;
  }


  public void setDay(Integer day) {
    this.day = day;
  }


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }
  
}
