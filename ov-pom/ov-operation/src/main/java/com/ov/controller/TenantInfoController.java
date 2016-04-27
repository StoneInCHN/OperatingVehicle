package com.ov.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.Admin;
import com.ov.entity.TenantInfo;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Pageable;
import com.ov.service.AdminService;
import com.ov.service.AreaService;
import com.ov.service.TenantInfoService;
import com.ov.service.VersionConfigService;

@Controller("tenantInfoController")
@RequestMapping("console/tenantInfo")
public class TenantInfoController extends BaseController {

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

//  @Resource(name = "identifierServiceImpl")
//  private IdentifierService identifierService;
  
  @Resource(name = "areaServiceImpl")
  private AreaService areaService;
  
  @Resource(name = "versionConfigServiceImpl")
  private VersionConfigService versionConfigService;
  
  @Resource(name = "adminServiceImpl")
  private AdminService adminService;
  
  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    model.addAttribute("versions", versionConfigService.findAll());
    return "/tenantInfo/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(TenantInfo tenantInfo, Long areaId ,Long versionConfigId) {
    if (!isValid(tenantInfo)) {
      return ERROR_VIEW;
    }
//    tenantInfo.setOrgCode(identifierService.getLatestOrgCode());
//    tenantInfo.setIsHaveAccount(false);
//    tenantInfo.setArea(areaService.find(areaId));
    tenantInfo.setVersionConfig(versionConfigService.find(versionConfigId));
    tenantInfoService.save(tenantInfo);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("tenantInfo", tenantInfoService.find(id));
    return "/tenantInfo/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(TenantInfo tenantInfo) {
    if (!isValid(tenantInfo)) {
      return ERROR_VIEW;
    }
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", tenantInfoService.findPage(pageable));
    return "/tenantInfo/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      tenantInfoService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 列表
   */
  @RequestMapping(value = "/list4distributor", method = RequestMethod.GET)
  public String list4distributor(Pageable pageable, ModelMap model) {
    Admin admin = adminService.getCurrent();
    List<Filter> filters = new ArrayList<Filter>();
//    if (admin.getIsDistributor() != null && admin.getIsDistributor()
//        && admin.getDistributor() != null) {
//      Filter distributorFilter = new Filter();
//      distributorFilter.setProperty("distributor");
//      distributorFilter.setValue(admin.getDistributor().getId());
//      distributorFilter.setOperator(Operator.eq);
//      filters.add(distributorFilter);
//    }
    pageable.setFilters(filters);
    model.addAttribute("page", tenantInfoService.findPage(pageable));
    return "/tenantInfo/list4distributor";
  }

}
