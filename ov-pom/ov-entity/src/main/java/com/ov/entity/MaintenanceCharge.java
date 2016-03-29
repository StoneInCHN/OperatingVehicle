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
 * 车辆保养费
 * @author luzhang
 *
 */
@Entity
@Table(name = "ov_maintenance_charge")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_maintenance_charge_sequence")
@Indexed(index="maintenanceCharge")
public class MaintenanceCharge extends BaseEntity{

  private static final long serialVersionUID = 7164407141246267487L;
  
  
  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 车辆
   */
  private Vehicle vehicle;
  
  /**
   * 保养时间
   */
  private Date maintenanceDate;
  /**
   * 保养费用
   */
  private BigDecimal maintenanceAmount;
  /**
   * 保养时公里数
   */
  private Long maintenanceMileage;
  /**
   * 保养公司
   */
  private String maintenanceCompany;
  
  /**
   * 备注
   */
  private String remark;
  
  @Index(name="maintenance_charge_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID() {
     return tenantID;
  }
  public void setTenantID (Long tenantID){
    this.tenantID = tenantID;
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
  public Date getMaintenanceDate() {
    return maintenanceDate;
  }
  public void setMaintenanceDate(Date maintenanceDate) {
    this.maintenanceDate = maintenanceDate;
  }
  
  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getMaintenanceAmount() {
    return maintenanceAmount;
  }
  public void setMaintenanceAmount(BigDecimal maintenanceAmount) {
    this.maintenanceAmount = maintenanceAmount;
  }
  
  @JsonProperty
  public Long getMaintenanceMileage() {
    return maintenanceMileage;
  }
  public void setMaintenanceMileage(Long maintenanceMileage) {
    this.maintenanceMileage = maintenanceMileage;
  }
  
  @JsonProperty
  @Column(length = 50)
  public String getMaintenanceCompany() {
    return maintenanceCompany;
  }
  public void setMaintenanceCompany(String maintenanceCompany) {
    this.maintenanceCompany = maintenanceCompany;
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
