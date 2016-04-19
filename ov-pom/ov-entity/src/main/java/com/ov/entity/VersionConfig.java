package com.ov.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.entity.commonenum.CommonEnum.VersionStatus;

@Entity
@Table(name = "ov_version_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_version_config")
public class VersionConfig extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -1856670758805303986L;
  /**
   * 版本名称
   */
  private String versionName;
  
  /**
   * 是否为内置版本（完整版）
   */
  private Boolean isSystem;

  /**
   * 元数据
   */
  private Set<ConfigMeta> configMeta;
  /**
   * 租户
   */
  private Set<TenantInfo> tenantInfos;

  /**
   * 版本状态
   */
  private VersionStatus versionStatus;
  
  @JsonProperty
  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }
  @JsonProperty
  public Boolean getIsSystem() {
    return isSystem;
  }

  public void setIsSystem(Boolean isSystem) {
    this.isSystem = isSystem;
  }
  @ManyToMany
  public Set<ConfigMeta> getConfigMeta() {
    return configMeta;
  }

  public void setConfigMeta(Set<ConfigMeta> configMeta) {
    this.configMeta = configMeta;
  }

  @OneToMany(mappedBy = "versionConfig", fetch = FetchType.LAZY)
  public Set<TenantInfo> getTenantInfos() {
    return tenantInfos;
  }

  public void setTenantInfos(Set<TenantInfo> tenantInfos) {
    this.tenantInfos = tenantInfos;
  }

  public VersionStatus getVersionStatus() {
    return versionStatus;
  }

  public void setVersionStatus(VersionStatus versionStatus) {
    this.versionStatus = versionStatus;
  }

}
