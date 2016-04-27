package com.ov.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.TenantAccount;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Pageable;
import com.ov.service.RoleService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;

@RequestMapping("console/tenantAccount")
@Controller("tenantAccountController")
public class TenantAccountController extends BaseController {

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;
  
  @Resource(name="roleServiceImpl")
  private RoleService roleService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter();
    filter.setProperty("isHaveAccount");
    filter.setValue(false);
    filter.setOperator(Operator.eq);
    filters.add(filter);
    model.addAttribute("tenantInfos", tenantInfoService.findList(null, filters, null));
    return "/tenantAccount/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(TenantAccount tenantAccount) {
    tenantAccountService.save(tenantAccount);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    TenantAccount tenantAccount = tenantAccountService.find(id);
    model.addAttribute("tenantAccount", tenantAccount);
    /*List<Filter> filters = new ArrayList<Filter>();
    Filter tenantIDFilter = new Filter();
    tenantIDFilter.setProperty("tenantID");
    tenantIDFilter.setValue(tenantAccount.getTenantID());
    tenantIDFilter.setOperator(Operator.eq);
    filters.add(tenantIDFilter);
    Filter typeFilter = new Filter();
    typeFilter.setProperty("systemType");
    typeFilter.setValue(SystemType.ENTERPRISE);
    typeFilter.setOperator(Operator.eq);
    filters.add(typeFilter);
    model.addAttribute("roles", roleService.findList(null, filters, null));*/
    return "/tenantAccount/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(TenantAccount tenantAccount) {
    TenantAccount temp = tenantAccountService.find(tenantAccount.getId());
    temp.setAccoutStatus(tenantAccount.getAccoutStatus());
    tenantAccountService.update(temp);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", tenantAccountService.findPage(pageable));
    return "/tenantAccount/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      tenantAccountService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 检查用户名是否存在
   */
  @RequestMapping(value = "/check_userName", method = RequestMethod.GET)
  public @ResponseBody boolean checkUsername(String userName) {
    if (StringUtils.isEmpty(userName)) {
      return false;
    }
    if (tenantAccountService.usernameExists(userName)) {
      return false;
    } else {
      return true;
    }
  }

}
