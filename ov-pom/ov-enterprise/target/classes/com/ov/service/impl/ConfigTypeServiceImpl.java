package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.ConfigType;
import com.ov.dao.ConfigTypeDao;
import com.ov.service.ConfigTypeService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("configTypeServiceImpl")
public class ConfigTypeServiceImpl extends BaseServiceImpl<ConfigType,Long> implements ConfigTypeService {

      @Resource(name="configTypeDaoImpl")
      public void setBaseDao(ConfigTypeDao configTypeDao) {
         super.setBaseDao(configTypeDao);
  }
}