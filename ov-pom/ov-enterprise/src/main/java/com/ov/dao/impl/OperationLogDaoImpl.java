package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.OperationLog;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.OperationLogDao;
@Repository("operationLogDaoImpl")
public class OperationLogDaoImpl extends  BaseDaoImpl<OperationLog,Long> implements OperationLogDao {

}