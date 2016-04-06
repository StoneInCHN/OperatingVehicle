package com.ov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;

/**
 * 每日车辆里程
 * @author luzhang
 *
 */

@Entity
@Table(name = "ov_vehicle_mileage")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_vehicle_mileage_sequence")
@Indexed(index = "vehiclemileage")
public class VehicleMileage extends BaseEntity {

	private static final long serialVersionUID = -7701072636165837668L;
	/**
	   * 租户ID
	   */
	private Long tenantID;
	  /**
	   * 车辆 ID
	   */
	private Long vehicleID;
	/**
	 * 车辆信息
	 */
	private Vehicle vehicle;
	/**
	 * 每日行车里程总数
	 */
	private Long mileage;
	
	/**
	 * 备注
	 */
	private String remark;
	
	@Index(name="vehicle_mileage_tenantid")
	@Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
	public Long getTenantID() {
	   return tenantID;
	}

	public void setTenantID(Long tenantID) {
	   this.tenantID = tenantID;
	}
	@org.hibernate.annotations.Index(name="vehicle_mileage_vehicleid")
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

	@Column(nullable = false)
	public Long getMileage() {
		return mileage;
	}

	public void setMileage(Long mileage) {
		this.mileage = mileage;
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
