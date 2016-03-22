package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.RoleDao;
import com.ov.entity.Role;
import com.ov.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 角色
 * @author pengyanan
 *
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

}
