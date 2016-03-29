package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.TenantMetaProperty;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.TenantMetaPropertyDao;
@Repository("tenantMetaPropertyDaoImpl")
public class TenantMetaPropertyDaoImpl extends  BaseDaoImpl<TenantMetaProperty,Long> implements TenantMetaPropertyDao {

}