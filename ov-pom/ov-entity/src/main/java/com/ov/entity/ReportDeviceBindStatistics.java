package com.ov.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ov.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 设备绑定统计
 * 
 */
@Entity
@Table(name = "ov_report_device_bind_statistics")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "ov_report_device_bind_statistics_sequence")
public class ReportDeviceBindStatistics extends BaseEntity {

  private static final long serialVersionUID = 1L;



  /**
   * 设备绑定数
   */
  private Integer bindDeviceNum;

  /**
   * 统计日期
   */
  private Date statisticsDate;


  @JsonProperty
  public Integer getBindDeviceNum() {
    return bindDeviceNum;
  }

  public void setBindDeviceNum(Integer bindDeviceNum) {
    this.bindDeviceNum = bindDeviceNum;
  }

  @JsonProperty
  @Temporal(TemporalType.DATE)
  public Date getStatisticsDate() {
    return statisticsDate;
  }

  public void setStatisticsDate(Date statisticsDate) {
    this.statisticsDate = statisticsDate;
  }

}
