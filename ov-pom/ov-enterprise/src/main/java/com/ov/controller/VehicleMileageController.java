package com.ov.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.VehicleMileage;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.DepartmentService;
import com.ov.service.VehicleMileageService;

/**
 * Controller - 车辆行程
 * 
 * @author luzhang
 *
 */
@Controller ("VehicleMileageController")
@RequestMapping ("console/vehicleMileage")
public class VehicleMileageController extends BaseController{
  
  @Resource (name = "departmentServiceImpl")
  private DepartmentService departmentService;

  @Resource (name = "vehicleMileageServiceImpl")
  private VehicleMileageService vehicleMileageService;

  @RequestMapping (value = "/vehicleMileage", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "vehicleMileage/vehicleMileage";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<VehicleMileage> list (Pageable pageable){
      return vehicleMileageService.findPage (pageable);
  }


  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id){
    model.addAttribute ("vehicleMileage", vehicleMileageService.find (id));
    return "vehicleMileage/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (VehicleMileage vehicleMileage)
  {
    if(vehicleMileage.getMileage() !=null){
     vehicleMileageService.save(vehicleMileage, true);
     return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (VehicleMileage vehicleMileage)
  {
    vehicleMileageService.update (vehicleMileage,"tenantID");
    return SUCCESS_MESSAGE;
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
      vehicleMileageService.delete (ids);
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

    model.addAttribute ("vehicleMileage", vehicleMileageService.find (id));
    return "vehicleMileage/details";
  }
  
}
