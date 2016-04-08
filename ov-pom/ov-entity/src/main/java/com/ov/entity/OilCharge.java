package com.ov.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.lucene.DateBridgeImpl;

/**
 * 车辆加油信息
 * @author luzhang
 *
 */
@Entity
@Table(name = "ov_oil_charge")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_oil_charge_sequence")
@Indexed(index="oilCharge")
public class OilCharge extends BaseEntity{

  private static final long serialVersionUID = -6400399057900315583L;
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
   * 油费合计
   */
  private BigDecimal oilAmount;
  /**
   * 优惠金额
   */
  private BigDecimal discountAmount;
  /**
   * 实收金额 = (油费合计 - 优惠金额)
   */
  private BigDecimal oilFinalAmount;
  /**
   * 加油量L
   */
  private BigDecimal oilCount;
  /**
   * 单价/元
   */
  private BigDecimal oilPrice;
  /**
   * 燃油标号 比如93#，95#，97#
   */
  private String oilLabel;
  /**
   * 是否提供发票（油票）
   */
  private Boolean provideInvoice;
  /**
   * 发票（油票）号码
   */
  private String invoiceNumber;
  /**
   * 加油日期
   */
  private Date oilDate;
  /**
   * 备注
   */
  private String remark;
  
  @org.hibernate.annotations.Index(name="oil_charge_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }
  @org.hibernate.annotations.Index(name="oil_charge_vehicleid")
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
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getOilAmount() {
    return oilAmount;
  }
  public void setOilAmount(BigDecimal oilAmount) {
    this.oilAmount = oilAmount;
  }
  
  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getDiscountAmount() {
    return discountAmount;
  }
  public void setDiscountAmount(BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }
  
  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getOilFinalAmount() {
    return oilFinalAmount;
  }
  public void setOilFinalAmount(BigDecimal oilFinalAmount) {
    this.oilFinalAmount = oilFinalAmount;
  }
  
  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getOilCount() {
    return oilCount;
  }
  public void setOilCount(BigDecimal oilCount) {
    this.oilCount = oilCount;
  }
  
  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getOilPrice() {
    return oilPrice;
  }
  public void setOilPrice(BigDecimal oilPrice) {
    this.oilPrice = oilPrice;
  }
  
  @JsonProperty
  @Column(length = 5)
  public String getOilLabel() {
    return oilLabel;
  }
  public void setOilLabel(String oilLabel) {
    this.oilLabel = oilLabel;
  }
  
  public Boolean getProvideInvoice() {
    return provideInvoice;
  }
  public void setProvideInvoice(Boolean provideInvoice) {
    this.provideInvoice = provideInvoice;
  }
  
  @JsonProperty
  @Column(length = 20)
  public String getInvoiceNumber() {
    return invoiceNumber;
  }
  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }
  
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getOilDate() {
    return oilDate;
  }
  public void setOilDate(Date oilDate) {
    this.oilDate = oilDate;
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
