package com.ov.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.controller.base.BaseController;
import com.ov.service.VehicleService;

/**
 * Controller - 车辆轨迹
 * 
 * @author Andrea
 *
 */
@Controller("vehicleTrackController")
@RequestMapping("console/vehicleTrack")
public class VehicleTrackController extends BaseController {


  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/track", method = RequestMethod.GET)
  public String page(ModelMap model) {
    return "/vehicle/track";
  }


  @RequestMapping(value = "/drawVehicleTrack", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> singleVehicleTrack(Model model, Long vehicleID,
      Date searchDate) {
    if (vehicleID == null || searchDate == null) {
      return null;
    }
    List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    map1.put("lat", "114.928715");
    map1.put("lng", "27.418907");
    map1.put("lat", "114.928815");
    map1.put("lng", "27.418807");
    maps.add(map1);
    maps.add(map2);
    return maps;
  }


}
