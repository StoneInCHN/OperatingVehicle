package com.ov.controller;

import java.math.BigDecimal;
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
import com.ov.entity.Sn.Type;
import com.ov.entity.VehicleScheduling;
import com.ov.entity.commonenum.CommonEnum.ClearingStatus;
import com.ov.entity.commonenum.CommonEnum.VehicleSchedulingStatus;
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
import com.ov.utils.DateTimeUtils;

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
	public @ResponseBody Page<TenantClearingRecord> listClearingRecord(Pageable pageable, String childrenOrParent){
		
		
		List<Filter> filters = new ArrayList<>();
		Filter filter = null;
		if (VehicleSchedulingController.CHILDREN_REQUEST.equals(childrenOrParent)) {
			filter = new Filter("child", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		}else if (VehicleSchedulingController.PARENT_REQUEST.equals(childrenOrParent)) {
			filter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
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
			String startDate, String endDate) throws ParseException{
		TenantInfo branchBusiness = tenantInfoService.find(branchBusinessId);
		clearingRecord.setChild(branchBusiness);
		clearingRecord.setParent(tenantAccountService.getCurrentTenantInfo());
		clearingRecord.setClearingSn(snService.generate(Type.clearing));
		clearingRecord.setClearingStatus(ClearingStatus.SUCCESS);
		
		List<Filter> filters = new ArrayList<Filter>();
	    Filter filter = new Filter("requestBusiness", Operator.eq, branchBusiness);
	    filters.add(filter);
	    filter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
	    filters.add(filter);
	    filter = new Filter("status", Operator.eq, VehicleSchedulingStatus.FINISHED);
	    filters.add(filter);
		if ( !StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			filter = new Filter("startDate", Operator.gt, DateTimeUtils.convertStringToDate(startDate, null));
		    filters.add(filter);
		    filter = new Filter("startDate", Operator.lt, DateTimeUtils.convertStringToDate(endDate, null));
		    filters.add(filter);
		}
		List<VehicleScheduling> vehicleSchedulings = vehicleSchedulingService.findList(null, filters, null);
		BigDecimal totalDistance = new BigDecimal(0);
		for (VehicleScheduling vehicleScheduling : vehicleSchedulings) {
			totalDistance = totalDistance.add(vehicleScheduling.getTotalDistance());
		}
		clearingRecord.setVehicleSchedulings(vehicleSchedulings);
		
		BigDecimal amountOfCurrent = totalDistance.multiply(clearingRecord.getUnitPrice());
		clearingRecord.setTotalDistance(totalDistance);
		clearingRecord.setAmountOfCurrent(amountOfCurrent);

		tenantClearingRecordService.save(clearingRecord);
		
		for (VehicleScheduling vehicleScheduling : vehicleSchedulings) {
			totalDistance = totalDistance.add(vehicleScheduling.getTotalDistance());
			vehicleScheduling.setStatus(VehicleSchedulingStatus.CLEARED);
			vehicleScheduling.setClearingRecord(clearingRecord);
			vehicleSchedulingService.update(vehicleScheduling);
		}
		
		return SUCCESS_MESSAGE;
	}
	
	
}
