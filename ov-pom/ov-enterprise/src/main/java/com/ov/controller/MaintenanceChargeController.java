package com.ov.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.MaintenanceCharge;
import com.ov.entity.Vehicle;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.DepartmentService;
import com.ov.service.MaintenanceChargeService;
import com.ov.service.VehicleService;

/**
 * Controller - 车辆保养
 * 
 * @author luzhang
 *
 */
@Controller ("MaintenanceChargeController")
@RequestMapping ("console/maintenanceCharge")
public class MaintenanceChargeController extends BaseController{
  
  @Resource (name = "departmentServiceImpl")
  private DepartmentService departmentService;
  
  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  @Resource (name = "maintenanceChargeServiceImpl")
  private MaintenanceChargeService maintenanceChargeService;

  @RequestMapping (value = "/maintenanceCharge", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "maintenanceCharge/maintenanceCharge";
  }
  
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<MaintenanceCharge> list (String vehiclePlateSearch, Date beginDate, Date endDate, Pageable pageable){
    if(vehiclePlateSearch != null || beginDate != null || endDate != null){
      return maintenanceChargeService.findPageByFilter(vehiclePlateSearch, beginDate, endDate, pageable, true);
    }else {
      return maintenanceChargeService.findPage (pageable, true);
    }
  }


  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id){
    model.addAttribute ("maintenanceCharge", maintenanceChargeService.find (id));
    return "maintenanceCharge/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (MaintenanceCharge maintenanceCharge)
  {
    if(maintenanceCharge.getVehicleID() !=null){
      Vehicle vehicle = vehicleService.find(maintenanceCharge.getVehicleID());
      maintenanceCharge.setVehicle(vehicle);
     maintenanceChargeService.save(maintenanceCharge, true);
     return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (MaintenanceCharge maintenanceCharge)
  {
    if(maintenanceCharge.getId() != null && maintenanceCharge.getVehicleID() !=null){
      Vehicle vehicle = vehicleService.find(maintenanceCharge.getVehicleID());
      maintenanceCharge.setVehicle(vehicle);
      maintenanceChargeService.update (maintenanceCharge,"tenantID");
      return SUCCESS_MESSAGE;
    }
    return ERROR_MESSAGE;
  }


  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long[] ids)
  {
    if (ids != null)
    {
      // 检查是否能被删除
      // if()
      maintenanceChargeService.delete (ids);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {

    model.addAttribute ("maintenanceCharge", maintenanceChargeService.find (id));
    return "maintenanceCharge/details";
  }
  
}
