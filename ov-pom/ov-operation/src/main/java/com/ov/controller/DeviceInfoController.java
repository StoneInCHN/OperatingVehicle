package com.ov.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.common.log.LogUtil;
import com.ov.controller.base.BaseController;
import com.ov.entity.Admin;
import com.ov.entity.DeviceInfo;
import com.ov.entity.DeviceType;
//import com.ov.entity.Distributor;
import com.ov.entity.commonenum.CommonEnum.BindStatus;
import com.ov.entity.commonenum.CommonEnum.DeviceStatus;
import com.ov.entity.commonenum.CommonEnum.Status;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Pageable;
import com.ov.service.AdminService;
import com.ov.service.DeviceInfoService;
import com.ov.service.DeviceTypeService;
//import com.ov.service.DistributorService;
import com.ov.service.TenantInfoService;
import com.ov.utils.ExcelUtils;

@RequestMapping("console/deviceInfo")
@Controller("deviceInfoController")
public class DeviceInfoController extends BaseController {

  @Resource(name = "deviceTypeServiceImpl")
  private DeviceTypeService deviceTypeService;

  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;

//  @Resource(name = "distributorServiceImpl")
//  private DistributorService distributorService;

  @Resource(name = "adminServiceImpl")
  private AdminService adminService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap modelMap) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter();
    filter.setProperty("status");
    filter.setValue(Status.ENABLE);
    filter.setOperator(Operator.eq);
    filters.add(filter);
    modelMap.addAttribute("types", deviceTypeService.findList(null, filters, null));
    return "/deviceInfo/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(DeviceInfo deviceInfo, Long typeId) {
    if (!isValid(deviceInfo)) {
      return ERROR_VIEW;
    }
    DeviceType type = deviceTypeService.find(typeId);
    deviceInfo.setType(type);
    deviceInfo.setDeviceStatus(DeviceStatus.INITED);
    deviceInfo.setBindStatus(BindStatus.UNBINDED);
    deviceInfoService.save(deviceInfo);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("deviceInfo", deviceInfoService.find(id));
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter();
    filter.setProperty("status");
    filter.setValue(Status.ENABLE);
    filter.setOperator(Operator.eq);
    filters.add(filter);
    model.addAttribute("types", deviceTypeService.findList(null, filters, null));
    return "/deviceInfo/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(DeviceInfo deviceInfo, Long typeId) {
    DeviceInfo info = deviceInfoService.find(deviceInfo.getId());
    DeviceType type = deviceTypeService.find(typeId);
    info.setType(type);
    info.setSimNo(deviceInfo.getSimNo());
    deviceInfoService.update(info);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model, DeviceStatus deviceStatus) {
    model.addAttribute("deviceStatus", deviceStatus);
    if (deviceStatus != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter();
      filter.setProperty("deviceStatus");
      filter.setValue(deviceStatus);
      filter.setOperator(Operator.eq);
      filters.add(filter);
      pageable.setFilters(filters);
    }
    model.addAttribute("page", deviceInfoService.findPage(pageable));
    return "/deviceInfo/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      deviceInfoService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/tenantInfoPage", method = RequestMethod.GET)
  public String tenantInfoPage(Pageable pageable, ModelMap model) {
    pageable.setPageSize(5);;
    model.addAttribute("page", tenantInfoService.findPage(pageable));
    return "/deviceInfo/tenantInfoPage";
  }


  @RequestMapping(value = "/deviceProvide", method = RequestMethod.GET)
  public String deviceProvide(ModelMap modelMap, Pageable pageable) {
    pageable.setPageSize(5);;
//    modelMap.addAttribute("page", distributorService.findPage(pageable));
    return "/deviceInfo/deviceProvide";
  }

  @RequestMapping(value = "/provideDevice", method = RequestMethod.POST)
  public @ResponseBody Message provideDevice(String ids, Long[] tenantInfoIds) {
    if (tenantInfoIds.length != 1) {
      return ERROR_MESSAGE;
    }
    String[] deviceIds = ids.split("&ids=");
    Long tenantInfoId = tenantInfoIds[0];
    for (String string : deviceIds) {
      if (string.length() > 0) {
        Long id = Long.valueOf(string);
        DeviceInfo deviceInfo = deviceInfoService.find(id);
        if (DeviceStatus.INITED.equals(deviceInfo.getDeviceStatus())) {
          deviceInfo.setTenantID(tenantInfoId);
          deviceInfo.setDeviceStatus(DeviceStatus.STORAGEOUT);
          deviceInfoService.update(deviceInfo);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }

//  @RequestMapping(value = "/provide", method = RequestMethod.POST)
//  public @ResponseBody Message provide(String ids, Long[] distributorIds) {
//    if (distributorIds.length != 1) {
//      return ERROR_MESSAGE;
//    }
//    String[] deviceIds = ids.split("&ids=");
//    Long distributorId = distributorIds[0];
//    for (String string : deviceIds) {
//      if (string.length() > 0) {
//        Long id = Long.valueOf(string);
//        DeviceInfo deviceInfo = deviceInfoService.find(id);
//        if (DeviceStatus.INITED.equals(deviceInfo.getDeviceStatus())){
////          Distributor distributor = distributorService.find(distributorId);
////          deviceInfo.setDistributor(distributor);
//          deviceInfo.setDeviceStatus(DeviceStatus.SENDOUT);
//          deviceInfoService.update(deviceInfo);
//        }
//        
//      }
//    }
//    return SUCCESS_MESSAGE;
//  }

  
  /**
   * 详情
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("deviceInfo", deviceInfoService.find(id));
    return "/deviceInfo/details";
  }


  /**
   * 跳转到批量上传页面
   * 
   */
  @RequestMapping(value = "/batchAdd", method = RequestMethod.GET)
  public String batchAdd(DeviceInfo device, ModelMap model) {
    return "/deviceInfo/batch_add";
  }

  /**
   * 批量上传
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/batchAddSave", method = RequestMethod.POST)
  public String batchAddSave(DeviceInfo device, ModelMap model, HttpSession session) {
    Message message = new Message();
    int successCount = 0;// 成功条数
    int faileCount = 0;// 失败条数
    List<String> dupfailDeviceIds = new ArrayList<String>();
    List<String> formatFailDeviceIds = new ArrayList<String>();
    try {
      String fileSuffix = device.getFile().getOriginalFilename().split("\\.")[1];
      List<DeviceInfo> devices = null;

      if (fileSuffix.toUpperCase().equals("XLS")) {
        devices = ExcelUtils.processingExcel_XLS(device.getFile().getInputStream());
      } else if (fileSuffix.toUpperCase().equals("XLSX")) {
        devices = ExcelUtils.processingExcel_XLSX(device.getFile().getInputStream());
      }

      model.addAttribute("device", device);

      if (LogUtil.isDebugEnabled(DeviceInfoController.class)) {
        LogUtil.debug(DeviceInfoController.class, "batchAddSave",
            "Load data from excel successful and count is : %s", devices.size());
      }

      if (devices.size() > 3000) {
        if (LogUtil.isDebugEnabled(DeviceInfoController.class)) {
          LogUtil.debug(DeviceInfoController.class, "batchAddSave", "batchAddSave",
              "Rows exceed 3000 : %s", devices.size());
        }
        message.setType(Message.Type.error);
        message.setContent("gutid.batch.upload.err");
        model.addAttribute("resMessage", message);
        return "/deviceInfo/batch_add";
      }


      for (DeviceInfo deviceTemp : devices) {
        try {
          if (deviceTemp.getDeviceNo() != null && deviceTemp.getSimNo() != null) {
            if (deviceTemp.getDeviceNo().length() != 10) {
              formatFailDeviceIds.add(deviceTemp.getDeviceNo());
              faileCount++;
            } else {
                deviceTemp.setDeviceStatus(DeviceStatus.INITED);
                deviceTemp.setBindStatus(BindStatus.UNBINDED);
                deviceInfoService.save(deviceTemp);
                if (LogUtil.isDebugEnabled(DeviceInfoController.class)) {
                  LogUtil.debug(DeviceInfoController.class, "batchAddSave",
                      "DeviceInfo saved , Sn : %s , simNo : %s", deviceTemp.getDeviceNo(),
                      deviceTemp.getSimNo());
                }
                successCount++;
            }
          } else {
            formatFailDeviceIds.add(deviceTemp.getDeviceNo() + "\n");
            faileCount++;
          }
        } catch (Exception e) {
          faileCount++;
          dupfailDeviceIds.add(deviceTemp.getDeviceNo());
        }
      }
      if (successCount > 0) {
        message.setType(Message.Type.success);
        message.setContent(message("gutid.batch.upload.success"));
        model.addAttribute("resMessage", message);
      } else {
        message.setType(Message.Type.error);
        message.setContent("gutid.batch.upload.fail");
        model.addAttribute("resMessage", message);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("successCount", successCount);
    model.addAttribute("faileCount", faileCount);
    model.addAttribute("dupfailDeviceIds", dupfailDeviceIds);
    model.addAttribute("formatFailDeviceIds", formatFailDeviceIds);
    return "/deviceInfo/batch_add";
  }

  /**
   * 检查用户名是否存在
   */
  @RequestMapping(value = "/check_deviceNo", method = RequestMethod.GET)
  public @ResponseBody boolean checkUsername(String deviceNo,Long id) {
    if (StringUtils.isEmpty(deviceNo)) {
      return false;
    }
    DeviceInfo deviceInfo = deviceInfoService.findByDeviceNo(deviceNo);
    if (deviceInfo!=null) {
      if (id !=null && deviceInfo.getId() ==id) {
        return true;
      }else{
        return false;
      }
    } 
      return true;
  }
}
