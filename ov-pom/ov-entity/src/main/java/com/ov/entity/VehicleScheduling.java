package com.ov.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import com.ov.entity.base.BaseEntity;
import com.ov.entity.commonenum.CommonEnum.VehicleSchedulingStatus;

/**
 *  车辆调度
 * 
 * @author yezhang
 *
 */
@Entity
@Table(name = "ov_vehicle_scheduling")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_vehicle_scheduling_sequence")
@Indexed(index="vehicleScheduling")
public class VehicleScheduling extends BaseEntity{

	private static final long serialVersionUID = -8205295087141493152L;

	/**
	 * 出发时间
	 */
	private Date startDate;
	
	/**
	 * 出发经度
	 */
	private Double startLongitude;
	
	/**
	 * 出发纬度
	 */
	private Double startLatitude;
	
	/**
	 * 出发地点详情
	 */
	private String startPositionDetails;
	
	/**
	 * 终点经度
	 */
	private Double endLongitude;
	
	/**
	 * 终点纬度
	 */
	private Double endLatitude;
	
	/**
	 * 终点地点详情
	 */
	private String endPositionDetails;
	
	/**
	 * 留言
	 */
	private String remark;
	
	/**
	 * 状态
	 */
	private VehicleSchedulingStatus status;
	
	/**
	 * 请求的分公司
	 */
	private TenantInfo requestBusiness;
	
	/**
	 * 总公司
	 */
	private TenantInfo parent;

	@Column
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column
	public Double getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(Double startLongitude) {
		this.startLongitude = startLongitude;
	}

	@Column
	public Double getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(Double startLatitude) {
		this.startLatitude = startLatitude;
	}

	@Column
	public String getStartPositionDetails() {
		return startPositionDetails;
	}

	public void setStartPositionDetails(String startPositionDetails) {
		this.startPositionDetails = startPositionDetails;
	}

	@Column
	public Double getEndLongitude() {
		return endLongitude;
	}

	public void setEndLongitude(Double endLongitude) {
		this.endLongitude = endLongitude;
	}

	@Column
	public Double getEndLatitude() {
		return endLatitude;
	}

	public void setEndLatitude(Double endLatitude) {
		this.endLatitude = endLatitude;
	}

	@Column
	public String getEndPositionDetails() {
		return endPositionDetails;
	}

	public void setEndPositionDetails(String endPositionDetails) {
		this.endPositionDetails = endPositionDetails;
	}

	@Column(length = 100)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column
	public VehicleSchedulingStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleSchedulingStatus status) {
		this.status = status;
	}

	@ManyToOne
	public TenantInfo getRequestBusiness() {
		return requestBusiness;
	}

	public void setRequestBusiness(TenantInfo requestBusiness) {
		this.requestBusiness = requestBusiness;
	}

	@ManyToOne
	public TenantInfo getParent() {
		return parent;
	}

	public void setParent(TenantInfo parent) {
		this.parent = parent;
	}
	
	
}
