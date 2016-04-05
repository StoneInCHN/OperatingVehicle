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
import com.ov.entity.MaintenanceTotalReport;
import com.ov.entity.Vehicle;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.ordering.Ordering;
import com.ov.framework.ordering.Ordering.Direction;
import com.ov.framework.paging.Pageable;
import com.ov.service.MaintenanceChargeReportService;
import com.ov.service.MaintenanceTotalReportService;
import com.ov.service.VehicleService;
import com.ov.utils.ReportDataComparator;

/**
 * Controller - 车辆保养报表
 * 
 * @author luzhang
 *
 */
@Controller("MaintenanceChargeReportController")
@RequestMapping("console/maintenanceChargeReport")
public class MaintenanceChargeReportController extends BaseController {

  @Resource(name = "MaintenanceChargeReportServiceImpl")
  private MaintenanceChargeReportService maintenanceChargeReportService;
  @Resource(name = "MaintenanceTotalReportServiceImpl")
  private MaintenanceTotalReportService maintenanceTotalReportService;
  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

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
  public @ResponseBody List<MaintenanceTotalReport> list(Model model, Pageable pageable,
      Date beginDate, Date endDate) {

    List<Filter> filters = constructFilter(null, beginDate, endDate);
    List<Ordering> orderings = constructOrdering();

    List<MaintenanceTotalReport> maintenanceTotalReportList =
        maintenanceTotalReportService.findList(12, filters, orderings, true, null);
    ReportDataComparator comparator = new ReportDataComparator("maintenanceChargeStatisticsDate");
    Collections.sort(maintenanceTotalReportList, comparator);
    return maintenanceTotalReportList;
  }

  @RequestMapping(value = "/reportSingleVehicle", method = RequestMethod.POST)
  public @ResponseBody List<MaintenanceChargeReport> list(Model model, Long vehicleID,
      Pageable pageable, Date beginDate, Date endDate) {

    List<Filter> filters = constructFilter(vehicleID, beginDate, endDate);
    List<Ordering> orderings = constructOrdering();

    List<MaintenanceChargeReport> maintenanceChargeReportList =
        maintenanceChargeReportService.findList(12, filters, orderings, true, null);
    ReportDataComparator comparator = new ReportDataComparator("maintenanceChargeStatisticsDate");
    Collections.sort(maintenanceChargeReportList, comparator);
    return maintenanceChargeReportList;
  }

  private List<Ordering> constructOrdering() {
    // 时间倒序
    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering dateCycleOrdering = new Ordering("maintenanceChargeStatisticsDate", Direction.desc);
    orderings.add(dateCycleOrdering);
    return orderings;
  }

  private List<Filter> constructFilter(Long vehicleID, Date beginDate, Date endDate) {
    if (vehicleID == null) {
      return null;
    }
    List<Filter> filters = new ArrayList<Filter>();
    Vehicle vehicle = vehicleService.find(vehicleID);
    if (vehicle == null) {
      return null;

    } else {
      Filter vehicleFilter = new Filter();
      vehicleFilter.setOperator(Operator.eq);
      vehicleFilter.setProperty("vehicle");
      vehicleFilter.setValue(vehicle);
      filters.add(vehicleFilter);
    }


    if (beginDate != null) {
      Filter startDateFilter = new Filter();
      startDateFilter.setOperator(Operator.gt);
      startDateFilter.setProperty("maintenanceChargeStatisticsDate");
      startDateFilter.setValue(beginDate);
      filters.add(startDateFilter);
    }

    if (endDate != null) {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty("maintenanceChargeStatisticsDate");
      endDateFilter.setValue(endDate);
      endDateFilter.setOperator(Operator.lt);
      filters.add(endDateFilter);
    }
    return filters;
  }
}
