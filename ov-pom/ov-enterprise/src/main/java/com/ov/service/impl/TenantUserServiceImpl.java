package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.TenantUserDao;
import com.ov.entity.TenantUser;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.TenantUserService;

@Service("tenantUserServiceImpl")
public class TenantUserServiceImpl extends BaseServiceImpl<TenantUser, Long> implements TenantUserService {

  @Resource(name = "tenantUserDaoImpl")
  private TenantUserDao tenantUserDao;
  
  @Resource
  public void setBaseDao(TenantUserDao tenantUserDao) {
    super.setBaseDao(tenantUserDao);
  }



}
