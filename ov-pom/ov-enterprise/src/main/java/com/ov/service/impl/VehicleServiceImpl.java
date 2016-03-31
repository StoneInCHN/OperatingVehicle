package com.ov.service.impl; 

import java.util.Date;

import javax.annotation.Resource; 

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service; 
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ov.entity.Vehicle;
import com.ov.dao.RoleDao;
import com.ov.dao.VehicleDao;
import com.ov.service.VehicleService;
import com.ov.utils.DateTimeUtils;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle,Long> implements VehicleService {

      @Resource(name="vehicleDaoImpl")
      public void setBaseDao(VehicleDao vehicleDao) {
         super.setBaseDao(vehicleDao);
      }
      
      @Resource(name = "vehicleDaoImpl")
      private VehicleDao vehicleDao;
      
      @Override
      public Page<Vehicle> searchPageByFilter(String vehiclePlateSearch, Pageable pageable,
          boolean isTenant) {
        IKAnalyzer analyzer = new IKAnalyzer();
        analyzer.setMaxWordLength(true);
        try {
          BooleanQuery query = getQuery(analyzer, vehiclePlateSearch, isTenant);
          return vehicleDao.search(query, pageable, analyzer, null);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    
      }
      
      private BooleanQuery getQuery(IKAnalyzer analyzer, String vehiclePlateSearch, boolean isTenant) {
        try {
          BooleanQuery query = new BooleanQuery();
          if (isTenant) {
            QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
            Query tenantIDQuery = queryParser.parse(tenantAccountService.getCurrentTenantID().toString());
            query.add(tenantIDQuery, Occur.MUST);
          }
          if (vehiclePlateSearch != null) {
            String text = QueryParser.escape(vehiclePlateSearch);
            QueryParser filterParser = new QueryParser(Version.LUCENE_35, "plate", analyzer);
            Query filterQuery = filterParser.parse(text);
            query.add(filterQuery, Occur.MUST);
          }
          return query;
        } catch (Exception e) {
          e.printStackTrace();
          return null;
        }
       
      }
}