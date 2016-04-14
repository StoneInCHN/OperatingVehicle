package com.ov.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ov.dao.RoleDao;
import com.ov.entity.*;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.RoleService;
import com.ov.utils.DateTimeUtils;

/**
 * 角色 Service Impl
 *
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

  @Resource(name = "roleDaoImpl")
  private void setBaseDao(RoleDao roleDao) {
    super.setBaseDao(roleDao);
  }
  
  @Resource(name = "roleDaoImpl")
  private RoleDao roleDao;
  
  @Override
  public Page<Role> searchByFilter(String name_roleSearch, Date beginDate_roleSearch,
      Date endDate_roleSearch, Pageable pageable, boolean isTenant) {
      IKAnalyzer analyzer = new IKAnalyzer();
      analyzer.setMaxWordLength(true);
      try {
        BooleanQuery query = getQuery(analyzer, name_roleSearch, beginDate_roleSearch, endDate_roleSearch, isTenant);
        return roleDao.search(query, pageable, analyzer, null);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
  }
  
  private BooleanQuery getQuery(IKAnalyzer analyzer, String name, Date beginDate, Date endDate, boolean isTenant) {
    try {
      BooleanQuery query = new BooleanQuery();
      if (isTenant) {
        try {
          QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
          Query tenantIDquery =
              queryParser.parse(tenantAccountService.getCurrentTenantID().toString());
          query.add(tenantIDquery, Occur.MUST);
        } catch (ParseException e1) {
          e1.printStackTrace();
        }
      }
      if (name != null) {
        String text = QueryParser.escape(name);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (beginDate != null || endDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("creatDate", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), true, true);
        query.add(tQuery, Occur.MUST);
      }
      return query;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
   
  }

}
