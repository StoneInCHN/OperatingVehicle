package com.ov.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;

/*
 * 车队
 */
@Entity
@Table(name = "ov_motorcade")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_motorcade_sequence")
public class Motorcade extends BaseEntity{

	private static final long serialVersionUID = 7044117024315860925L;

	/**
	 * 描述
	 */
	private String motorcadeDesc;
	
	/**
	 * 所属总公司
	 */
	private TenantInfo parent;
	
	/**
	 * 包含车辆
	 */
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();

	@Column
	@JsonProperty
	@Field(index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(
      impl = IKAnalyzer.class))
	public String getMotorcadeDesc() {
		return motorcadeDesc;
	}

	public void setMotorcadeDesc(String motorcadeDesc) {
		this.motorcadeDesc = motorcadeDesc;
	}

	@ManyToOne
	public TenantInfo getParent() {
		return parent;
	}

	public void setParent(TenantInfo parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "motorcade", fetch = FetchType.LAZY)
	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	
}
