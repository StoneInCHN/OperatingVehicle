package com.ov.service;

import java.util.List;
import java.util.Set;

import com.ov.entity.ConfigMeta;
import com.ov.entity.Department;
import com.ov.entity.Role;
import com.ov.entity.TenantAccount;
import com.ov.entity.TenantInfo;
import com.ov.entity.TenantUser;
import com.ov.framework.service.BaseService;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
public interface TenantInfoService extends BaseService<TenantInfo, Long> {

  /**
   * 通过机构代码找租户
   * 
   * @param orgCode
   * @return
   */
  public TenantInfo findTenantWithOrgCode(String orgCode);

  /**
   * 获取当前用户版本的功能包
   * 
   * @return
   */
  Set<ConfigMeta> getCurrentTenantVersionPackage();

  List<TenantInfo> findRoots();

  void addAdminTransaction(TenantUser tenantUser, TenantAccount tenantAccount, Role role);

  void updateAdminTransaction(TenantUser tenantUser, TenantAccount tenantAccount);


}
