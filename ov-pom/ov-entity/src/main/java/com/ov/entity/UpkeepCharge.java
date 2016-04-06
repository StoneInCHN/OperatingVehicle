package com.ov.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.lucene.DateBridgeImpl;
/**
 * 车辆维修费
 * @author luzhang
 *
 */
@Entity
@Table(name = "ov_upkeep_charge")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_upkeep_charge_sequence")
@Indexed(index="upkeepCharge")
public class UpkeepCharge extends BaseEntity{

  private static final long serialVersionUID = 3157148173688026879L;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 车辆 ID
   */
  private Long vehicleID;
  
  /**
   * 车辆
   */
  private Vehicle vehicle;
  
  /**
   * 维修时间
   */
  private Date upkeepDate;
  /**
   * 维修费用
   */
  private BigDecimal upkeepAmount;

  /**
   * 维修公司
   */
  private String upkeepCompany;
  
  /**
   * 备注
   */
  private String remark;
  
  
  @Index(name="upkeep_charge_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID() {
     return tenantID;
  }

  public void setTenantID(Long tenantID) {
     this.tenantID = tenantID;
  }
  @org.hibernate.annotations.Index(name="upkeep_charge_vehicleid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getVehicleID() {
    return vehicleID;
  }

  public void setVehicleID(Long vehicleID) {
    this.vehicleID = vehicleID;
  }
  @JsonProperty
  @ManyToOne(fetch=FetchType.EAGER)
  @IndexedEmbedded
  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
  
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getUpkeepDate() {
    return upkeepDate;
  }

  public void setUpkeepDate(Date upkeepDate) {
    this.upkeepDate = upkeepDate;
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
  @Column(length = 50)
  public String getUpkeepCompany() {
    return upkeepCompany;
  }

  public void setUpkeepCompany(String upkeepCompany) {
    this.upkeepCompany = upkeepCompany;
  }

  @JsonProperty
  @Column(length = 150)
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
}
