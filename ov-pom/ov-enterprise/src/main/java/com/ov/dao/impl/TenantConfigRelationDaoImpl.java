package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.TenantConfigRelation;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.TenantConfigRelationDao;
@Repository("tenantConfigRelationDaoImpl")
public class TenantConfigRelationDaoImpl extends  BaseDaoImpl<TenantConfigRelation,Long> implements TenantConfigRelationDao {

}