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
import com.ov.entity.OilCharge;
import com.ov.entity.Vehicle;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.DepartmentService;
import com.ov.service.OilChargeService;
import com.ov.service.VehicleService;

/**
 * Controller - 车辆加油
 * 
 * @author luzhang
 *
 */
@Controller ("OilChargeController")
@RequestMapping ("console/oilCharge")
public class OilChargeController extends BaseController{
  
  @Resource (name = "departmentServiceImpl")
  private DepartmentService departmentService;

  @Resource (name = "oilChargeServiceImpl")
  private OilChargeService oilChargeService;
  
  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  @RequestMapping (value = "/oilCharge", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "oilCharge/oilCharge";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<OilCharge> list (String vehiclePlateSearch, Date beginDate, Date endDate,Pageable pageable){
    if(vehiclePlateSearch != null || beginDate != null || endDate != null){
      return oilChargeService.findPageByFilter(vehiclePlateSearch, beginDate, endDate, pageable, true);
    }else {
      return oilChargeService.findPage (pageable, true);
    }
  }


  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id){
    model.addAttribute ("oilCharge", oilChargeService.find (id));
    return "oilCharge/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (OilCharge oilCharge)
  {
    if(oilCharge.getVehicleID() !=null){
      Vehicle vehicle = vehicleService.find(oilCharge.getVehicleID());
      oilCharge.setVehicle(vehicle);
      if (oilCharge.getInvoiceNumber() != null) {
        oilCharge.setProvideInvoice(true);
      }
      oilChargeService.save(oilCharge, true);
     return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (OilCharge oilCharge)
  {
    if(oilCharge.getId() != null && oilCharge.getVehicleID() !=null){
      Vehicle vehicle = vehicleService.find(oilCharge.getVehicleID());
      oilCharge.setVehicle(vehicle);
      if (oilCharge.getInvoiceNumber() != null) {
        oilCharge.setProvideInvoice(true);
      }
      oilChargeService.update (oilCharge,"tenantID");
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
      oilChargeService.delete (ids);
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

    model.addAttribute ("oilCharge", oilChargeService.find (id));
    return "oilCharge/details";
  }
  
}
