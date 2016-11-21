package com.ov.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ov.beans.Message;
import com.ov.beans.Setting;
import com.ov.controller.base.BaseController;
import com.ov.entity.DeviceInfo;
import com.ov.entity.Motorcade;
import com.ov.entity.Vehicle;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.json.response.VehicleDailyReport;
import com.ov.json.response.VehicleStatus;
import com.ov.service.MotorcadeService;
import com.ov.service.TenantAccountService;
import com.ov.service.VehicleService;
import com.ov.utils.ApiUtils;
import com.ov.utils.FieldFilterUtils;
import com.ov.utils.LatLonUtil;
import com.ov.utils.SettingUtils;

/**
 * Controller - 车辆
 * 
 * @author luzhang
 *
 */
@Controller("vehicleController")
@RequestMapping("/console/vehicle")
public class VehicleController extends BaseController {

  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  @Resource(name = "motorcadeServiceImpl")
  private MotorcadeService motorcadeService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  private Setting setting = SettingUtils.get();

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/vehicle", method = RequestMethod.GET)
  public String vehicle(ModelMap model) {
    return "/vehicle/vehicle";
  }

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Vehicle> list(String vehicleFullBrandSearch, String motorcadeSearch,
      String vehiclePlateSearch, Pageable pageable) {
    Page<Vehicle> vehiclePage = null;
    if (vehicleFullBrandSearch == null && vehiclePlateSearch == null && motorcadeSearch == null) {
      vehiclePage = vehicleService.findPage(pageable, true);
    } else {
      vehiclePage =
          vehicleService.searchPageByFilter(vehiclePlateSearch, motorcadeSearch,
              vehicleFullBrandSearch, pageable);
    }
    return vehiclePage;
  }

