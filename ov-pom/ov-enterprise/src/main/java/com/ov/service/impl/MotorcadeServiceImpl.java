package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.Motorcade;
import com.ov.dao.MotorcadeDao;
import com.ov.service.MotorcadeService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("motorcadeServiceImpl")
public class MotorcadeServiceImpl extends BaseServiceImpl<Motorcade,Long> implements MotorcadeService {

      @Resource(name="motorcadeDaoImpl")
      public void setBaseDao(MotorcadeDao motorcadeDao) {
         super.setBaseDao(motorcadeDao);
  }
}