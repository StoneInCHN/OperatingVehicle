package com.ov.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.entity.commonenum.CommonEnum.Status;

/**
 * 设备类型
 * 
 */
@Entity
@Table (name = "ov_device_type")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "ov_device_type_sequence")
public class DeviceType extends BaseEntity
{
  private static final long serialVersionUID = 1L;

  /**
   * 类型名称
   */
  private String name;

  /**
   * 状态
   */
  private Status status;

  private Set<DeviceInfo> deviceInfos = new HashSet<DeviceInfo> ();
  @JsonProperty
  @Field(store=Store.NO,index=Index.UN_TOKENIZED)
  @Column(length=20)
  public String getName ()
  {
    return name;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  @Column(nullable= false)
  public Status getStatus ()
  {
    return status;
  }

  public void setStatus (Status status)
  {
    this.status = status;
  }

  @OneToMany(mappedBy= "type")
  public Set<DeviceInfo> getDeviceInfos ()
  {
    return deviceInfos;
  }

  public void setDeviceInfos (Set<DeviceInfo> deviceInfos)
  {
    this.deviceInfos = deviceInfos;
  }


}