package com.ov.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.Role;
import com.ov.entity.TenantAccount;
import com.ov.entity.TenantInfo;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;

@Controller
@RequestMapping("console/tenantInfo")
public class TenantInfoController extends BaseController{
	
	@Autowired
	private TenantInfoService tenantInfoService;
	
	@Autowired
	private TenantAccountService tenantAccountService;

	@RequestMapping(value = "/branchBusiness", method = RequestMethod.GET)
	public String listBranchBusiness(){
		return "tenantInfo/branchBusiness";
	}
	
	/**
	 * 查询子公司
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listBranch", method = RequestMethod.POST)
	public @ResponseBody Page<TenantInfo> listBranchBusiness(Pageable pageable, ModelMap model){
		
		List<Filter> filters = new ArrayList<Filter>();
		Filter filter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		filters.add(filter);
		pageable.setFilters(filters);
		return tenantInfoService.findPage(pageable, false);
	}
	/**
	 * 编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping (value = "/editBranch", method = RequestMethod.GET)
	public String editBranch(ModelMap model, Long id){
		TenantInfo tenantInfo = tenantInfoService.find(id);
	    model.put("tenantInfo", tenantInfo);
	    return "tenantInfo/edit";
	}
	
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST)
	public @ResponseBody Message updateBranch(TenantInfo tenantInfo){
		tenantInfoService.update(tenantInfo, "createDate", "orgCode", "parent", "child", "versionConfig");
		return SUCCESS_MESSAGE;
	}

}
