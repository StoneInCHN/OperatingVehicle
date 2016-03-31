package com.ov.job;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ov.common.log.LogUtil;
import com.ov.service.TenantInfoService;

@Component("BillJob")
@Lazy(false)
public class BillJob {


  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  @Scheduled(cron = "${job.monthly.bill.cron}")
  public void genMonthlyBill() {

    LogUtil.debug(BillJob.class, "Bill", "generate monthly bill start!");
    //tenantInfoService.genMonthlyBill();
    LogUtil.debug(BillJob.class, "Bill", "generate monthly bill end!");
  }
}