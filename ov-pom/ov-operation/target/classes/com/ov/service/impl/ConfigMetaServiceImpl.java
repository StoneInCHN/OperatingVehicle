package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.ConfigMeta;
import com.ov.dao.ConfigMetaDao;
import com.ov.service.ConfigMetaService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("configMetaServiceImpl")
public class ConfigMetaServiceImpl extends BaseServiceImpl<ConfigMeta,Long> implements ConfigMetaService {

      @Resource(name="configMetaDaoImpl")
      public void setBaseDao(ConfigMetaDao configMetaDao) {
         super.setBaseDao(configMetaDao);
  }
}