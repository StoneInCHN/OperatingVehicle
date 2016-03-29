package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.TenantExtendColumn;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.TenantExtendColumnDao;
@Repository("tenantExtendColumnDaoImpl")
public class TenantExtendColumnDaoImpl extends  BaseDaoImpl<TenantExtendColumn,Long> implements TenantExtendColumnDao {

}