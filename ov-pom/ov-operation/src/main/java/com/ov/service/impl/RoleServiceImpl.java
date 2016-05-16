package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.Role;
import com.ov.dao.RoleDao;
import com.ov.dao.TenantAccountDao;
import com.ov.service.RoleService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements RoleService {

      @Resource(name="roleDaoImpl")
      public void setBaseDao(RoleDao roleDao) {
         super.setBaseDao(roleDao);
  }
      @Resource(name = "roleDaoImpl")
      private RoleDao roleDao;
      
      @Override
      public boolean roleNameExists(String name) {
        return roleDao.roleNameExists(name);
      }
}