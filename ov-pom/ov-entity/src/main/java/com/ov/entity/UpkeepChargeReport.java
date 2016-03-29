package com.ov.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;

/**
 * 车辆维修费报表
 * @author luzhang
 */

@Entity
@Table(name = "ov_upkeep_charge_report")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_upkeep_charge_report_sequence")
public class UpkeepChargeReport extends BaseEntity{

  private static final long serialVersionUID = 3157148173688026879L;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 车辆
   */
  private Vehicle vehicle;
  
  /**
   * 维修费用
   */
  private BigDecimal upkeepAmount;
  
  /**
   * 报告统计时间
   */
  private Date upkeepChargeStatisticsDate;

  
  @Index(name="upkeep_charge_report_tenantid")
  public Long getTenantID() {
     return tenantID;
  }

  public void setTenantID(Long tenantID) {
     this.tenantID = tenantID;
  }
  
  @JsonProperty
  @ManyToOne(fetch=FetchType.EAGER)
  public Vehicle getVehicle() {
    return vehicle;
  }
  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
  
  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getUpkeepAmount() {
    return upkeepAmount;
  }

  public void setUpkeepAmount(BigDecimal upkeepAmount) {
    this.upkeepAmount = upkeepAmount;
  }
  
  @JsonProperty
  @Temporal(TemporalType.DATE)
  public Date getUpkeepChargeStatisticsDate() {
	return upkeepChargeStatisticsDate;
  }

  public void setUpkeepChargeStatisticsDate(Date upkeepChargeStatisticsDate) {
	this.upkeepChargeStatisticsDate = upkeepChargeStatisticsDate;
  }
  
}
