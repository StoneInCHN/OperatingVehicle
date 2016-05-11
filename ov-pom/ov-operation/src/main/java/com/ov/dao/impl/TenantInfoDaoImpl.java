package com.ov.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Repository;

import com.ov.dao.TenantInfoDao;
import com.ov.entity.TenantInfo;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;

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

 
}
