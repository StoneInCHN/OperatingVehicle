package com.ov.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.DeviceType;
import com.ov.framework.paging.Pageable;
import com.ov.service.DeviceTypeService;

@RequestMapping("console/deviceType")
@Controller("deviceTypeController")
public class DeviceTypeController extends BaseController {

  @Resource(name = "deviceTypeServiceImpl")
  private DeviceTypeService deviceTypeService;


  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/deviceType/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(DeviceType deviceType) {
    if (!isValid(deviceType)) {
      return ERROR_VIEW;
    }
    deviceTypeService.save(deviceType);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("deviceType", deviceTypeService.find(id));
    return "/deviceType/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(DeviceType deviceType) {
    if (!isValid(deviceType)) {
      return ERROR_VIEW;
    }
    DeviceType temp = deviceTypeService.find(deviceType.getId());
    temp.setName(deviceType.getName());
    temp.setStatus(deviceType.getStatus());
    deviceTypeService.update(temp);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", deviceTypeService.findPage(pageable));
    return "/deviceType/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      deviceTypeService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
