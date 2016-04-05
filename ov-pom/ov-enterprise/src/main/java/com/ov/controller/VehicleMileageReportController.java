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
import com.ov.entity.Vehicle;
import com.ov.entity.VehicleMileageReport;
import com.ov.entity.VehicleMileageTotalReport;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.ordering.Ordering;
import com.ov.framework.ordering.Ordering.Direction;
import com.ov.framework.paging.Pageable;
import com.ov.framework.service.BaseService;
import com.ov.service.VehicleMileageReportService;
import com.ov.service.VehicleMileageTotalReportService;
import com.ov.service.VehicleService;
import com.ov.utils.ReportDataComparator;

/**
 * Controller - 车辆维修费报表
 * 
 * @author luzhang
 *
 */
@Controller("vehicleMileageReportController")
@RequestMapping("console/vehicleMileageReport")
public class VehicleMileageReportController extends BaseController {

  @Resource(name = "vehicleMileageReportServiceImpl")
  private VehicleMileageReportService vehicleMileageReportService;
  @Resource(name = "vehicleMileageTotalReportServiceImpl")
  private VehicleMileageTotalReportService vehicleMileageTotalReportService;
  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

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
  public @ResponseBody List<VehicleMileageTotalReport> list(Model model, Pageable pageable,
      Date beginDate, Date endDate) {

    List<Filter> filters = constructFilter(null, beginDate, endDate);
    List<Ordering> orderings = constructOrdering();
    List<VehicleMileageTotalReport> vehicleMileageTotalReportList =
        vehicleMileageTotalReportService.findList(12, filters, orderings, true, null);
    ReportDataComparator comparator = new ReportDataComparator("vehicleMileageStatisticsDate");
    Collections.sort(vehicleMileageTotalReportList, comparator);
    return vehicleMileageTotalReportList;
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/reportSingleVehicle", method = RequestMethod.POST)
  public @ResponseBody List<VehicleMileageReport> list(Model model, Long vehicleID,
      Pageable pageable, Date beginDate, Date endDate) {

    List<Filter> filters = constructFilter(vehicleID, beginDate, endDate);
    List<Ordering> orderings = constructOrdering();

    List<VehicleMileageReport> vehicleMileageTotalReportList =
        vehicleMileageReportService.findList(12, filters, orderings, true, null);
    ReportDataComparator comparator = new ReportDataComparator("vehicleMileageStatisticsDate");
    Collections.sort(vehicleMileageTotalReportList, comparator);
    return vehicleMileageTotalReportList;
  }

  private List<Ordering> constructOrdering() {
    // 时间倒序
    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering dateCycleOrdering = new Ordering("vehicleMileageStatisticsDate", Direction.desc);
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
      startDateFilter.setProperty("vehicleMileageStatisticsDate");
      startDateFilter.setValue(beginDate);
      filters.add(startDateFilter);
    }

    if (endDate != null) {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty("vehicleMileageStatisticsDate");
      endDateFilter.setValue(endDate);
      endDateFilter.setOperator(Operator.lt);
      filters.add(endDateFilter);
    }
    return filters;
  }
}
