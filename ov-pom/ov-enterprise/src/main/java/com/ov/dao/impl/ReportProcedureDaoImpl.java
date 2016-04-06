package com.ov.dao.impl;

import org.springframework.stereotype.Repository;

import com.ov.dao.ReportProcedureDao;
import com.ov.entity.base.BaseEntity;
import com.ov.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 报表存储过程调用
 * @author luzhang
 *
 */
@Repository("reportProcedureDaoImpl")
public class ReportProcedureDaoImpl extends BaseDaoImpl<BaseEntity, Long> implements ReportProcedureDao {


}
