package com.ov.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.Department;
import com.ov.service.DepartmentService;
import com.ov.service.TenantInfoService;

/**
 * 部门管理
 * 
 */
@Controller("departmentController")
@RequestMapping("console/department")
public class DepartmentController extends BaseController {

  @Resource(name = "departmentServiceImpl")
  private DepartmentService departmentService;

  @RequestMapping(value = "/department", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "department/department";
  }
  
  @Autowired
  private TenantInfoService tenantInfoService;
  
  /**
   * 部门列表
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public @ResponseBody List<Department> list() {
    return departmentService.findRoots();
  }
  /**
   * 部门列表（根据租户）
   * @return
   */
  @RequestMapping(value = "/listByTenantID", method = RequestMethod.GET)
  public @ResponseBody List<Department> listByTenantID(Long tenantID) {
    if (tenantID != null && tenantInfoService.find(tenantID) != null) {
      return departmentService.findRoots(true, tenantID);
    }
    return null;
  }
  /**
   * 编辑部门页面
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("department", departmentService.find(id));
    return "department/edit";
  }
  /**
   * 添加部门
   * @param parentId
   * @param department
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long tenantID, Long parentId, Department department) {

    if (parentId != null) {
      Department parent = departmentService.find(parentId);
      department.setParent(parent);
      Integer grade = parent.getGrade();
      if (grade == 0) {
        grade = 1;
      }
      department.setGrade(++grade);
    } else {
      department.setGrade(1);
    }
    department.setTenantID(tenantID);
    departmentService.save(department, false);;
    return SUCCESS_MESSAGE;
  }
  /**
   * 更新部门
   * @param department
   * @param parentId
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Department department, Long parentId) {
    if (parentId != null) {
      Department parent = departmentService.find(parentId);
      department.setParent(parent);
      department.setGrade(parent.getGrade() + 1);
    }
    departmentService.update(department,"tenantID");
    return SUCCESS_MESSAGE;
  }


  /**
   * 删除部门
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      departmentService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 部门详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {

    model.addAttribute("department", departmentService.find(id));
    return "department/details";
  }
  /**
   * 查找所有的部门
   * @return
   */
  @RequestMapping(value = "/findAllDepartments", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAllDepartments() {
    return departmentService.findAllDepartments();
  }

}
