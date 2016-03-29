package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.TenantMetaProperty;
import com.ov.dao.TenantMetaPropertyDao;
import com.ov.service.TenantMetaPropertyService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("tenantMetaPropertyServiceImpl")
public class TenantMetaPropertyServiceImpl extends BaseServiceImpl<TenantMetaProperty,Long> implements TenantMetaPropertyService {

      @Resource(name="tenantMetaPropertyDaoImpl")
      public void setBaseDao(TenantMetaPropertyDao tenantMetaPropertyDao) {
         super.setBaseDao(tenantMetaPropertyDao);
  }
}