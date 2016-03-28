package com.ov.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;

/**
 * 车辆品牌表
 * 
 */
@Indexed(index="vehicleBrand")
@Entity
@Table (name = "csh_vehicle_brand")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "csh_vehicle_brand_sequence")
public class VehicleBrand extends BaseEntity
{
  private static final long serialVersionUID = 1L;


  /**
   * 名称首字母
   */
  private String code;


  /**
   * 图片
   */
  private String icon;


  /**
   * 名称
   */
  private String name;

  
  private Set<VehicleLine> children = new HashSet<VehicleLine> ();


  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.UN_TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getCode ()
  {
    return code;
  }

  public void setCode (String code)
  {
    this.code = code;
  }


  public String getIcon ()
  {
    return icon;
  }

  public void setIcon (String icon)
  {
    this.icon = icon;
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
  
  @OneToMany(mappedBy="vehicleBrand")
  public Set<VehicleLine> getChildren ()
  {
    return children;
  }

  public void setChildren (Set<VehicleLine> children)
  {
    this.children = children;
  }

}