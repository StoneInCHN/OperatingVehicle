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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.common.log.LogUtil;
import com.ov.controller.base.BaseController;
import com.ov.entity.ConfigMeta;
import com.ov.entity.Role;
import com.ov.entity.TenantInfo;
import com.ov.entity.commonenum.CommonEnum.TreeNodeState;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.json.response.TreeNodeResponse;
import com.ov.service.ConfigMetaService;
import com.ov.service.RoleService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;

/**
 * Controller - 角色
 * 
 *
 */
@Controller("roleController")
@RequestMapping("console/role")
public class RoleController extends BaseController {
  @Resource(name = "roleServiceImpl")
  private RoleService roleService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "configMetaServiceImpl")
  private ConfigMetaService configMetaService;
  
  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  /**
   * 角色以及授权页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/role", method = RequestMethod.GET)
  public String role(ModelMap model, String path) {
    return "/role/" + path;
  }  

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Role> list(Model model, String name_roleSearch, 
      Date beginDate_roleSearch, Date endDate_roleSearch, Pageable pageable) {
    if (name_roleSearch != null || beginDate_roleSearch != null || endDate_roleSearch != null) {
      return roleService.searchByFilter(name_roleSearch, beginDate_roleSearch, endDate_roleSearch, pageable, true);
    }
    return roleService.findPage (pageable, true);
  }

  /**
   * 添加
   * 
   * @param role
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Role role) {
    if (role != null) {
      role.setTenantID(tenantAccountService.getCurrentTenantID());
      role.setIsSystem(true);
      roleService.save(role);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 查询角色
   * @param model
   * @return
   */
  @RequestMapping(value = "/commonRolesSearch", method = RequestMethod.GET)
  public String commonRolesSearch(ModelMap model) {
    return "/common/commonRolesSearch";
  }
  /**
   * 删除
   * 
   * @param id arrays
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      roleService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 获取数据进入编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    Role role = roleService.find(id);
    model.addAttribute("role", role);
    return "role/edit";
  }

  /**
   * 更新
   * 
   * @param role
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Role role) {
    roleService.update(role);
    return SUCCESS_MESSAGE;
  }

  /**
   * 权限树列表
   * 
   * @param pageable
   * @param id
   * @return
   */
  @RequestMapping(value = "/listAuth", method = RequestMethod.POST)
  public @ResponseBody List<TreeNodeResponse> listAuth(Pageable pageable, Long id) {
    List<TreeNodeResponse> treeNodeResponses = new ArrayList<TreeNodeResponse>();
    Map<Long, TreeNodeResponse> parentMap = new HashMap<Long, TreeNodeResponse>();
    Role role = roleService.find(id);
    Set<ConfigMeta> currentAuthList = null;// 当前角色的权限
    if (role != null && role.getConfigMetas () != null) {
      currentAuthList = role.getConfigMetas();
    }

    // 租户能使用的所有功能包
    Set<ConfigMeta> packageAll = tenantInfoService.getCurrentTenantVersionPackage ();

    /*
     * 创建树结构，定义树结构只有2级，子节点必须有父节点Id,没有parentId的肯定是父节点
     */
    // 创建父节点（package）
    for (ConfigMeta packagecConfigMeta : packageAll) {
      TreeNodeResponse treeNodeResponse = new TreeNodeResponse();
        treeNodeResponse.setId(packagecConfigMeta.getId());
        treeNodeResponse.setText(packagecConfigMeta.getName ());
        if (currentAuthList.contains(packagecConfigMeta)) {
//          treeNodeResponse.setChecked(true);
        } else {
          treeNodeResponse.setChecked(false);
        }
        treeNodeResponse.setIconCls("icon-remove");
        treeNodeResponse.setState (TreeNodeState.closed);
        
        
        //配置子节点（function）
        List<ConfigMeta> relatedFunctions = configMetaService.findRelationFunction (packagecConfigMeta);
        //是否是总公司
        boolean isParentTenant = isParentTenant();
        for(ConfigMeta function : relatedFunctions)
        {
          //功能包 "车辆调度" 下，子公司只能 "用车请求"，总公司只能 "车辆指派"
          if (isParentTenant) {
            if (function.getConfigKey().equals("useCarRequest") ||
                function.getConfigKey().equals("settleSearch")) {
              continue;
            }
          }else {
            if (function.getConfigKey().equals("vehicleAssign") ||
                function.getConfigKey().equals("settleManagement")) {
              continue;
            }
          }
          List<TreeNodeResponse> childList = new ArrayList<TreeNodeResponse>();
          TreeNodeResponse treeNodeResponseChild = new TreeNodeResponse();
          
          treeNodeResponseChild.setId(function.getId());
          treeNodeResponseChild.setText(function.getName ());
          if (currentAuthList.contains(function)) {
            treeNodeResponseChild.setChecked(true);
          } else {
            treeNodeResponseChild.setChecked(false);
          }
          treeNodeResponseChild.setIconCls("icon-add");
          
          treeNodeResponseChild.setState (TreeNodeState.open);
          
          if (treeNodeResponse != null && treeNodeResponse.getChildren() != null) {
            childList = treeNodeResponse.getChildren();
          }
          
          
          childList.add(treeNodeResponseChild);
          treeNodeResponse.setChildren(childList);
        }
        
        parentMap.put(treeNodeResponse.getId(), treeNodeResponse);
        treeNodeResponses.add(treeNodeResponse);
    }
    // 创建子节点

    return treeNodeResponses;
  }


  /**
   * 授权
   * 
   * @param role
   * @return
   */
  @RequestMapping(value = "/addAuth", method = RequestMethod.POST)
  public @ResponseBody Message addAuth(Long[] authIds, Long id) {
    Role role = roleService.find(id);
    Set<ConfigMeta> authorityResources = new HashSet<ConfigMeta>();

    if (authIds == null || id == null) {
      LogUtil.debug(RoleController.class, "RoleController.addAuth()",
          "authId=null or id=null, tenant ID=%s", tenantAccountService.getCurrentTenantID());
      return ERROR_MESSAGE;
    }
    List<ConfigMeta> authorityResourceList = configMetaService.findList (authIds);
    authorityResources.addAll (authorityResourceList);
    role.setConfigMetas (authorityResources);
    roleService.update (role);

    return SUCCESS_MESSAGE;
  }
  /**
   * 检查是否是总公司，总公司return true， 否者分公司return false
   * @return
   */
  private boolean isParentTenant(){
    Long currentTenantID = tenantAccountService.getCurrentTenantID();
    TenantInfo tenantInfo = tenantInfoService.find(currentTenantID);
    if (tenantInfo.getParent() == null) {
      return true;
    }
    return false;
  }
}
