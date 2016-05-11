package com.ov.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.ReportUserRegStatistics;
import com.ov.service.ReportUserRegStatisticsService;

@RequestMapping("console/reportUserReg")
@Controller("reportUserRegStatisticsController")
public class ReportUserRegStatisticsController extends BaseController{
  
  @Resource(name="reportUserRegStatisticsServiceImpl")
  private ReportUserRegStatisticsService reportUserRegStatisticsService;
  
  @RequestMapping(value = "/reportUserReg", method = RequestMethod.GET)
  public String reportUserReg() {
    return "/reportUserReg/reportUserReg";
  }
  
  @RequestMapping(value = "/getUserRegReport", method = RequestMethod.GET)
  public @ResponseBody List<ReportUserRegStatistics> getUserRegReport(Date beginDate,Date endDate) {
    if (beginDate ==null || endDate==null) {
      Calendar current = Calendar.getInstance();
      current.set(Calendar.DATE, -3);
      Date date = new Date();
      date.setTime(current.getTimeInMillis());
      beginDate = date;
      endDate  = new Date();
    }
    
    return reportUserRegStatisticsService.findList(beginDate, endDate);
  }
}
