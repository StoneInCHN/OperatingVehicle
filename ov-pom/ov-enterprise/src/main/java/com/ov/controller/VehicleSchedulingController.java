package com.ov.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.VehicleScheduling;
import com.ov.entity.commonenum.CommonEnum.VehicleSchedulingStatus;
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
	public @ResponseBody Page<VehicleScheduling> listRequest(Pageable pageable, String titlesSearch,
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

	    if (nameQuery != null || statusQuery != null){
	      return vehicleSchedulingService.search (query, pageable, analyzer, luceneFilter, false);
	    }
		
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
	
	
}
