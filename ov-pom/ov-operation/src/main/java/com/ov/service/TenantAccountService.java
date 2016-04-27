package com.ov.service; 

import com.ov.entity.TenantAccount;
import com.ov.framework.service.BaseService;

public interface TenantAccountService extends BaseService<TenantAccount,Long>{
  /**
   * 判断用户名是否存在
   * 
   * @param username
   *            用户名(忽略大小写)
   * @return 用户名是否存在
   */
  boolean usernameExists(String username);
}