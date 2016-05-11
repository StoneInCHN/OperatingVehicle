package com.ov.service.impl; 

import java.util.Set;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.ConfigMeta;
import com.ov.entity.TenantInfo;
import com.ov.dao.TenantInfoDao;
import com.ov.service.TenantInfoService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("tenantInfoServiceImpl")
public class TenantInfoServiceImpl extends BaseServiceImpl<TenantInfo,Long> implements TenantInfoService {

      @Resource(name="tenantInfoDaoImpl")
      public void setBaseDao(TenantInfoDao tenantInfoDao) {
         super.setBaseDao(tenantInfoDao);
  }

	@Override
	public TenantInfo findTenantWithOrgCode(String orgCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ConfigMeta> getCurrentTenantVersionPackage() {
		// TODO Auto-generated method stub
		return null;
	}
}