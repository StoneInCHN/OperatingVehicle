package com.ov.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ov.controller.base.BaseController;
import com.ov.framework.paging.Pageable;
import com.ov.service.VehicleService;

@RequestMapping("console/vehicle")
@Controller("vehicleController")
public class VehicleController extends BaseController {

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("vehicle", vehicleService.find(id));
    return "/vehicle/details";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", vehicleService.findPage(pageable));
    return "/vehicle/list";
  }
}
