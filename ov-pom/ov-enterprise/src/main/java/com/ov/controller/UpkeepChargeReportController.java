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
import com.ov.entity.UpkeepChargeReport;
import com.ov.entity.UpkeepTotalReport;
import com.ov.entity.Vehicle;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.ordering.Ordering;
import com.ov.framework.ordering.Ordering.Direction;
import com.ov.framework.paging.Pageable;
import com.ov.service.UpkeepChargeReportService;
import com.ov.service.UpkeepTotalReportService;
import com.ov.service.VehicleService;
import com.ov.utils.ReportDataComparator;

/**
 * Controller - 车辆维修费报表
 * 
 * @author luzhang
 *
 */
@Controller("upkeepChargeReportController")
@RequestMapping("console/upkeepChargeReport")
public class UpkeepChargeReportController extends BaseController {

  @Resource(name = "upkeepChargeReportServiceImpl")
  private UpkeepChargeReportService upkeepChargeReportService;
  @Resource(name = "upkeepTotalReportServiceImpl")
  private UpkeepTotalReportService upkeepTotalReportService;
  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/upkeepChargeReport", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/upkeepChargeReport";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/reportAll", method = RequestMethod.POST)
  public @ResponseBody List<UpkeepTotalReport> list(Model model, Pageable pageable, Date beginDate,
      Date endDate) {

    List<Filter> filters = constructFilter(null, beginDate, endDate);
    List<Ordering> orderings = constructOrdering();

    List<UpkeepTotalReport> upkeepTotalReportList =
        upkeepTotalReportService.findList(12, filters, orderings, true, null);
    ReportDataComparator comparator = new ReportDataComparator("upkeepChargeStatisticsDate");
    Collections.sort(upkeepTotalReportList, comparator);
    return upkeepTotalReportList;
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/reportSingleVehicle", method = RequestMethod.POST)
  public @ResponseBody List<UpkeepChargeReport> reportSingleVehichle(Model model, Long vehicleID,
      Pageable pageable, Date beginDate, Date endDate) {

    List<Filter> filters = constructFilter(vehicleID, beginDate, endDate);
    List<Ordering> orderings = constructOrdering();

    List<UpkeepChargeReport> upkeepChargeReportList =
        upkeepChargeReportService.findList(12, filters, orderings, true, null);
    ReportDataComparator comparator = new ReportDataComparator("upkeepChargeStatisticsDate");
    Collections.sort(upkeepChargeReportList, comparator);

    return upkeepChargeReportList;
  }

  private List<Ordering> constructOrdering() {
    // 时间倒序
    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering dateCycleOrdering = new Ordering("upkeepChargeStatisticsDate", Direction.desc);
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
      startDateFilter.setProperty("upkeepChargeStatisticsDate");
      startDateFilter.setValue(beginDate);
      filters.add(startDateFilter);
    }

    if (endDate != null) {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty("upkeepChargeStatisticsDate");
      endDateFilter.setValue(endDate);
      endDateFilter.setOperator(Operator.lt);
      filters.add(endDateFilter);
    }
    return filters;
  }
}
