package com.ov.controller;

import java.util.ArrayList;
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
import com.ov.json.response.VehicleTrack;
import com.ov.service.VehicleService;
import com.ov.utils.ApiUtils;
import com.ov.utils.DateTimeUtils;
import com.ov.utils.FieldFilterUtils;
import com.ov.utils.LatLonUtil;
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
      return maps;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  /**
   * 一天内，多条轨迹片段
   * @param model
   * @param vehicleID
   * @param searchDate
   * @return
   */
  @RequestMapping(value = "/drawVehicleTrackMultiple", method = RequestMethod.POST)
  @SuppressWarnings("unchecked")
  public @ResponseBody List<Map<String, Object>> multipleVehicleTrack(Model model, Long vehicleID,
      Date searchDate) {
    if (vehicleID == null || searchDate == null) {
      return null;
    }
    List<Map<String, Object>> responseTrackList = new ArrayList<Map<String, Object>>();
    Vehicle vehicle = vehicleService.find(vehicleID);
    String deviceNo = vehicle.getDeviceNo();
    String date = DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, searchDate);
    Setting set = SettingUtils.get();
    String url =
        set.getObdServerUrl() + "/tenantVehicleData/vehicleTrackFragment.jhtml?date=" + date
            + "&deviceId=" + deviceNo;
    String res = ApiUtils.post(url);

    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(res, Map.class);
      List<Map<String, Object>> trackMapList = (List<Map<String, Object>>) map.get("msg");
      String value = mapper.writeValueAsString(trackMapList);


      List<VehicleTrack> trackList =
          mapper.readValue(value,
              mapper.getTypeFactory().constructParametricType(List.class, VehicleTrack.class));
      for (VehicleTrack vehicleTrack : trackList) {
        if (vehicleTrack.getTracks() != null && vehicleTrack.getTracks().size() > 0) {
          Map<String, Object> startPoint = vehicleTrack.getTracks().get(0);
          Map<String, Object> endPoint =
              vehicleTrack.getTracks().get(vehicleTrack.getTracks().size() - 1);
          String startAddr =
              LatLonUtil.convertCoorForAddr(startPoint.get("lat").toString(), startPoint.get("lon")
                  .toString());
          String endAddr =
              LatLonUtil.convertCoorForAddr(endPoint.get("lat").toString(), endPoint.get("lon")
                  .toString());
          vehicleTrack.setStartAddr(startAddr);
          vehicleTrack.setEndAddr(endAddr);

          /*
           * gps坐标转为百度坐标
           */
          List<Map<String, Object>> convertMaps =
              LatLonUtil.convertCoordinates(vehicleTrack.getTracks());
          vehicleTrack.setTracks(convertMaps);

          vehicleTrack.setPlate(vehicle.getPlate());
        }

      }
      String[] properties =
          {"from", "to", "plate", "mileage", "runTime", "tracks", "endAddr", "startAddr"};
      responseTrackList = FieldFilterUtils.filterCollectionMap(properties, trackList);
      return responseTrackList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  /**
   * 一段时间内(跨度小于5天),完整的轨迹
   * @param model
   * @param vehicleID
   * @param searchDate
   * @return
   */
  @RequestMapping(value = "/drawVehicleTrackByTime", method = RequestMethod.POST)
  @SuppressWarnings("unchecked")
  public @ResponseBody List<Map<String, Object>> drawVehicleTrackByTime(Model model, Long vehicleID,
      Date startDate, Date endDate) {
    if (vehicleID == null || startDate == null || endDate == null) {
      return null;
    }
    if ((endDate.getTime() - startDate.getTime()) > (5* 24 * 60 * 60 * 1000)) {
      return null;
    }
    List<Map<String, Object>> responseTrackList = new ArrayList<Map<String, Object>>();
    Vehicle vehicle = vehicleService.find(vehicleID);
    String deviceNo = vehicle.getDeviceNo();
    String startDateStr = DateTimeUtils.getSimpleFormatString(DateTimeUtils.filePostfixFormat, startDate);
    String endDateStr = DateTimeUtils.getSimpleFormatString(DateTimeUtils.filePostfixFormat, endDate);
    Setting set = SettingUtils.get();
    String url =
        set.getObdServerUrl() + "/tenantVehicleData/vehicleTrackByTime.jhtml?startDate=" 
            + startDateStr + "&endDate=" + endDateStr + "&deviceId=" + deviceNo;
    String res = ApiUtils.post(url);

    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(res, Map.class);
      List<Map<String, Object>> trackMapList = (List<Map<String, Object>>) map.get("msg");
      String value = mapper.writeValueAsString(trackMapList);


      List<VehicleTrack> trackList =
          mapper.readValue(value,
              mapper.getTypeFactory().constructParametricType(List.class, VehicleTrack.class));
      for (VehicleTrack vehicleTrack : trackList) {
        if (vehicleTrack.getTracks() != null && vehicleTrack.getTracks().size() > 0) {
          Map<String, Object> startPoint = vehicleTrack.getTracks().get(0);
          Map<String, Object> endPoint =
              vehicleTrack.getTracks().get(vehicleTrack.getTracks().size() - 1);
          String startAddr =
              LatLonUtil.convertCoorForAddr(startPoint.get("lat").toString(), startPoint.get("lon")
                  .toString());
          String endAddr =
              LatLonUtil.convertCoorForAddr(endPoint.get("lat").toString(), endPoint.get("lon")
                  .toString());
          vehicleTrack.setStartAddr(startAddr);
          vehicleTrack.setEndAddr(endAddr);

          /*
           * gps坐标转为百度坐标
           */
          List<Map<String, Object>> convertMaps =
              LatLonUtil.convertCoordinates(vehicleTrack.getTracks());
          vehicleTrack.setTracks(convertMaps);

          vehicleTrack.setPlate(vehicle.getPlate());
        }

      }
      String[] properties =
          {"from", "to", "plate", "mileage", "runTime", "tracks", "endAddr", "startAddr"};
      responseTrackList = FieldFilterUtils.filterCollectionMap(properties, trackList);
      return responseTrackList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }
}
