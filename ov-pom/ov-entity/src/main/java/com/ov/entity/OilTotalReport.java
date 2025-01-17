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
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.lucene.DateBridgeImpl;

/**
 * 车辆加油信息报表(总)
 * 
 * @author luzhang
 *
 */
@Entity
@Table(name = "ov_oil_total_report")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_oil_total_report_sequence")
public class OilTotalReport extends BaseEntity {

  private static final long serialVersionUID = -6400399057900315583L;
  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 油费
   */
  private BigDecimal oilFinalAmount;
  /**
   * 加油次数
   */
  private int oilNumber;
  /**
   * 加油量L
   */
  private BigDecimal oilCount;
  /**
   * 报告统计时间
   */
  private Date oilChargeReportStatisticsDate;


  @Index(name = "oil_total_report_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getOilFinalAmount() {
    return oilFinalAmount;
  }

  public void setOilFinalAmount(BigDecimal oilFinalAmount) {
    this.oilFinalAmount = oilFinalAmount;
  }

  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getOilCount() {
    return oilCount;
  }

  public void setOilCount(BigDecimal oilCount) {
    this.oilCount = oilCount;
  }

  @JsonProperty
  @Temporal(TemporalType.DATE)
  @Index(name="oiltotalreport_oilchargereportstatisticsdate")
  public Date getOilChargeReportStatisticsDate() {
    return oilChargeReportStatisticsDate;
  }

  public void setOilChargeReportStatisticsDate(Date oilChargeReportStatisticsDate) {
    this.oilChargeReportStatisticsDate = oilChargeReportStatisticsDate;
  }

  @JsonProperty
  public int getOilNumber() {
    return oilNumber;
  }

  public void setOilNumber(int oilNumber) {
    this.oilNumber = oilNumber;
  }

}
