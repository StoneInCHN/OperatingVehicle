package com.ov.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ov.dao.TenantInfoDao;
import com.ov.entity.Department;
import com.ov.entity.TenantInfo;
import com.ov.framework.dao.impl.BaseDaoImpl;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
@Repository("tenantInfoDaoImpl")
public class TenantInfoDaoImpl extends BaseDaoImpl<TenantInfo, Long> implements TenantInfoDao {

  public TenantInfo findTenantWithOrgCode(String orgCode) {
    try {
      String jpql =
          "select tenantInfo from TenantInfo tenantInfo where tenantInfo.orgCode = :orgCode";
      return entityManager.createQuery(jpql, TenantInfo.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("orgCode", orgCode).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
  
//  public List<TenantInfo> findChildren(TenantInfo tenantInfo, Integer count) {
//	    TypedQuery<TenantInfo> query;
//	    if (tenantInfo != null) {
//	      String jpql =
//	          "select tenantInfo from TenantInfo tenantInfo where tenantInfo.parent = :id order by tenantInfo.id desc";
//	      query =
//	          entityManager
//	              .createQuery(jpql, TenantInfo.class)
//	              .setFlushMode(FlushModeType.COMMIT)
//	              .setParameter(
//	                  "id",
//	                  "%" + Department.TREE_PATH_SEPARATOR + department.getId()
//	                      + Department.TREE_PATH_SEPARATOR + "%");
//	    } else {
//	      String jpql = "select department from Department department order by department.id asc";
//	      query = entityManager.createQuery(jpql, Department.class).setFlushMode(FlushModeType.COMMIT);
//	    }
//	    if (count != null) {
//	      query.setMaxResults(count);
//	    }
//	    return sort(query.getResultList(), department);
//	  }
  
  
}
