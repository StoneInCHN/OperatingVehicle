package com.ov.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ov.entity.base.BaseEntity;
import com.ov.entity.commonenum.CommonEnum.ClearingStatus;

@Entity
@Table (name = "ov_tenant_clearing_record")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "tenant_clearing_record_sequence")
public class TenantClearingRecord extends BaseEntity{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 结算单号
   */
  private String clearingSn;

  /**
   * 结算状态
   */
  private ClearingStatus clearingStatus;

  /**
   * 单价
   */
  private BigDecimal unitPrice = new BigDecimal(0);
  
  /**
   *本次结算金额 
   */
  private BigDecimal amountOfCurrent = new BigDecimal (0);

  /**
   * 扣除金额 比如个人所得税
   */
  private BigDecimal reduce=new BigDecimal(0);
  
  /**
   * 备注，记录扣除原因
   */
  private String comments;

  /**
   *关联的车辆调度请求VehicleScheduling 
   */
  private List<VehicleScheduling> vehicleSchedulings = new ArrayList<VehicleScheduling>();
  
  private TenantInfo parent;
  
  private TenantInfo child;

  @JsonProperty
  @Field(store = Store.YES, index = Index.UN_TOKENIZED)
  @Pattern(regexp = "^[0-9a-zA-Z_-]+$")
  @Length(max = 100)
  @Column(nullable = false, unique = true, length = 100)
  public String getClearingSn ()
  {
    return clearingSn;
  }

  public void setClearingSn (String clearingSn)
  {
    this.clearingSn = clearingSn;
  }

  public ClearingStatus getClearingStatus ()
  {
    return clearingStatus;
  }

  public void setClearingStatus (ClearingStatus clearingStatus)
  {
    this.clearingStatus = clearingStatus;
  }
  
  public BigDecimal getAmountOfCurrent()
  {
    return amountOfCurrent;
  }

  public void setAmountOfCurrent(BigDecimal amountOfCurrent)
  
  {
    this.amountOfCurrent = amountOfCurrent;
  }
  
  public BigDecimal getReduce() {
		return reduce;
	}

	public void setReduce(BigDecimal reduce) {
		this.reduce = reduce;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@ManyToOne
	public TenantInfo getParent() {
		return parent;
	}

	public void setParent(TenantInfo parent) {
		this.parent = parent;
	}
	
	@ManyToOne
	public TenantInfo getChild() {
		return child;
	}

	public void setChild(TenantInfo child) {
		this.child = child;
	}

    @OneToMany(mappedBy = "clearingRecord")
	public List<VehicleScheduling> getVehicleSchedulings() {
		return vehicleSchedulings;
	}

	public void setVehicleSchedulings(List<VehicleScheduling> vehicleSchedulings) {
		this.vehicleSchedulings = vehicleSchedulings;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

  
}
