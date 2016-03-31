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
import com.ov.entity.VehicleMileageReport;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.ordering.Ordering;
import com.ov.framework.ordering.Ordering.Direction;
import com.ov.framework.paging.Pageable;
import com.ov.service.VehicleMileageReportService;
import com.ov.utils.ReportDataComparator;

/**
 * Controller - 车辆维修费报表
 * @author luzhang
 *
 */
@Controller("vehicleMileageReportController")
@RequestMapping("console/vehicleMileageReport")
public class VehicleMileageReportController extends BaseController {
  
  @Resource(name = "vehicleMileageReportServiceImpl")
  private VehicleMileageReportService vehicleMileageReportService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/vehicleMileageReport", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/vehicleMileageReport";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/reportAll", method = RequestMethod.POST)
  public @ResponseBody List<VehicleMileageReport> list(Model model, Pageable pageable
      ,Date beginDate, Date endDate) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("vehicleMileageStatisticsDate",
        Direction.desc);
    orderings.add (dateCycleOrdering);
    
    List<Filter> filters = new ArrayList<Filter> ();
    if (beginDate != null)
    {
      Filter startDateFilter = new Filter();
      startDateFilter.setOperator (Operator.gt);
      startDateFilter.setProperty ("vehicleMileageStatisticsDate");
      startDateFilter.setValue (beginDate);
      filters.add (startDateFilter);
    }
    
    if (endDate != null)
    {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty ("vehicleMileageStatisticsDate");
      endDateFilter.setValue (endDate);
      endDateFilter.setOperator (Operator.lt);
      filters.add (endDateFilter);
    }
    
    List<VehicleMileageReport>  reportWaterElectricityRecordList = vehicleMileageReportService.findList (12, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("vehicleMileageStatisticsDate");
    Collections.sort (reportWaterElectricityRecordList, comparator);
    return reportWaterElectricityRecordList;
  }
}
