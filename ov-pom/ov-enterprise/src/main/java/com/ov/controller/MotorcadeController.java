package com.ov.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.controller.base.BaseController;
import com.ov.service.DeviceTypeService;
import com.ov.service.MotorcadeService;

/**
 * 设备管理
 * @author huyong
 *
 */
@Controller ("motorcadeController")
@RequestMapping ("console/motorcade")
public class MotorcadeController extends BaseController
{

  @Resource (name = "motorcadeServiceImpl")
  private MotorcadeService motorcadeService;
  
  @RequestMapping (value = "/findAllMotorcadeUnderTenant", method = RequestMethod.GET)
  public @ResponseBody List<Map<String, Object>> findAllMotorcadeUnderTenant (ModelMap model)
  {
     return motorcadeService.findAllMotorcadeUnderTenant ();
  }
 
}
