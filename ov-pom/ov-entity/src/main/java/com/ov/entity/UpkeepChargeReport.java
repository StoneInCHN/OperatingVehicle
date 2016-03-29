package com.ov.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;
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
   * 维修费用
   */
  private BigDecimal upkeepAmount;
  
  /**
   * 报告统计时间
   */
  private Date upkeepChargeStatiticsDate;

  
  @Index(name="upkeep_charge_report_tenantid")
  public Long getTenantID() {
     return tenantID;
  }

  public void setTenantID(Long tenantID) {
     this.tenantID = tenantID;
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
  public Date getUpkeepChargeStatiticsDate() {
    return upkeepChargeStatiticsDate;
  }

  public void setUpkeepChargeStatiticsDate(Date upkeepChargeStatiticsDate) {
    this.upkeepChargeStatiticsDate = upkeepChargeStatiticsDate;
  }

}
