package com.ov.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ov.dao.AdminDao;
import com.ov.entity.Admin;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;

/**
 * Dao - 管理员
 * 
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao {

  public boolean usernameExists(String username) {
    if (username == null) {
      return false;
    }
    String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
    Long count =
        entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
            .setParameter("username", username).getSingleResult();
    return count > 0;
  }

  public boolean emailExists(String email) {
    if (email == null) {
      return false;
    }
    String jpql = "select count(*) from Admin admin where lower(admin.email) = lower(:email)";
    Long count =
        entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
            .setParameter("email", email).getSingleResult();
    return count > 0;
  }

  @Transactional
  public Admin findByUsername(String username) {
    if (username == null) {
      return null;
    }
    try {
      String jpql = "select admin from Admin admin where lower(admin.username) = lower(:username)";
      return entityManager.createQuery(jpql, Admin.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("username", username).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public List<Admin> findByName(String name) {
    if (name == null) {
      return null;
    }
    try {
      String jpql = "select admin from Admin admin where lower(admin.name) like lower(:name)";
      return entityManager.createQuery(jpql, Admin.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("name", "%" + name + "%").getResultList();

    } catch (NoResultException e) {
      return null;
    }
  }

  @Transactional
  public Admin findByNameAccurate(String name) {
    String jpql = "select admin from Admin admin where lower(admin.name)= lower(:name)";
    try {
      return entityManager.createQuery(jpql, Admin.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("name", name).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }

  }

  @Override
  public Page<Admin> search(Query query, Pageable pageable, Analyzer analyzer, Filter filter) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void refreshIndex() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int count(Query query, Analyzer analyzer, Filter filter) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<Admin> searchList(Query query, Analyzer analyzer, Filter filter) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void callProcedure(String procName, Object... args) {
    // TODO Auto-generated method stub
    
  }

}
