package com.ov.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.ov.entity.OperationLog;
import com.ov.dao.OperationLogDao;
import com.ov.service.OperationLogService;
import com.ov.framework.service.impl.BaseServiceImpl;

@Service("operationLogServiceImpl")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog,Long> implements OperationLogService {

      @Resource(name="operationLogDaoImpl")
      public void setBaseDao(OperationLogDao operationLogDao) {
         super.setBaseDao(operationLogDao);
  }
}