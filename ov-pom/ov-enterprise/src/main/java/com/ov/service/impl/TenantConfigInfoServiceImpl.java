package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.TenantConfigInfo;
import com.ov.dao.TenantConfigInfoDao;
import com.ov.service.TenantConfigInfoService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("tenantConfigInfoServiceImpl")
public class TenantConfigInfoServiceImpl extends BaseServiceImpl<TenantConfigInfo,Long> implements TenantConfigInfoService {

      @Resource(name="tenantConfigInfoDaoImpl")
      public void setBaseDao(TenantConfigInfoDao tenantConfigInfoDao) {
         super.setBaseDao(tenantConfigInfoDao);
  }
}