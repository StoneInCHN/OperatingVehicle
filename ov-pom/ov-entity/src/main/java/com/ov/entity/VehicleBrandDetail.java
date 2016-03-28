package com.ov.entity;

import java.util.HashSet;
import java.util.Set;

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

/**
 * 车辆型号详情表
 * 
 */
@Entity
@Table (name = "ov_vehicle_brand_detail")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "ov_vehicle_brand_detail_sequence")
public class VehicleBrandDetail extends BaseEntity
{
  private static final long serialVersionUID = 1L;

  /**
   * 平均油耗
   */
  private Float averageOil;

  /**
   * 名称首字母
   */
  private String code;

  /**
   * 排量
   */
  private Float disp;

  /**
   * 是否支持获取里程
   */
  private Boolean canGetmileage;

  /**
   * 是否支持获取油量
   */
  private Boolean canGetoil;

  /**
   * 树形节点级别
   */
  private int grade;

  /**
   * 图片
   */
  private String icon;

  /**
   * 最大电压
   */
  private float maxbv;

  /**
   * 最小电压
   */
  private float minbv;

  /**
   * 名称
   */
  private String name;

  /**
   * 是否支持OBD
   */
  private Boolean canOBD;
  /**
   * 百公里油耗
   */
  private Float oilPerHundred;

  private VehicleLine vehicleLine;

  /**
   * 油箱容积
   */
  private Float tank;
  
  private Set<Vehicle> vehicles = new HashSet<Vehicle> ();

  public Float getAverageOil ()
  {
    return averageOil;
  }

  public void setAverageOil (Float averageOil)
  {
    this.averageOil = averageOil;
  }

  public String getCode ()
  {
    return code;
  }

  public void setCode (String code)
  {
    this.code = code;
  }

  public Float getDisp ()
  {
    return disp;
  }

  public void setDisp (Float disp)
  {
    this.disp = disp;
  }

  public Boolean getCanGetmileage ()
  {
    return canGetmileage;
  }

  public void setCanGetmileage (Boolean canGetmileage)
  {
    this.canGetmileage = canGetmileage;
  }

  public Boolean getCanGetoil ()
  {
    return canGetoil;
  }

  public void setCanGetoil (Boolean canGetoil)
  {
    this.canGetoil = canGetoil;
  }

  public int getGrade ()
  {
    return grade;
  }

  public void setGrade (int grade)
  {
    this.grade = grade;
  }

  public String getIcon ()
  {
    return icon;
  }

  public void setIcon (String icon)
  {
    this.icon = icon;
  }

  public float getMaxbv ()
  {
    return maxbv;
  }

  public void setMaxbv (float maxbv)
  {
    this.maxbv = maxbv;
  }

  public float getMinbv ()
  {
    return minbv;
  }

  public void setMinbv (float minbv)
  {
    this.minbv = minbv;
  }

  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getName ()
  {
    return name;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public Boolean getCanOBD ()
  {
    return canOBD;
  }

  public void setCanOBD (Boolean canOBD)
  {
    this.canOBD = canOBD;
  }

  public Float getOilPerHundred ()
  {
    return oilPerHundred;
  }

  public void setOilPerHundred (Float oilPerHundred)
  {
    this.oilPerHundred = oilPerHundred;
  }

  public Float getTank ()
  {
    return tank;
  }

  public void setTank (Float tank)
  {
    this.tank = tank;
  }

  @OneToMany(mappedBy="vehicleBrandDetail")
  public Set<Vehicle> getVehicles ()
  {
    return vehicles;
  }

  public void setVehicles (Set<Vehicle> vehicles)
  {
    this.vehicles = vehicles;
  }

  @JsonProperty
  @ManyToOne(fetch=FetchType.EAGER)
  public VehicleLine getVehicleLine ()
  {
    return vehicleLine;
  }

  public void setVehicleLine (VehicleLine vehicleLine)
  {
    this.vehicleLine = vehicleLine;
  }

}