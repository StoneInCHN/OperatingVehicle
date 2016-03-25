package com.ov.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ov.dao.TenantInfoDao;
import com.ov.entity.ConfigMeta;
import com.ov.entity.Department;
import com.ov.entity.Role;
import com.ov.entity.TenantAccount;
import com.ov.entity.TenantInfo;
import com.ov.entity.TenantUser;
import com.ov.entity.commonenum.CommonEnum.ConfigKey;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.RoleService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;
import com.ov.service.TenantUserService;
import com.ov.utils.DateTimeUtils;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
@Service("tenantInfoServiceImpl")
public class TenantInfoServiceImpl extends BaseServiceImpl<TenantInfo, Long> implements
    TenantInfoService {


  @Resource(name = "tenantInfoDaoImpl")
  private TenantInfoDao tenantInfoDao;

  @Resource(name = "tenantInfoDaoImpl")
  public void setBaseDao(TenantInfoDao tenantInfoDao) {
    super.setBaseDao(tenantInfoDao);
  }

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @Resource (name = "tenantUserServiceImpl")
  private TenantUserService tenantUserService;
  
  @Resource (name = "roleServiceImpl")
  private RoleService roleService;

  @Override
  public TenantInfo findTenantWithOrgCode(String orgCode) {
    return tenantInfoDao.findTenantWithOrgCode(orgCode);
  }

  /**
   * 获取租户的功能包
   */
  @Override
  public Set<ConfigMeta> getCurrentTenantVersionPackage() {
    TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo();
    tenantInfo = tenantInfoDao.find(tenantInfo.getId());
    if (tenantInfo != null) {
      return tenantInfo.getVersionConfig().getConfigMeta();
    }
    return null;
  }

  public List<TenantInfo> findRoots() {
    return tenantInfoDao.findRoots(tenantAccountService.getCurrentTenantID(), null);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void addAdminTransaction(TenantUser tenantUser, TenantAccount tenantAccount, Role role) {
    tenantUserService.save (tenantUser,false);
    tenantAccount.setTenantUser(tenantUser);
    tenantAccountService.save (tenantAccount,false);
    roleService.save(role,false);
    Set<Role> roles = new HashSet<Role>();
    roles.add(role);
    tenantAccount.setRoles(roles);
    tenantAccountService.update(tenantAccount);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void updateAdminTransaction(TenantUser tenantUser, TenantAccount tenantAccount) {
    tenantUserService.update(tenantUser,"tenantID","tenantName");
    tenantAccountService.update(tenantAccount,"tenantID","isAdmin","loginDate","loginIp");
  }
}
