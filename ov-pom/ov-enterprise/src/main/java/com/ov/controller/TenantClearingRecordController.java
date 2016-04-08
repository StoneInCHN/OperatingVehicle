package com.ov.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.TenantClearingRecord;
import com.ov.entity.TenantInfo;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.ordering.Ordering;
import com.ov.framework.ordering.Ordering.Direction;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.SnService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantClearingRecordService;
import com.ov.service.TenantInfoService;
import com.ov.service.VehicleSchedulingService;

@Controller("tenantClearingRecordController")
@RequestMapping("console/tenantClearingRecord")
public class TenantClearingRecordController extends BaseController{

	@Autowired
	private TenantClearingRecordService tenantClearingRecordService;
	
	@Autowired
	private TenantAccountService tenantAccountService;
	
	@Autowired
	private TenantInfoService tenantInfoService;
	
	@Autowired
	private SnService snService;
	
	@Autowired
	private VehicleSchedulingService vehicleSchedulingService;
	
	@RequestMapping(value = "/clearingRecordsManagement", method = RequestMethod.GET)
	public String clearingRecordsView(){
		return "clearingRecord/clearingRecordsManagement";
	}
	
	/**
	 * 结算列表
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/listClearingRecord", method = RequestMethod.POST)
	public @ResponseBody Page<TenantClearingRecord> listClearingRecord(Pageable pageable, String childrenOrParent,
			String snSearch){
		
		List<Filter> filters = new ArrayList<>();
		Filter filter = null;
		if (VehicleSchedulingController.CHILDREN_REQUEST.equals(childrenOrParent)) {
			filter = new Filter("child", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		}else if (VehicleSchedulingController.PARENT_REQUEST.equals(childrenOrParent)) {
			filter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		}
		filters.add(filter);
		if (!StringUtils.isEmpty(snSearch)) {
			filter = new Filter("clearingSn", Operator.eq, snSearch);
		}
		filters.add(filter);
		pageable.setFilters(filters);
		
		return tenantClearingRecordService.findPage(pageable);
	}
	/**
	 * 获取所有子公司
	 */
	@RequestMapping(value = "/listBranchBusiness", method = RequestMethod.POST)
	public @ResponseBody List<TenantInfo> listBranchBusiness(){
		List<Filter> filters = new ArrayList<>();
		Filter filter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		filters.add(filter);
		List<Ordering> orderings = new ArrayList<Ordering>();
		orderings.add(new Ordering("id", Direction.asc));
		return tenantInfoService.findList(1000, filters, orderings);
	}
	/**
	 * 新增结算
	 * @param clearingRecord
	 * @param branchBusinessId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/addClearingRecord", method = RequestMethod.POST)
	public @ResponseBody Message addClearingRecord(TenantClearingRecord clearingRecord, Long branchBusinessId,
			String startDate, String endDate){
		try {
			tenantClearingRecordService.saveClearingRecord(clearingRecord, branchBusinessId, startDate, endDate);
			return SUCCESS_MESSAGE;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR_MESSAGE;
		}
		
	}
	
	
}
