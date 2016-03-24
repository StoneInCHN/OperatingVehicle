package com.ov.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	 * 标题
	 */
	private String title;
	
	/**
	 * 出发时间
	 */
	private Date startDate;
	
	/**
	 * 人数
	 */
	private Integer personNum;
	
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
	 * 拥堵费
	 */
	private Float congestionCharge;
	
	/**
	 * 备注
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

	@Column(length = 100)
	@JsonProperty
	@Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column
	@JsonProperty
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column
	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
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
	@JsonProperty
	@Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
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
	@JsonProperty
	@Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
	public String getEndPositionDetails() {
		return endPositionDetails;
	}

	public void setEndPositionDetails(String endPositionDetails) {
		this.endPositionDetails = endPositionDetails;
	}

	@Column
	public Float getCongestionCharge() {
		return congestionCharge;
	}

	public void setCongestionCharge(Float congestionCharge) {
		this.congestionCharge = congestionCharge;
	}

	@Column(length = 100)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column
	@JsonProperty
	@Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
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
