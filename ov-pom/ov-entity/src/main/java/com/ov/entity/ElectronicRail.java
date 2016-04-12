package com.ov.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;

/**
 * 电子围栏
 * 
 */
@Entity
@Table(name = "ov_electronic_rail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_electronic_rail_sequence")
public class ElectronicRail extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 圆心经度
	 */
	private Double centerLng;
	
	/**
	 * 圆心纬度
	 */
	private Double centerLat;
	
	/**
	 * 半径(米)
	 */
	private Double radius;
	
	@JsonProperty
	public Double getCenterLng() {
		return centerLng;
	}

	public void setCenterLng(Double centerLng) {
		this.centerLng = centerLng;
	}

	@JsonProperty
	public Double getCenterLat() {
		return centerLat;
	}

	public void setCenterLat(Double centerLat) {
		this.centerLat = centerLat;
	}

	@JsonProperty
	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}
	
	
}
