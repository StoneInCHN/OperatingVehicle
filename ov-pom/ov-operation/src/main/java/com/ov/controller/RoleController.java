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
import com.ov.entity.Role;
import com.ov.entity.commonenum.CommonEnum.SystemType;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Pageable;
import com.ov.service.RoleService;

/**
 * Controller - 角色
 * 
 */
@Controller("roleController")
@RequestMapping("/console/role")
public class RoleController extends BaseController {

  @Resource(name = "roleServiceImpl")
  private RoleService roleService;

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "/role/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(Role role) {
    role.setIsSystem(false);
    role.setSystemType(SystemType.OPERATION);
    roleService.save(role);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("role", roleService.find(id));
    return "/role/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(Role role) {
    Role pRole = roleService.find(role.getId());
    if (pRole == null || pRole.getIsSystem()) {
      return ERROR_VIEW;
    }
    roleService.update(role, "isSystem", "admins", "systemType");
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    List<Filter> filters = new ArrayList<Filter>();
    filters.add(Filter.eq("systemType", SystemType.OPERATION));
    pageable.setFilters(filters);
    model.addAttribute("page", roleService.findPage(pageable));
    return "/role/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        Role role = roleService.find(id);
        if (role != null && role.getIsSystem()) {
          return Message.error("admin.role.deleteExistNotAllowed", role.getName());
        }
        //角色下有用户存在，不允许删除
        if (role.getAdmins().size() > 0) {
          return Message.error("admin.role.containAdminNotAllowed", role.getName(), role.getAdmins().size());
        }
      }
      roleService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 检查角色名是否存在
   */
  @RequestMapping(value = "/check_roleName", method = RequestMethod.GET)
  public @ResponseBody boolean checkRoleName(String name) {
    if (StringUtils.isEmpty(name)) {
      return false;
    }
    if (roleService.roleNameExists(name)) {
      return false;
    } else {
      return true;
    }
  }
}
