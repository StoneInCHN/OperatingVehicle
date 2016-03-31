package com.ov.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.controller.base.BaseController;
import com.ov.entity.MaintenanceChargeReport;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.ordering.Ordering;
import com.ov.framework.ordering.Ordering.Direction;
import com.ov.framework.paging.Pageable;
import com.ov.service.MaintenanceChargeReportService;
import com.ov.utils.ReportDataComparator;

/**
 * Controller - 车辆保养报表
 * @author luzhang
 *
 */
@Controller("MaintenanceChargeReportController")
@RequestMapping("console/maintenanceChargeReport")
public class MaintenanceChargeReportController extends BaseController {
  
  @Resource(name = "MaintenanceChargeReportServiceImpl")
  private MaintenanceChargeReportService maintenanceChargeReportService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/maintenanceChargeReport", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/maintenanceChargeReport";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/reportAll", method = RequestMethod.POST)
  public @ResponseBody List<MaintenanceChargeReport> list(Model model, Pageable pageable
      ,Date beginDate, Date endDate) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("maintenanceChargeStatisticsDate",
        Direction.desc);
    orderings.add (dateCycleOrdering);
    
    List<Filter> filters = new ArrayList<Filter> ();
    if (beginDate != null)
    {
      Filter startDateFilter = new Filter();
      startDateFilter.setOperator (Operator.gt);
      startDateFilter.setProperty ("maintenanceChargeStatisticsDate");
      startDateFilter.setValue (beginDate);
      filters.add (startDateFilter);
    }
    
    if (endDate != null)
    {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty ("maintenanceChargeStatisticsDate");
      endDateFilter.setValue (endDate);
      endDateFilter.setOperator (Operator.lt);
      filters.add (endDateFilter);
    }
    
    List<MaintenanceChargeReport>  reportWaterElectricityRecordList = maintenanceChargeReportService.findList (12, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("maintenanceChargeStatisticsDate");
    Collections.sort (reportWaterElectricityRecordList, comparator);
    return reportWaterElectricityRecordList;
  }
}
