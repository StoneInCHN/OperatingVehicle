package com.ov.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Setting;
import com.ov.controller.base.BaseController;
import com.ov.entity.Vehicle;
import com.ov.service.VehicleService;
import com.ov.utils.ApiUtils;
import com.ov.utils.DateTimeUtils;
import com.ov.utils.SettingUtils;

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
    Vehicle vehicle = vehicleService.find(vehicleID);
    String deviceNo = vehicle.getDeviceNo();
    String date = DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, searchDate);
    Setting set = SettingUtils.get();
    String url =
        set.getObdServerUrl() + "/tenantVehicleData/vehicleTrack.jhtml?date=" + date + "&deviceId="
            + deviceNo;
    String res = ApiUtils.post(url);

    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(res, Map.class);
      List<Map<String, Object>> maps = (List<Map<String, Object>>) map.get("msg");
      System.out.println(maps);
      return maps;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }


}
