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

import com.ov.entity.MaintenanceCharge;
import com.ov.dao.MaintenanceChargeDao;
import com.ov.service.MaintenanceChargeService;
import com.ov.utils.DateTimeUtils;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("maintenanceChargeServiceImpl")
public class MaintenanceChargeServiceImpl extends BaseServiceImpl<MaintenanceCharge, Long>
    implements MaintenanceChargeService {

  @Resource(name = "maintenanceChargeDaoImpl")
  public void setBaseDao(MaintenanceChargeDao maintenanceChargeDao) {
    super.setBaseDao(maintenanceChargeDao);
  }

  @Resource(name = "maintenanceChargeDaoImpl")
  private MaintenanceChargeDao maintenanceChargeDao;

  @Override
  public Page<MaintenanceCharge> findPageByFilter(String vehiclePlateSearch, Date beginDate,
      Date endDate, Pageable pageable, boolean isTenant) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, vehiclePlateSearch, beginDate, endDate, isTenant);
      return maintenanceChargeDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  private BooleanQuery getQuery(IKAnalyzer analyzer, String plate, Date beginDate, Date endDate,
      boolean isTenant) {
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
      if (plate != null) {
        String text = QueryParser.escape(plate);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "vehicle.plate", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (beginDate != null || endDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("maintenanceDate",
                DateTimeUtils.convertDateToString(beginDate, null),
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
