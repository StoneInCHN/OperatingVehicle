package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.MetaRelation;
import com.ov.dao.MetaRelationDao;
import com.ov.service.MetaRelationService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("metaRelationServiceImpl")
public class MetaRelationServiceImpl extends BaseServiceImpl<MetaRelation,Long> implements MetaRelationService {

      @Resource(name="metaRelationDaoImpl")
      public void setBaseDao(MetaRelationDao metaRelationDao) {
         super.setBaseDao(metaRelationDao);
  }
}