package com.ov.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ov.entity.base.BaseEntity;

/**
 * 配置元
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "ov_config_meta")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_config_meta_sequence")
public class ConfigMeta extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 配置元名称
   */
  private String name;

  /**
   * 配置元 key
   */
  private String configKey;

  /**
   * 配置元内容
   */
  private String content;

  /**
   * 配置元类型
   */
  private ConfigType configType;


  private Set<VersionConfig> versionConfig = new HashSet<VersionConfig>();

  @Column(length = 10)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 30)
  public String getConfigKey() {
    return configKey;
  }

  public void setConfigKey(String configKey) {
    this.configKey = configKey;
  }

  public String getContent() {
    return content;
  }

  @Column(length = 50)
  public void setContent(String content) {
    this.content = content;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public ConfigType getConfigType() {
    return configType;
  }

  public void setConfigType(ConfigType configType) {
    this.configType = configType;
  }

  @ManyToMany(mappedBy = "configMeta", fetch = FetchType.LAZY)
  public Set<VersionConfig> getVersionConfig() {
    return versionConfig;
  }

  public void setVersionConfig(Set<VersionConfig> versionConfig) {
    this.versionConfig = versionConfig;
  }

}
