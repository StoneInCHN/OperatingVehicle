package com.ov.json.response;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 返回给租户的车辆实时状态数据,用户车辆查询页面显示
 * 
 * @author sujinxuan
 *
 */
public class VehicleTrack {

  /**
   * 车牌号
   */
  private String plate;

  /**
   * 起始时间
   */
  private Date from;

  /**
   * 结束时间
   */
  private Date to;

  /**
   * 行驶里程
   */
  private Float mileage;
  /**
   * 行驶时间
   */
  private Integer runTime;

  /**
   * 轨迹坐标集合
   */
  private List<Map<String, Object>> tracks;

  /**
   * 起始地址
   */
  private String startAddr;

  /**
   * 结束地址
   */
  private String endAddr;


  public String getStartAddr() {
    return startAddr;
  }

  public void setStartAddr(String startAddr) {
    this.startAddr = startAddr;
  }

  public String getEndAddr() {
    return endAddr;
  }

  public void setEndAddr(String endAddr) {
    this.endAddr = endAddr;
  }

  public Date getFrom() {
    return from;
  }

  public void setFrom(Date from) {
    this.from = from;
  }

  public Date getTo() {
    return to;
  }

  public void setTo(Date to) {
    this.to = to;
  }

  public Float getMileage() {
    return mileage;
  }

  public void setMileage(Float mileage) {
    this.mileage = mileage;
  }

  public Integer getRunTime() {
    return runTime;
  }

  public void setRunTime(Integer runTime) {
    this.runTime = runTime;
  }

  public List<Map<String, Object>> getTracks() {
    return tracks;
  }

  public void setTracks(List<Map<String, Object>> tracks) {
    this.tracks = tracks;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

}
