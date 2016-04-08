package com.ov.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.OilCharge;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.DepartmentService;
import com.ov.service.OilChargeService;

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

  @RequestMapping (value = "/oilCharge", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "oilCharge/oilCharge";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<OilCharge> list (Pageable pageable){
      return oilChargeService.findPage (pageable);
  }


  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id){
    model.addAttribute ("oilCharge", oilChargeService.find (id));
    return "oilCharge/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (OilCharge oilCharge)
  {
    if(oilCharge.getOilAmount() !=null){
     oilChargeService.save(oilCharge, true);
     return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (OilCharge oilCharge)
  {
    oilChargeService.update (oilCharge,"tenantID");
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
