package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.TenantUserDao;
import com.ov.entity.TenantUser;
import com.ov.framework.dao.impl.BaseDaoImpl;

@Repository("tenantUserDaoImpl")
public class TenantUserDaoImpl extends BaseDaoImpl<TenantUser, Long> implements TenantUserDao {


}
