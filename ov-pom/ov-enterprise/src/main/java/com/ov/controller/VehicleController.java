package com.ov.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.Vehicle;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.VehicleService;
import com.ov.service.TenantAccountService;
import com.ov.utils.FieldFilterUtils;

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

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

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
  public @ResponseBody Page<Vehicle> list(String vehicleBrandSearch, 
      String vehiclePlateSearch, Pageable pageable) {
    Page<Vehicle> vehiclePage = null;
    if (vehicleBrandSearch == null && vehiclePlateSearch == null) {
      vehiclePage = vehicleService.findPage(pageable, true);
    } else {
      
      
      vehiclePage = vehicleService.findPage(pageable, true);
//      vehiclePage = vehicleService.searchPageByFilter(keysOfElderlyName, beginDate, endDate,pageable);
    }
    return vehiclePage;
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
  public @ResponseBody Message save(Vehicle vehicle, Long elderlyInfoID) {
    
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
  public @ResponseBody Message update(Vehicle vehicle, Long elderlyInfoID) {
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
}
