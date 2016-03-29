package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.ov.entity.OilChargeReport;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.OilChargeReportDao;
@Repository("oilChargeReportDaoImpl")
public class OilChargeReportDaoImpl extends  BaseDaoImpl<OilChargeReport,Long> implements OilChargeReportDao {

}