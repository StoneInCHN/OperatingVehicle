package com.ov.dao.impl; 

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Repository; 

import com.ov.entity.OperationLog;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.dao.OperationLogDao;
@Repository("operationLogDaoImpl")
public class OperationLogDaoImpl extends  BaseDaoImpl<OperationLog,Long> implements OperationLogDao {

  @Override
  public Page<OperationLog> search(Query query, Pageable pageable, Analyzer analyzer, Filter filter) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void refreshIndex() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int count(Query query, Analyzer analyzer, Filter filter) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<OperationLog> searchList(Query query, Analyzer analyzer, Filter filter) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void callProcedure(String procName, Object... args) {
    // TODO Auto-generated method stub
    
  }

}