package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.VersionConfig;
import com.ov.dao.VersionConfigDao;
import com.ov.service.VersionConfigService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("versionConfigServiceImpl")
public class VersionConfigServiceImpl extends BaseServiceImpl<VersionConfig,Long> implements VersionConfigService {

      @Resource(name="versionConfigDaoImpl")
      public void setBaseDao(VersionConfigDao versionConfigDao) {
         super.setBaseDao(versionConfigDao);
  }
}