  @RequestMapping(value = "/listUnBuindVehicle", method = RequestMethod.POST)
  public @ResponseBody Page<Vehicle> listUnBuindVehicle(String vehicleFullBrandSearch,
      String motorcadeSearch, String vehiclePlateSearch, Pageable pageable) {

    return vehicleService.listUnBuindVehicle(vehiclePlateSearch, motorcadeSearch,
        vehicleFullBrandSearch, pageable);
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, vehicleService.findAll(true));
  }


  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id, String handle) {
    if (id != null && handle != null) {
      model.addAttribute("vehicle", vehicleService.find(id));
      return "vehicle/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param vehicle
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(Vehicle vehicle, Long motorcadeId) {

    Motorcade motorcade = motorcadeService.find(motorcadeId);
    vehicle.setMotorcade(motorcade);
    vehicle.setTenantInfo(tenantAccountService.getCurrentTenantInfo());
    vehicleService.save(vehicle, true);
    return SUCCESS_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param vehicle
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Vehicle vehicle, Long vehicleMotorcadeId) {
    Motorcade motorcade = motorcadeService.find(vehicleMotorcadeId);
    vehicle.setMotorcade(motorcade);
    vehicleService.update(vehicle, "vehicleStatus", "oilType", "createDate", "tenantID",
        "tenantInfo");
    return SUCCESS_MESSAGE;
  }



  /**
   * 删除
   * 
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        Vehicle vehicle = vehicleService.find(id);
        if (vehicle != null) {
          vehicleService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 查询车辆
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/commonVehiclesSearch", method = RequestMethod.GET)
  public String commonVehiclesSearch(ModelMap model) {
    return "/common/commonVehiclesSearch";
  }

  /**
   * 查询车辆实时数据
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/vehicleDailyReport", method = RequestMethod.GET)
  public String getVehicleDailyReport(ModelMap model, Long vehicleId) {
    VehicleDailyReport report = vehicleService.callVehicleDailyData(new Date(), vehicleId);
    model.put("vehicleDailyReport", report);
    return "vehicle/vehicleDailyReport";
  }

  @RequestMapping(value = "/getVehicleDailyData", method = RequestMethod.POST)
  public @ResponseBody VehicleDailyReport getVehicleDailyData(ModelMap model, Date date,
      Long vehicleId) {

    return vehicleService.callVehicleDailyData(date, vehicleId);

  }

  @RequestMapping(value = "/allVehicleStatus", method = RequestMethod.GET)
  public String allVehicleStatus(ModelMap model) {
    model.put("tenantInfo", tenantAccountService.getCurrentTenantInfo());
    return "vehicle/allVehicleStatus";
  }

  @RequestMapping(value = "/allVehicleStatus", method = RequestMethod.POST)
  public @ResponseBody List<Vehicle> allVehicleStatus() {
    List<Filter> filters = new ArrayList<Filter>();
    Filter exceptFilter = new Filter("plate", Operator.ne, "0000000");
    filters.add(exceptFilter);

    List<Vehicle> vehicleList =
        prepareVehicleList(vehicleService.findList(null, filters, null, true, null));
    List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
    for (Vehicle vehicle : vehicleList) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("lon", vehicle.getLon());
      map.put("lat", vehicle.getLat());
      map.put("vehicleId", vehicle.getId());
      mapList.add(map);
    }
    List<Map<String, Object>> newMapList = LatLonUtil.convertCoordinates(mapList);
    for (int i = 0; i < newMapList.size(); i++) {
      for (int j = 0; j < vehicleList.size(); j++) {
        if (vehicleList.get(j).getId().equals(newMapList.get(i).get("vehicleId"))) {
          vehicleList.get(j).setLon(Float.parseFloat(newMapList.get(i).get("x").toString()));
          vehicleList.get(j).setLat(Float.parseFloat(newMapList.get(i).get("y").toString()));
          break;
        }
      }

    }
    return vehicleList;
  }


  private List<Vehicle> prepareVehicleList(List<Vehicle> vehicleList) {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
    for (Vehicle vehicle : vehicleList) {
      DeviceInfo deviceInfo = vehicle.getDevice();
      if (deviceInfo != null) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deviceId", deviceInfo.getDeviceNo());
        map.put("rowId", deviceInfo.getId());
        paramList.add(map);
      }

    }
    try {
      String params = objectMapper.writeValueAsString(paramList);

      String response =
          ApiUtils.postJson(setting.getObdServerUrl()
              + "tenantVehicleData/vehicleOnlineStatus.jhtml", "UTF-8", "UTF-8", params);
      // String response =
      // "{\"msg\": [{\"deviceId\": \"1\",\"rowId\": \"1\",\"mileage\": 100,\"online\": true,\"remaininggas\": 10, \"bv\": 12.5},{\"deviceId\": \"2\",\"rowId\": \"1\",\"mileage\": 20,\"online\": true,\"remaininggas\": 20,\"bv\": 10.5}]}";
      if (response != null && !response.equals("")) {
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode msgNode = rootNode.path("msg");
        String msg = objectMapper.writeValueAsString(msgNode);
        List<VehicleStatus> vehicleStatusList =
            objectMapper.readValue(msg, new TypeReference<List<VehicleStatus>>() {});
        for (Vehicle vehicle : vehicleList) {
          for (VehicleStatus vehicleStatus : vehicleStatusList) {
            if (vehicle.getDevice() != null
                && vehicle.getDevice().getId().toString().equals(vehicleStatus.getRowId())) {
              vehicle.setDashboardBV(vehicleStatus.getBv());
              if (vehicleStatus.getMileage() != null && vehicleStatus.getMileage() != 0) {
                vehicle.setDashboardMileage(vehicleStatus.getMileage());
              } else {
                if (vehicle.getDriveMileage() != null) {
                  vehicle.setDashboardMileage(vehicleStatus.getGpsMileage()
                      + vehicle.getDriveMileage());
                } else {
                  vehicle.setDashboardMileage(vehicleStatus.getGpsMileage());
                }

              }
              vehicle.setDashboradOil(vehicleStatus.getRemaininggas());
              vehicle.setIsOnline(vehicleStatus.getOnline());
              vehicle.setLat(vehicleStatus.getLat());
              vehicle.setLon(vehicleStatus.getLon());
              vehicle.setObdStatusTime(vehicleStatus.getCreatetime());
              // 解析故障码
              if (vehicleStatus.getFaultcode() != null) {
                String[] faultCodes = vehicleStatus.getFaultcode().split(",");
                Set<String> faultCodeSet = new HashSet<String>();
                for (String faultCode : faultCodes) {
                  String code = faultCode.split(":")[0].trim();
                  if (!faultCodeSet.contains(code)) {
                    faultCodeSet.add(code);
                  }
                }
                vehicle.setFaultCodeSet(faultCodeSet);
              }

            }
          }
        }
      }
    }

    catch (Exception e) {
      e.printStackTrace();
    }
    return vehicleList;
  }

}
