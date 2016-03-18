package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.Role;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.RoleDao;
@Repository("roleDaoImpl")
public class RoleDaoImpl extends  BaseDaoImpl<Role,Long> implements RoleDao {

}