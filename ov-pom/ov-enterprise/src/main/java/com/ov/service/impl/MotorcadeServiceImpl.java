package com.ov.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.MotorcadeDao;
import com.ov.entity.DeviceType;
import com.ov.entity.Motorcade;
import com.ov.entity.TenantInfo;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.MotorcadeService;
import com.ov.service.TenantAccountService;
import com.ov.utils.FieldFilterUtils;

@Service("motorcadeServiceImpl")
public class MotorcadeServiceImpl extends BaseServiceImpl<Motorcade,Long> implements MotorcadeService {

      @Resource(name="motorcadeDaoImpl")
      private MotorcadeDao motorcadeDao;
      @Resource(name="tenantAccountServiceImpl")
      private TenantAccountService tenantAccountService;
      @Resource
      public void setBaseDao(MotorcadeDao motorcadeDao) {
         super.setBaseDao(motorcadeDao);
  }

      @Override
      public List<Map<String, Object>> findAllMotorcadeUnderTenant ()
      {
        TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo ();
        
        List<Filter> filters = new ArrayList<Filter>();
        
        Filter tenantFilter = new Filter ("parent", Operator.eq, tenantInfo);
        
        filters.add (tenantFilter);
       
        List<Motorcade> motorcadeList= motorcadeDao.findList (null, null, filters, null);
        String[] propertys = {"id", "motorcadeDesc"};
        return FieldFilterUtils.filterCollectionMap(propertys, motorcadeList);
      }
}