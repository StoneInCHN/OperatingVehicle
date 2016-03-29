package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.TenantExtendColumn;
import com.ov.dao.TenantExtendColumnDao;
import com.ov.service.TenantExtendColumnService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("tenantExtendColumnServiceImpl")
public class TenantExtendColumnServiceImpl extends BaseServiceImpl<TenantExtendColumn,Long> implements TenantExtendColumnService {

      @Resource(name="tenantExtendColumnDaoImpl")
      public void setBaseDao(TenantExtendColumnDao tenantExtendColumnDao) {
         super.setBaseDao(tenantExtendColumnDao);
  }
}