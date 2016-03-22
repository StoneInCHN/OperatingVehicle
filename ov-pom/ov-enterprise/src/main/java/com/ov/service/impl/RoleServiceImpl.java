package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.RoleDao;
import com.ov.entity.*;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.RoleService;

/**
 * 
 * @author pengyanan
 *
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

  @Resource(name = "roleDaoImpl")
  private void setBaseDao(RoleDao roleDao) {
    super.setBaseDao(roleDao);
  }
}
