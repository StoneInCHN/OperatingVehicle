package com.ov.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.TenantInfo;
import com.ov.entity.VehicleScheduling;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.TenantAccountService;
import com.ov.service.VehicleSchedulingService;

@Controller("vehicleSchedulingController")
@RequestMapping("console/vehicleScheduling")
public class VehicleSchedulingController extends BaseController{
	
	@Autowired
	private VehicleSchedulingService vehicleSchedulingService;

	@Autowired
	private TenantAccountService tenantAccountService;
	
	@RequestMapping(value = "/useCarRequest", method = RequestMethod.GET)
	public String useCarRequest(){
		return "vehicleScheduling/useCarRequest";
	}
	
	/**
	 * 子公司用车请求列表
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/listRequest", method = RequestMethod.POST)
	public @ResponseBody Page<VehicleScheduling> listRequest(Pageable pageable){
		
		List<Filter> filters = new ArrayList<>();
		Filter filter = new Filter("requestBusiness", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		filters.add(filter);
		pageable.setFilters(filters);
		return vehicleSchedulingService.findPage(pageable);
	}
	/**
	 * 新增用车请求
	 * @return
	 */
	@RequestMapping(value = "/addRequest", method = RequestMethod.POST)
	public @ResponseBody Message addBranch(VehicleScheduling vehicleScheduling){
		
		vehicleSchedulingService.save(vehicleScheduling);
		return SUCCESS_MESSAGE;
	}
	//是否能够取消用车请求？编辑用车请求？
	
}
