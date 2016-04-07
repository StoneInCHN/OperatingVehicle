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

/**
 * 设备管理
 * @author huyong
 *
 */
@Controller ("deviceTypeController")
@RequestMapping ("console/deviceType")
public class DeviceTypeController extends BaseController
{

  @Resource (name = "deviceTypeServiceImpl")
  private DeviceTypeService deviceTypeService;
  
  @RequestMapping (value = "/findAllDeviceType", method = RequestMethod.GET)
  public @ResponseBody List<Map<String, Object>> findAllDeviceType (ModelMap model)
  {
     return deviceTypeService.findAllDeviceType ();
  }
 
}
