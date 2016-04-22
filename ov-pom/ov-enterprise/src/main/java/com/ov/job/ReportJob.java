package com.ov.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ov.common.log.LogUtil;
import com.ov.entity.TenantInfo;
import com.ov.service.ReportProcedureService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;
import com.ov.utils.ApiUtils;
import com.ov.utils.DateTimeUtils;
/**
 * 调用存储过程生成报表
 * @author luzhang
 *
 */
@Component("ReportJob")
@Lazy(false)
public class ReportJob {

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;
  
  @Resource(name = "reportProcedureServiceImpl")
  private ReportProcedureService reportProcedureService;
  
  //需要调用的存储过程名称
  private static final String[] procedures = {"report_maintenance_charge_pr","report_oil_charge_pr",
                      "report_upkeep_charge_pr"}; 
  
  //@Scheduled(cron = "${job.monthly.report.cron.test}")//用于测试
  //@Scheduled(cron = "${job.monthly.report.cron}")//每个月最后一天 23:30PM 跑一次
  public void startReportJob() {
    List<TenantInfo> tenantInfos = tenantInfoService.findAll();
    for (int i = 0; i < procedures.length; i++) {
      String procedure = procedures[i];
      LogUtil.debug(ReportJob.class, "startReportJob", "call " + procedure + " start!");
      Date currentDate = new Date ();
      String currentDateString = DateTimeUtils.convertDateToString (currentDate, "yyyy-MM-dd");
      for (int j = 0; j < tenantInfos.size(); j++) { //以租户为单位（一个事务）来调用存储过程
        Long tenantId = tenantInfos.get(j).getId();
        LogUtil.debug(ReportJob.class, "startReportJob", "tenantId: " + tenantId + " currentDateString:"+currentDateString);
        reportProcedureService.callProcedure(procedure,tenantId,currentDateString);
      }
      LogUtil.debug(ReportJob.class, "startReportJob", "call " + procedure + " end!");
    }
  }

}
