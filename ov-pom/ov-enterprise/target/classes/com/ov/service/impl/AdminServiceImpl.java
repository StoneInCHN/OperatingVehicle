package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.Admin;
import com.ov.dao.AdminDao;
import com.ov.service.AdminService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin,Long> implements AdminService {

      @Resource(name="adminDaoImpl")
      public void setBaseDao(AdminDao adminDao) {
         super.setBaseDao(adminDao);
  }
}