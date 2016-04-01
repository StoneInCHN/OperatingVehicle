package com.ov.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ov.beans.Message;
import com.ov.beans.Message.Type;
import com.ov.controller.base.BaseController;
import com.ov.entity.Vehicle;
import com.ov.entity.VehicleScheduling;
import com.ov.entity.commonenum.CommonEnum.VehicleSchedulingStatus;
import com.ov.entity.commonenum.CommonEnum.VehicleStatus;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.response.VehicleResponse;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;
import com.ov.service.VehicleSchedulingService;
import com.ov.service.VehicleService;

@Controller("vehicleSchedulingController")
@RequestMapping("console/vehicleScheduling")
public class VehicleSchedulingController extends BaseController{
	
	private static final String CHILDREN_REQUEST = "children";
	
	private static final String PARENT_REQUEST = "parent";
	
	@Autowired
	private VehicleSchedulingService vehicleSchedulingService;

	@Autowired
	private TenantAccountService tenantAccountService;
	
	@Autowired
	private TenantInfoService tenantInfoService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value = "/useCarRequest", method = RequestMethod.GET)
	public String useCarRequest(){
		return "vehicleScheduling/useCarRequest";
	}
	
	/**
	 * 用车请求列表
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/listRequest", method = RequestMethod.POST)
	public @ResponseBody Page<VehicleScheduling> listRequest(Pageable pageable, String titlesSearch, String childrenOrParent, 
			String startPositionDetailsSearch, String endPositionDetailsSearch, VehicleSchedulingStatus statusSearch){
		
		IKAnalyzer analyzer = new IKAnalyzer ();
	    analyzer.setMaxWordLength (true);
	    BooleanQuery query = new BooleanQuery ();

	    QueryParser nameParser = null;
	    Query nameQuery = null;
	    TermQuery statusQuery = null;
	    
	    org.apache.lucene.search.Filter luceneFilter = null;
	    if (titlesSearch != null){
	      String text = QueryParser.escape (titlesSearch);
	        try{
	        	nameParser = new QueryParser (Version.LUCENE_35, "title", analyzer);
	        	nameQuery = nameParser.parse (text);
	        	query.add (nameQuery, Occur.MUST);
	          
	        }catch (ParseException e){
	          e.printStackTrace();
	        }
	    }
	    if (startPositionDetailsSearch != null){
	      String text = QueryParser.escape (startPositionDetailsSearch);
	        try{
	        	nameParser = new QueryParser (Version.LUCENE_35, "startPositionDetails", analyzer);
	          nameQuery = nameParser.parse (text);
	          query.add (nameQuery, Occur.MUST);
	          
	        }catch (ParseException e){
	          e.printStackTrace();
	        }
	    }
	    if (endPositionDetailsSearch != null){
	      String text = QueryParser.escape (endPositionDetailsSearch);
	        try{
	        	nameParser = new QueryParser (Version.LUCENE_35, "endPositionDetails", analyzer);
	          nameQuery = nameParser.parse (text);
	          query.add (nameQuery, Occur.MUST);
	          
	        }catch (ParseException e){
	          e.printStackTrace();
	        }
	    }
	    if (statusSearch != null){
	      statusQuery = new TermQuery (new Term ("status",statusSearch.toString ()));
	      query.add (statusQuery,Occur.MUST);
	    }
	    
	    List<Filter> filters = new ArrayList<>();
		Filter filter = null;
		if (CHILDREN_REQUEST.equals(childrenOrParent)) {
			filter = new Filter("requestBusiness", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		}else if (PARENT_REQUEST.equals(childrenOrParent)) {
			filter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		}
		filters.add(filter);
		pageable.setFilters(filters);

	    if (nameQuery != null || statusQuery != null){
	      return vehicleSchedulingService.search (query, pageable, analyzer, luceneFilter, false);
	    }
		
		return vehicleSchedulingService.findPage(pageable);
	}
	/**
	 * 新增用车请求
	 * @return
	 */
	@RequestMapping(value = "/addRequest", method = RequestMethod.POST)
	public @ResponseBody Message addRequest(VehicleScheduling vehicleScheduling){
		vehicleScheduling.setStatus(VehicleSchedulingStatus.TO_CONFIRM);
		vehicleScheduling.setRequestBusiness(tenantAccountService.getCurrentTenantInfo());
		vehicleScheduling.setParent(tenantAccountService.getCurrentTenantInfo().getParent());
		vehicleSchedulingService.save(vehicleScheduling);
		return SUCCESS_MESSAGE;
	}
	/**
	 * 请求详情
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/detailsRequest", method = RequestMethod.GET)
	public String detailsRequest(Long id, ModelMap modelMap){
		VehicleScheduling vehicleScheduling = vehicleSchedulingService.find(id);
		modelMap.put("vehicleScheduling", vehicleScheduling);
		return "vehicleScheduling/detailsCarRequest";
	}
	/**
	 * 编辑请求
	 * @return
	 */
	@RequestMapping(value = "/editRequest", method = RequestMethod.GET)
	public String editRequest(Long id, ModelMap modelMap){
		modelMap.put("vehicleScheduling", vehicleSchedulingService.find(id));
		return "vehicleScheduling/editCarRequest";
	}
	/**
	 * 更新用车请求信息
	 * @param vehicleScheduling
	 * @return
	 */
	@RequestMapping(value = "/updateRequest", method = RequestMethod.POST)
	public @ResponseBody Message updateRequest(VehicleScheduling vehicleScheduling){
		if (vehicleScheduling.getStatus() == VehicleSchedulingStatus.TO_CONFIRM) {
			vehicleSchedulingService.update(vehicleScheduling, "createDate", "status", "requestBusiness", "parent");
			return SUCCESS_MESSAGE;
		}else {
			return Message.error("ov.message.statusError");
		}
	}
	/**
	 * 获取当前访问者IP
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getIP", method = RequestMethod.GET)
	public @ResponseBody Message getIP(HttpServletRequest request){
		String remoteAddr = request.getRemoteAddr();
		Message message = new Message(Type.success, remoteAddr);
		return message;
	}
	/**
	 * 车辆分配列表
	 * @return
	 */
	@RequestMapping(value = "/vehicleAssign", method = RequestMethod.GET)
	public String vehicleAssign(){
		return "vehicleScheduling/vehicleAssign";
	}
	/**
	 * 车辆分配弹出框
	 * @return
	 */
	@RequestMapping(value = "/assignVehicleView", method = RequestMethod.GET)
	public String assignVehicleView(Long id, ModelMap model){
		model.put("vehicleSchedulingId", id);
		return "vehicleScheduling/assignVehicleView";
	}
	/**
	 * 车辆分配弹出框-车辆列表
	 * @return
	 */
	@RequestMapping(value = "/listVehicle", method = RequestMethod.POST)
	public @ResponseBody Page<VehicleResponse> listVehicle(Pageable pageable, String motorcadeSearch, String plateSearch){
		
		/**
		 * 车辆信息添加功能未做，此处不能使用lucene
		 */
//		IKAnalyzer analyzer = new IKAnalyzer ();
//	    analyzer.setMaxWordLength (true);
//	    BooleanQuery query = new BooleanQuery ();
//
//	    QueryParser nameParser = null;
//	    Query nameQuery = null;
//	    
//	    org.apache.lucene.search.Filter luceneFilter = null;
//	    if (motorcadeSearch != null){
//	      String text = QueryParser.escape (motorcadeSearch);
//	        try{
//	        	nameParser = new QueryParser (Version.LUCENE_35, "motorcade", analyzer);
//	        	nameQuery = nameParser.parse (text);
//	        	query.add (nameQuery, Occur.MUST);
//	          
//	        }catch (ParseException e){
//	          e.printStackTrace();
//	        }
//	    }
//	    if (plateSearch != null){
//	      String text = QueryParser.escape (plateSearch);
//	        try{
//	        	nameParser = new QueryParser (Version.LUCENE_35, "plate", analyzer);
//	          nameQuery = nameParser.parse (text);
//	          query.add (nameQuery, Occur.MUST);
//	          
//	        }catch (ParseException e){
//	          e.printStackTrace();
//	        }
//	    }
		
	    List<Filter> filters = new ArrayList<>();
		Filter filter = new Filter("tenantInfo", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		filters.add(filter);
		filter = new Filter("vehicleStatus", Operator.eq, VehicleStatus.ENABLE);
		filters.add(filter);

		if (plateSearch != null){
			filter = new Filter("plate", Operator.like, plateSearch);
			filters.add(filter);
		}
		
		pageable.setFilters(filters);
	    
		Page<Vehicle> vehiclePage = null;
		
//	    if (motorcadeSearch != null || plateSearch != null){
//	    	vehiclePage = vehicleService.search (query, pageable, analyzer, luceneFilter, false);
//	    }else {
//	    	vehiclePage = vehicleService.findPage(pageable);
//		}
		
		vehiclePage = vehicleService.findPage(pageable);
		
		long total = vehiclePage.getTotal();
		List<Vehicle> vehicleRows = vehiclePage.getRows();
		List<VehicleResponse> vehicleResponses = new ArrayList<VehicleResponse>();
		for (Vehicle vehicle : vehicleRows) {
			VehicleResponse vehicleResponse = new VehicleResponse();
			vehicleResponse.setId(vehicle.getId());
			vehicleResponse.setPlate(vehicle.getPlate());
			vehicleResponse.setVehicleLine(vehicle.getVehicleBrandDetail().getVehicleLine().getName());
			vehicleResponse.setMotorcade(vehicle.getMotorcade().getMotorcadeDesc());
			vehicleResponse.setOilPerHundred(vehicle.getVehicleBrandDetail().getOilPerHundred());
			vehicleResponses.add(vehicleResponse);
		}
		Page<VehicleResponse> page = new Page<VehicleResponse>(vehicleResponses, total, pageable);
		return page;
	}
	/**
	 * 确认车辆分配
	 * @return
	 */
	 @RequestMapping(value = "/assignVehicle", method = RequestMethod.POST)
	 public @ResponseBody Message assignVehicle(Long vehicleSchedulingId, String vehicle_id){
		 VehicleScheduling vehicleScheduling = vehicleSchedulingService.find(vehicleSchedulingId);
		 Set<Vehicle> vehicles = new HashSet<Vehicle>();
		 org.json.JSONArray jsonArray = new org.json.JSONArray(vehicle_id);
		 for (Object object : jsonArray) {
			 vehicles.add(vehicleService.find(Long.valueOf((String) object)));
		 }
		 vehicleScheduling.setVehicles(vehicles);
		 vehicleScheduling.setStatus(VehicleSchedulingStatus.DISTRIBUTED);
		 vehicleSchedulingService.update(vehicleScheduling);
		 
		 return SUCCESS_MESSAGE;
	 }
	 
	
}
