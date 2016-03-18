package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.Area;
import com.ov.dao.AreaDao;
import com.ov.service.AreaService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseServiceImpl<Area,Long> implements AreaService {

      @Resource(name="areaDaoImpl")
      public void setBaseDao(AreaDao areaDao) {
         super.setBaseDao(areaDao);
  }
}