package com.ov.entity;

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
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;

/**
 * 每日车辆里程报表
 * 
 * @author luzhang
 *
 */

@Entity
@Table(name = "ov_vehicle_mileage_report")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_vehicle_mileage_report_sequence")
public class VehicleMileageReport extends BaseEntity {

  private static final long serialVersionUID = -7701072636165837668L;
  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 车辆信息
   */
  private Vehicle vehicle;
  /**
   * 每日行车里程总数
   */
  private Long mileage;

  /**
   * 报告统计时间
   */
  private Date vehicleMileageStatisticsDate;


  @Index(name = "vehicle_mileage_report_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.EAGER)
  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  @Column(nullable = false)
  @JsonProperty
  public Long getMileage() {
    return mileage;
  }

  public void setMileage(Long mileage) {
    this.mileage = mileage;
  }

  @JsonProperty
  @Temporal(TemporalType.DATE)
  @Index(name="vehiclemileagereport_vehiclemileagestatisticsdate")
  public Date getVehicleMileageStatisticsDate() {
    return vehicleMileageStatisticsDate;
  }

  public void setVehicleMileageStatisticsDate(Date vehicleMileageStatisticsDate) {
    this.vehicleMileageStatisticsDate = vehicleMileageStatisticsDate;
  }



}
