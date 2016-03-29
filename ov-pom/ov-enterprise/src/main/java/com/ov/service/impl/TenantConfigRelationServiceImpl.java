package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.TenantConfigRelation;
import com.ov.dao.TenantConfigRelationDao;
import com.ov.service.TenantConfigRelationService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("tenantConfigRelationServiceImpl")
public class TenantConfigRelationServiceImpl extends BaseServiceImpl<TenantConfigRelation,Long> implements TenantConfigRelationService {

      @Resource(name="tenantConfigRelationDaoImpl")
      public void setBaseDao(TenantConfigRelationDao tenantConfigRelationDao) {
         super.setBaseDao(tenantConfigRelationDao);
  }
}