package com.ov.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.DeviceTypeDao;
import com.ov.entity.DeviceType;
import com.ov.entity.commonenum.CommonEnum.Status;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.DeviceTypeService;
import com.ov.utils.FieldFilterUtils;

@Service("deviceTypeServiceImpl")
public class DeviceTypeServiceImpl extends BaseServiceImpl<DeviceType,Long> implements DeviceTypeService {

      @Resource(name="deviceTypeDaoImpl")
      private DeviceTypeDao deviceTypeDao;
      @Resource
      public void setBaseDao(DeviceTypeDao deviceTypeDao) {
         super.setBaseDao(deviceTypeDao);
  }

      @Override
      public List<Map<String, Object>> findAllDeviceType ()
      {
        List<Filter> filters = new ArrayList<Filter>();
        Filter statusFilter = new Filter ();
        statusFilter.setOperator (Operator.eq);
        statusFilter.setProperty ("status");
        statusFilter.setValue (Status.ENABLE);
        
        filters.add (statusFilter);
        
        List<DeviceType> deviceTypeList= deviceTypeDao.findList (null, null, filters, null);
        String[] propertys = {"id", "name"};
        return FieldFilterUtils.filterCollectionMap(propertys, deviceTypeList);
      }
}