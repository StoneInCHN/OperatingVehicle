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
  @Override
  public List<TenantInfo> findRoots(Long tenantID,Integer count) {
    String jpql =
        "select tenantInfo from TenantInfo tenantInfo where tenantInfo.parent is null and tenantInfo.id = :tenantID";
    TypedQuery<TenantInfo> query =
        entityManager.createQuery(jpql, TenantInfo.class).setParameter("tenantID", tenantID).setFlushMode(FlushModeType.COMMIT);
    if (count != null) {
      query.setMaxResults(count);
    }
    List<TenantInfo> list = query.getResultList();
    return list;
  }


}
