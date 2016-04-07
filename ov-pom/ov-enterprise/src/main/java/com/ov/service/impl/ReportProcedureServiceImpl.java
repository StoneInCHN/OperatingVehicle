package com.ov.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ov.dao.ReportProcedureDao;
import com.ov.entity.base.BaseEntity;
import com.ov.framework.service.impl.BaseServiceImpl;
import com.ov.service.ReportProcedureService;

/**
 * 
 * @author luzhang
 *
 */
@Service ("reportProcedureServiceImpl")
public class ReportProcedureServiceImpl extends
    BaseServiceImpl<BaseEntity, Long> implements
    ReportProcedureService
{

  @Resource (name = "reportProcedureDaoImpl")
  private void setBaseDao (ReportProcedureDao reportProcedureDao)
  {
    super.setBaseDao (reportProcedureDao);
  }
}
