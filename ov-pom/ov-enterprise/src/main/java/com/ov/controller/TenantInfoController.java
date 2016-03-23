package com.ov.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
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
import com.ov.entity.TenantInfo;
import com.ov.entity.commonenum.CommonEnum.AccountStatus;
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
	public @ResponseBody Page<TenantInfo> listBranchBusiness(Pageable pageable, String tenantNameSearch,
			String contactPhoneSearch, String contactPersonSearch, AccountStatus accountStatusSearch){

	    IKAnalyzer analyzer = new IKAnalyzer ();
	    analyzer.setMaxWordLength (true);
	    BooleanQuery query = new BooleanQuery ();

	    QueryParser nameParser = null;
	    Query nameQuery = null;
	    TermQuery statusQuery = null;
	    
	    Filter luceneFilter = null;
	    if (tenantNameSearch != null){
	      String text = QueryParser.escape (tenantNameSearch);
	        try{
	        	nameParser = new QueryParser (Version.LUCENE_35, "tenantName", analyzer);
	        	nameQuery = nameParser.parse (text);
	        	query.add (nameQuery, Occur.MUST);
	          
	        }catch (ParseException e){
	          e.printStackTrace();
	        }
	    }
	    if (contactPhoneSearch != null){
	      String text = QueryParser.escape (contactPhoneSearch);
	        try{
	        	nameParser = new QueryParser (Version.LUCENE_35, "contactPhone", analyzer);
	          nameQuery = nameParser.parse (text);
	          query.add (nameQuery, Occur.MUST);
	          
	        }catch (ParseException e){
	          e.printStackTrace();
	        }
	    }
	    if (contactPersonSearch != null){
	      String text = QueryParser.escape (contactPersonSearch);
	        try{
	        	nameParser = new QueryParser (Version.LUCENE_35, "contactPerson", analyzer);
	          nameQuery = nameParser.parse (text);
	          query.add (nameQuery, Occur.MUST);
	          
	        }catch (ParseException e){
	          e.printStackTrace();
	        }
	    }
	    if (accountStatusSearch != null){
	      statusQuery = new TermQuery (new Term ("accountStatus",accountStatusSearch.toString ()));
	      query.add (statusQuery,Occur.MUST);
	    }

	    if (nameQuery != null || statusQuery != null){
	      return tenantInfoService.search (query, pageable, analyzer, luceneFilter, false);
	    }
		
		List<com.ov.framework.filter.Filter> filters = new ArrayList<com.ov.framework.filter.Filter>();
		com.ov.framework.filter.Filter filter = new com.ov.framework.filter.Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
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
	    return "tenantInfo/editBranch";
	}
	/**
	 * 更新
	 * @param tenantInfo
	 * @return
	 */
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST)
	public @ResponseBody Message updateBranch(TenantInfo tenantInfo){
		tenantInfoService.update(tenantInfo, "createDate", "orgCode", "parent", "child", "versionConfig");
		return SUCCESS_MESSAGE;
	}
	/**
	 * 新增子公司
	 * @return
	 */
	@RequestMapping(value = "/addBranch", method = RequestMethod.POST)
	public @ResponseBody Message addBranch(TenantInfo tenantInfo){
		tenantInfo.setParent(tenantAccountService.getCurrentTenantInfo());
		tenantInfo.setOrgCode(tenantAccountService.getCurrentTenantOrgCode());
		tenantInfoService.save(tenantInfo);
		return SUCCESS_MESSAGE;
	}
	  /**
	   * 删除公司
	   */
	  @RequestMapping (value = "/deleteBranch", method = RequestMethod.POST)
	  public @ResponseBody Message delete (Long[] ids)
	  {
	    if (ids != null){
	      tenantInfoService.delete (ids);
	    }
	    return SUCCESS_MESSAGE;
	  }
	  /**
	   * 详情页面
	   * 
	   * @param model
	   * @param id
	   * @return
	   */
	  @RequestMapping(value = "/detailsBranch", method = RequestMethod.GET)
	  public String details(ModelMap model, Long id) {
	    TenantInfo tenantInfo = tenantInfoService.find(id);
	    model.addAttribute("tenantInfo", tenantInfo);
	    return "tenantInfo/detailsBranch";
	  }
	
}
