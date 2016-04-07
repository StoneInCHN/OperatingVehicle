package com.ov.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.UpkeepCharge;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.DepartmentService;
import com.ov.service.UpkeepChargeService;

/**
 * Controller - 车辆维修
 * 
 * @author luzhang
 *
 */
@Controller ("UpkeepChargeController")
@RequestMapping ("console/upkeepCharge")
public class UpkeepChargeController extends BaseController{
  
  @Resource (name = "departmentServiceImpl")
  private DepartmentService departmentService;

  @Resource (name = "upkeepChargeServiceImpl")
  private UpkeepChargeService upkeepChargeService;

  @RequestMapping (value = "/upkeepCharge", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "upkeepCharge/upkeepCharge";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<UpkeepCharge> list (Pageable pageable){
      return upkeepChargeService.findPage (pageable);
  }


  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id){
    model.addAttribute ("upkeepCharge", upkeepChargeService.find (id));
    return "upkeepCharge/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (UpkeepCharge upkeepCharge)
  {
    if(upkeepCharge.getUpkeepAmount() !=null){
     upkeepChargeService.save(upkeepCharge, true);
     return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (UpkeepCharge upkeepCharge)
  {
    upkeepChargeService.update (upkeepCharge,"tenantID");
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
      upkeepChargeService.delete (ids);
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

    model.addAttribute ("upkeepCharge", upkeepChargeService.find (id));
    return "upkeepCharge/details";
  }
  
}
