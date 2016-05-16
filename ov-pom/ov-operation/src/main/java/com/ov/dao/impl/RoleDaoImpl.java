package com.ov.dao.impl; 

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository; 

import com.ov.entity.Role;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.RoleDao;
@Repository("roleDaoImpl")
public class RoleDaoImpl extends  BaseDaoImpl<Role,Long> implements RoleDao {

  @Override
  public boolean roleNameExists(String name) {
    if (name == null) {
      return false;
    }
    String jpql = "select count(*) from Role role where lower(role.name) = lower(:name)";
    Long count =
        entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
            .setParameter("name", name).getSingleResult();
    return count > 0;
  }
}