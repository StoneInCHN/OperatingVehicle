package com.ov.service.impl; 

import javax.annotation.Resource;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ov.dao.VehicleDao;
import com.ov.entity.Vehicle;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.VehicleService;
import com.ov.utils.LuceneUtils;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle,Long> implements VehicleService {

      @Resource(name="vehicleDaoImpl")
      public void setBaseDao(VehicleDao vehicleDao) {
         super.setBaseDao(vehicleDao);
      }
      
      @Resource(name = "vehicleDaoImpl")
      private VehicleDao vehicleDao;
      
      @Override
      public Page<Vehicle> searchPageByFilter(String vehiclePlateSearch,String motorcadeSearch, String vehicleBrandSearch,Pageable pageable) {
        IKAnalyzer analyzer = new IKAnalyzer();
        analyzer.setMaxWordLength(true);
        try {
          
          BooleanQuery query = new BooleanQuery ();
          Query tenantQuery = LuceneUtils.getTermQuery ("tenantID", tenantAccountService.getCurrentTenantID ().toString ());
          query.add (tenantQuery,Occur.MUST);
          Query unBindQuery = LuceneUtils.getTermQuery ("device", "null");
          query.add (unBindQuery,Occur.MUST);
          
          if (vehiclePlateSearch != null){
            Query plateQuery = LuceneUtils.getBooleanQuery (analyzer,"plate", vehiclePlateSearch, true);
            query.add (plateQuery,Occur.MUST);
          }
          
          if (vehicleBrandSearch != null){
            Query vehicleBrandQuery = LuceneUtils.getBooleanQuery (analyzer,"vehicleFullBrand", vehicleBrandSearch, true);
            query.add (vehicleBrandQuery,Occur.MUST);
          }
          
          if(motorcadeSearch != null){
            Query motorcadeQuery = LuceneUtils.getBooleanQuery (analyzer,"motorcade.motorcadeDesc", motorcadeSearch, true);
            query.add (motorcadeQuery,Occur.MUST);
          }
          
          return vehicleDao.search(query, pageable, analyzer, null);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    
      }

      @Override
      public Page<Vehicle> listUnBuindVehicle (String vehiclePlateSearch,
          String motorcadeSearch, String vehicleFullBrandSearch,
          Pageable pageable)
      {
        return vehicleDao.listUnBuindVehicle(vehiclePlateSearch,motorcadeSearch,
              vehicleFullBrandSearch,pageable);
      }
      
}