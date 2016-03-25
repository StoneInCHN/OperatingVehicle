package com.ov.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.entity.commonenum.CommonEnum.AccountStatus;

/**
 *  租户信息
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "ov_tenant_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "ov_tenant_info_sequence")
@Indexed(index="tenantInfo")
public class TenantInfo extends BaseEntity {

  
  private static final long serialVersionUID = 3525183712762376969L;

  /**
   * 租户组织机构代码
   */
  private String orgCode;
  
  /**
   * 租户名称
   */
  private String tenantName;
  
  /**
   * 联系电话
   */
  private String contactPhone;
  
  /**
   * 租户地址
   */
  private String address;
  
  /**
   * 联系人
   */
  private String contactPerson;
  
  /** 邮编 */
  private String zipCode;
  
  /** E-mail */
  private String email;
  /**
   * 备注
   */
  private String remark;
  
  /**
   * 租户账号状态
   */
  private AccountStatus accountStatus;
  
  /**
   * 总公司
   */
  private TenantInfo parent;
  
  /** 层次 */
  private Integer grade;
  
  /**
   * 子公司
   */
  private Set<TenantInfo> children = new HashSet<TenantInfo>();

  /**
   * 版本
   */
  private VersionConfig versionConfig;
  
  /**
   * 车辆调度请求
   */
  private Set<VehicleScheduling> requestScheduling = new HashSet<VehicleScheduling>();
  
  /**
   * 处理车辆调度请求
   */
  private Set<VehicleScheduling> dealScheduling = new HashSet<VehicleScheduling>(); 
  
  
  @Column(length = 20)
  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length = 30)
  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  @Column(length = 80)
  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  @Column(length = 100)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(length = 15)
  @JsonProperty
  @Field(index=org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  @Column(length = 20)
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Column(length = 60)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }
  
  @ManyToOne(cascade=CascadeType.ALL)
  public VersionConfig getVersionConfig ()
  {
    return versionConfig;
  }
  
  @JsonProperty
  @Column(length = 10)
  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  public void setVersionConfig (VersionConfig versionConfig)
  {
    this.versionConfig = versionConfig;
  }
  	@ManyToOne(fetch = FetchType.LAZY)
  	public TenantInfo getParent() {
		return parent;
	}
	
	public void setParent(TenantInfo parent) {
		this.parent = parent;
	}
	@JsonProperty
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<TenantInfo> getChildren() {
		return children;
	}

	public void setChildren(Set<TenantInfo> children) {
		this.children = children;
	}

	@OneToMany(mappedBy = "requestBusiness", fetch = FetchType.LAZY)
	public Set<VehicleScheduling> getRequestScheduling() {
		return requestScheduling;
	}

	public void setRequestScheduling(Set<VehicleScheduling> requestScheduling) {
		this.requestScheduling = requestScheduling;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<VehicleScheduling> getDealScheduling() {
		return dealScheduling;
	}

	public void setDealScheduling(Set<VehicleScheduling> dealScheduling) {
		this.dealScheduling = dealScheduling;
	}
	

  
}
