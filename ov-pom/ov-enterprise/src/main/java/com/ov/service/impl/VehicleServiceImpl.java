package com.ov.service.impl; 

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ov.beans.Setting;
import com.ov.dao.VehicleDao;
import com.ov.entity.Vehicle;
import com.ov.entity.commonenum.CommonEnum.OilType;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.json.response.VehicleDailyReport;
import com.ov.service.VehicleOilService;
import com.ov.service.VehicleService;
import com.ov.utils.ApiUtils;
import com.ov.utils.LuceneUtils;
import com.ov.utils.SettingUtils;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle,Long> implements VehicleService {

      @Resource(name="vehicleDaoImpl")
      public void setBaseDao(VehicleDao vehicleDao) {
         super.setBaseDao(vehicleDao);
      }
      
      @Resource(name = "vehicleDaoImpl")
      private VehicleDao vehicleDao;
      
      @Autowired
      private VehicleOilService vehicleOilService;
      
      private Setting setting = SettingUtils.get();
      
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

      @Override
      public VehicleDailyReport callVehicleDailyData (Date date, Long vehicleId)
      {
        Vehicle vehicle = vehicleDao.find (vehicleId);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put ("date", date);
        params.put ("deviceId", vehicle.getDevice ().getId ());
        VehicleDailyReport vehicleDailyReport = new VehicleDailyReport ();
        try
        {
          String response = ApiUtils.post (setting.getObdServerUrl() + "/tenantVehicleData/dailyVehicleStatus.jhtml", params);
          
//          String response = "{\"msg\":{\"dailyMileage\":10.0,\"averageFuelConsumption\":10.0,\"fuelConsumption\":0.0,\"cost\":null,\"averageSpeed\":0.0,\"emergencybrakecount\":0,\"suddenturncount\":0,\"rapidlyspeedupcount\":0}}";
          if (response != null)
          {
            OilType oilType = vehicle.getOilType ();
            String shortPlate = vehicle.getPlate ().substring (0,1);
            
            BigDecimal oilPrice = vehicleOilService.getOidPrice(oilType,shortPlate);
            
            ObjectMapper objectMapper = new ObjectMapper();
           
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode msgNode = rootNode.path ("msg");
            String msg = objectMapper.writeValueAsString(msgNode);
            vehicleDailyReport = objectMapper.readValue (msg, VehicleDailyReport.class);
            
            BigDecimal dailMile = new BigDecimal(vehicleDailyReport.getDailyMileage ().toString ());
            
            vehicleDailyReport.setDeviceId (vehicle.getDevice ().getId ());
            vehicleDailyReport.setReportDate (date);
            vehicleDailyReport.setCost (oilPrice.multiply (dailMile).doubleValue ());
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        return vehicleDailyReport;
      }
      
}