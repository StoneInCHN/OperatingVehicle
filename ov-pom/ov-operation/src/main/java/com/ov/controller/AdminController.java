package com.ov.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.Admin;
import com.ov.entity.Admin.AdminStatus;
import com.ov.entity.Role;
import com.ov.entity.base.BaseEntity.Save;
import com.ov.entity.commonenum.CommonEnum.SystemType;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.AdminService;
import com.ov.service.RoleService;

/**
 * Controller - 管理员
 * 
 */
@Controller("adminController")
@RequestMapping("/console/admin")
public class AdminController extends BaseController {

  @Resource(name = "adminServiceImpl")
  private AdminService adminService;
  @Resource(name = "roleServiceImpl")
  private RoleService roleService;
//  @Resource(name = "distributorServiceImpl")
//  private DistributorService distributorService;

  /**
   * 检查用户名是否存在
   */
  @RequestMapping(value = "/check_username", method = RequestMethod.GET)
  public @ResponseBody boolean checkUsername(String username) {
    if (StringUtils.isEmpty(username)) {
      return false;
    }
    if (adminService.usernameExists(username)) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("systemType", SystemType.OPERATION));
    model.addAttribute("roles", roleService.findList(null, filters, null));
    model.addAttribute("adminStatusTypes", AdminStatus.values());
    return "/admin/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Admin admin, Long[] roleIds,Long distributorId) {
    admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
    if (!isValid(admin, Save.class)) {
      return ERROR_VIEW;
    }
    if (adminService.usernameExists(admin.getUsername())) {
      return ERROR_VIEW;
    }
//    if(admin.getIsDistributor()!=null&&admin.getIsDistributor() && distributorId!=null){
//     admin.setDistributor(distributorService.find(distributorId));
//    }
    admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
    admin.setIsSystem(false);
    adminService.save(admin);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("systemType", SystemType.OPERATION));
    model.addAttribute("roles", roleService.findList(null, filters, null));
    model.addAttribute("admin", adminService.find(id));
    model.addAttribute("adminStatusTypes", AdminStatus.values());
    return "/admin/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Admin admin, Long[] roleIds, RedirectAttributes redirectAttributes) {
    admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
    if (!isValid(admin)) {
      return ERROR_VIEW;
    }
    Admin pAdmin = adminService.find(admin.getId());
    if (pAdmin == null) {
      return ERROR_VIEW;
    }
    if (StringUtils.isNotEmpty(admin.getPassword())) {
      admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
    } else {
      admin.setPassword(pAdmin.getPassword());
    }
    adminService
        .update(admin, "username", "loginDate", "loginIp", "isDistributor", "distributor", "isSystem");

    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    Page<Admin> adminPage = adminService.findPage(pageable);
    model.addAttribute("page", adminPage);
    model.addAttribute("currentAdmin", adminService.getCurrent());
    model.addAttribute("adminStatusTypes", AdminStatus.values());
    return "/admin/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids.length >= adminService.count()) {
      return Message.error("admin.common.deleteAllNotAllowed");
    }
    adminService.delete(ids);
    return SUCCESS_MESSAGE;
  }

  
  @RequestMapping(value = "/selectDistributor", method = RequestMethod.GET)
  public String selectDistributor(ModelMap modelMap, Pageable pageable) {
//    pageable.setPageSize(5);;
//    modelMap.addAttribute("page", distributorService.findPage(pageable));
    return "/apply/selectDistributor";
  }
}
