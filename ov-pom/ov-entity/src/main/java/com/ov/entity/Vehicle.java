package com.ov.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.entity.commonenum.CommonEnum.OilType;
import com.ov.entity.commonenum.CommonEnum.VehicleStatus;
import com.ov.lucene.VehicleDeviceBridgeImpl;

/**
 * The persistent class for the ov_vehicle database table.
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
   * 车牌号
   */
  private String plate;

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
  /**
   * 设备
   */
  private DeviceInfo device;
  /**
   * 设备号，冗余字段
   */
  private String deviceNo;

  /**
   * 油品名称 如93#汽油，0#柴油
   */
  private OilType oilType;

  /**
   * 电子围栏
   */
  private ElectronicRail electronicRail;

  /**
   * 当前车辆纬度
   */
  private Float lat;
  /**
   * 当前车辆经度
   */
  private Float lon;

  /**
   * 设备信息上传时间
   */
  private Date obdStatusTime;

  /**
   * 绑定的设备是否在线
   */
  private Boolean isOnline;
  /**
   * 绑定的设备历史离线记录
   */
  private Set<VehicleOffLineLog> vehicleOffLineLogs = new HashSet<VehicleOffLineLog>();

  private Set<String> faultCodeSet = new HashSet<String>();

  @Transient
  @JsonProperty
  public Set<String> getFaultCodeSet() {
    return faultCodeSet;
  }

  public void setFaultCodeSet(Set<String> faultCodeSet) {
    this.faultCodeSet = faultCodeSet;
  }

  @JsonProperty
  public Boolean getIsOnline() {
    return isOnline;
  }

  public void setIsOnline(Boolean isOnline) {
    this.isOnline = isOnline;
  }

  @Transient
  @JsonProperty
  public Date getObdStatusTime() {
    return obdStatusTime;
  }

  public void setObdStatusTime(Date obdStatusTime) {
    this.obdStatusTime = obdStatusTime;
  }

  @Transient
  @JsonProperty
  public Float getLon() {
    return lon;
  }

  public void setLon(Float lon) {
    this.lon = lon;
  }

  @Transient
  @JsonProperty
  public Float getLat() {
    return lat;
  }

  public void setLat(Float lat) {
    this.lat = lat;
  }

  public OilType getOilType() {
    return oilType;
  }

  public void setOilType(OilType oilType) {
    this.oilType = oilType;
  }

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

  // @Transient
  @Field(index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(
      impl = IKAnalyzer.class))
  @JsonProperty
  public String getVehicleFullBrand() {
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

  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED)
  @Index(name = "vehicle_plate")
  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
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
  @Index(name = "vehicle_vehicleno")
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

  @JsonProperty
  public Date getProduceDate() {
    return produceDate;
  }

  public void setProduceDate(Date produceDate) {
    this.produceDate = produceDate;
  }

  @JsonProperty
  public Date getPlateDate() {
    return plateDate;
  }

  public void setPlateDate(Date plateDate) {
    this.plateDate = plateDate;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.EAGER)
  @IndexedEmbedded
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

  @OneToOne(mappedBy = "vehicle", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED)
  @FieldBridge(impl = VehicleDeviceBridgeImpl.class)
  public DeviceInfo getDevice() {
    return device;
  }

  public void setDevice(DeviceInfo device) {
    this.device = device;
  }

  @JsonProperty
  @Transient
  public String getDeviceNo() {
    if (device != null) {
      return device.getDeviceNo();
    } else {
      return null;
    }

  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  @ManyToOne
  public ElectronicRail getElectronicRail() {
    return electronicRail;
  }

  public void setElectronicRail(ElectronicRail electronicRail) {
    this.electronicRail = electronicRail;
  }
  @JsonProperty
  @OneToMany(mappedBy = "vehicle")
  public Set<VehicleOffLineLog> getVehicleOffLineLogs() {
    return vehicleOffLineLogs;
  }

  public void setVehicleOffLineLogs(Set<VehicleOffLineLog> vehicleOffLineLogs) {
    this.vehicleOffLineLogs = vehicleOffLineLogs;
  }


}
