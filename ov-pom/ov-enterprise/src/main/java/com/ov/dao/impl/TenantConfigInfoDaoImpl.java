package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.TenantConfigInfo;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.TenantConfigInfoDao;
@Repository("tenantConfigInfoDaoImpl")
public class TenantConfigInfoDaoImpl extends  BaseDaoImpl<TenantConfigInfo,Long> implements TenantConfigInfoDao {

}