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
 * 手机app注册用户统计
 * 
 */
@Entity
@Table(name = "ov_report_user_reg_statistics")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "ov_report_user_reg_statistics_sequence")
public class ReportUserRegStatistics extends BaseEntity {

  private static final long serialVersionUID = 1L;



  /**
   * 注册用户数
   */
  private Integer regNum;

  /**
   * 统计日期
   */
  private Date statisticsDate;


  @JsonProperty
  public Integer getRegNum() {
    return regNum;
  }

  public void setRegNum(Integer regNum) {
    this.regNum = regNum;
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
