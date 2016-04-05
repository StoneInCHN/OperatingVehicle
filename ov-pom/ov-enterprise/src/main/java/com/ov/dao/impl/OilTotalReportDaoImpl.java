package com.ov.dao.impl; 

import org.springframework.stereotype.Repository; 
import com.ov.entity.OilTotalReport;
import com.ov.framework.dao.impl.BaseDaoImpl;
import com.ov.dao.OilTotalReportDao;
/**
 * DaoImpl - 加油报表(总)
 * @author luzhang
 *
 */
@Repository("oilTotalReportDaoImpl")
public class OilTotalReportDaoImpl extends  BaseDaoImpl<OilTotalReport,Long> implements OilTotalReportDao {

}