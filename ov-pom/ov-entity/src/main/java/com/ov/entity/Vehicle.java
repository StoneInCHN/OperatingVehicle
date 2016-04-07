package com.ov.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.entity.commonenum.CommonEnum.VehicleStatus;

/**
 * The persistent class for the csh_vehicle database table.
 * 
 */
@Indexed(index = "vehicle")
@Entity
@Table(name = "ov_vehicle")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_vehicle_sequence")
public class Vehicle extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 代理商
   * 
   * private String agent;
   */

  /**
   * 车辆型号
   */
  private VehicleBrandDetail vehicleBrandDetail;

  /**
   * 车架号
   */
  private String vehicleNo;
  /**
   * 颜色
   */
  private String color;

  /**
   * 是否默认显示
   */
  private Boolean isDefault;

  /**
   * 车牌号
   */
  private String plate;


  private String vin;

  /**
   * 仪表盘里程
   */
  private Float dashboardMileage;

  /**
   * 仪表盘电压
   */
  private Float dashboardBV;

  /**
   * 仪表盘油量
   */
  private Float dashboradOil;

  /**
   * 生产日期
   */
  private Date produceDate;

  /**
   * 上牌日期
   */
  private Date plateDate;
  
  private Long tenantID;

  /**
   * 所属公司
   */
  private TenantInfo tenantInfo;

  /**
   * 品牌图标
   */
  private String brandIcon;
  /**
   * 车辆车型全称
   */
  private String vehicleFullBrand;

  /**
   * 交强险到期时间
   */
  private String trafficInsuranceExpiration;
  /**
   * 商业险到期时间
   */
  private String commercialInsuranceExpiration;

  /**
   * 下次年检时间
   */
  private String nextAnnualInspection;

  /**
   * 行驶里程
   */
  private Long driveMileage;
  /**
   * 上次保养里程
   */
  private Long lastMaintainMileage;
  
  /**
   * 所属车队
   */
  private Motorcade motorcade;
  
  /**
   * 状态
   */
  private VehicleStatus vehicleStatus;
  

  @Column(length = 200)
  @JsonProperty
  public String getBrandIcon() {
    return brandIcon;
  }

  public void setBrandIcon(String brandIcon) {
    this.brandIcon = brandIcon;
  }

  public String getTrafficInsuranceExpiration() {
    return trafficInsuranceExpiration;
  }

  public void setTrafficInsuranceExpiration(String trafficInsuranceExpiration) {
    this.trafficInsuranceExpiration = trafficInsuranceExpiration;
  }

  public String getCommercialInsuranceExpiration() {
    return commercialInsuranceExpiration;
  }

  public void setCommercialInsuranceExpiration(String commercialInsuranceExpiration) {
    this.commercialInsuranceExpiration = commercialInsuranceExpiration;
  }

  public String getNextAnnualInspection() {
    return nextAnnualInspection;
  }

  public void setNextAnnualInspection(String nextAnnualInspection) {
    this.nextAnnualInspection = nextAnnualInspection;
  }

  public Long getDriveMileage() {
    return driveMileage;
  }

  public void setDriveMileage(Long driveMileage) {
    this.driveMileage = driveMileage;
  }

  public Long getLastMaintainMileage() {
    return lastMaintainMileage;
  }

  public void setLastMaintainMileage(Long lastMaintainMileage) {
    this.lastMaintainMileage = lastMaintainMileage;
  }

//  @Transient
  public String getVehicleFullBrand() {
//    VehicleLine vl = vehicleBrandDetail.getVehicleLine();
//    vehicleFullBrand = vl.getName();
//    if (vl.getParent() != null) {
//      VehicleLine vl_parent = vl.getParent();
//      vehicleFullBrand = vl_parent.getName() + "-" + vehicleFullBrand;
//    }
    return vehicleFullBrand;
  }

  public void setVehicleFullBrand(String vehicleFullBrand) {
    this.vehicleFullBrand = vehicleFullBrand;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.EAGER)
  @IndexedEmbedded
  public VehicleBrandDetail getVehicleBrandDetail() {
    return vehicleBrandDetail;
  }

  public void setVehicleBrandDetail(VehicleBrandDetail vehicleBrandDetail) {
    this.vehicleBrandDetail = vehicleBrandDetail;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Boolean getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(
      impl = IKAnalyzer.class))
  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }
  
  @Index(name = "index_vehicle_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  	@ManyToOne
  	public TenantInfo getTenantInfo() {
	  return tenantInfo;
	}
	
	public void setTenantInfo(TenantInfo tenantInfo) {
		this.tenantInfo = tenantInfo;
	}

@JsonProperty
  public String getVehicleNo() {
    return vehicleNo;
  }

  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }

  @JsonProperty
  public Float getDashboardMileage() {
    return dashboardMileage;
  }

  public void setDashboardMileage(Float dashboardMileage) {
    this.dashboardMileage = dashboardMileage;
  }

  @JsonProperty
  public Float getDashboardBV() {
    return dashboardBV;
  }

  public void setDashboardBV(Float dashboardBV) {
    this.dashboardBV = dashboardBV;
  }

  @JsonProperty
  public Float getDashboradOil() {
    return dashboradOil;
  }

  public void setDashboradOil(Float dashboradOil) {
    this.dashboradOil = dashboradOil;
  }

  public Date getProduceDate() {
    return produceDate;
  }

  public void setProduceDate(Date produceDate) {
    this.produceDate = produceDate;
  }


  public Date getPlateDate() {
    return plateDate;
  }

  public void setPlateDate(Date plateDate) {
    this.plateDate = plateDate;
  }
  	@JsonProperty
  	@ManyToOne(fetch = FetchType.EAGER)
	public Motorcade getMotorcade() {
		return motorcade;
	}
	
	public void setMotorcade(Motorcade motorcade) {
		this.motorcade = motorcade;
	}
	
	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}


}
