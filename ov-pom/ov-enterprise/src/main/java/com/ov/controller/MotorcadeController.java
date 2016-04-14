package com.ov.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
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
import com.ov.entity.Motorcade;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.MotorcadeService;
import com.ov.service.TenantAccountService;

/**
 * 设备管理
 * @author huyong
 *
 */
@Controller ("motorcadeController")
@RequestMapping ("console/motorcade")
public class MotorcadeController extends BaseController
{

  @Resource (name = "motorcadeServiceImpl")
  private MotorcadeService motorcadeService;
  
  	@Autowired
  	private TenantAccountService tenantAccountService;
  
  	/**
  	 * 车队列表
  	 * @return
  	 */
  	@RequestMapping(value = "/motorcade", method = RequestMethod.GET)
  	public String motorcade(){
  		return "/motorcade/motorcade";
  	}
  	
  	@RequestMapping(value = "/list", method = RequestMethod.POST)
  	public @ResponseBody Page<Motorcade> list(String motorcadeDescSearch, Pageable pageable){
  		IKAnalyzer analyzer = new IKAnalyzer ();
	    analyzer.setMaxWordLength (true);
	    BooleanQuery query = new BooleanQuery ();

	    QueryParser nameParser = null;
	    Query nameQuery = null;
	    
	    org.apache.lucene.search.Filter luceneFilter = null;
	    if (motorcadeDescSearch != null){
	      String text = QueryParser.escape (motorcadeDescSearch);
	        try{
	        	nameParser = new QueryParser (Version.LUCENE_35, "title", analyzer);
	        	nameQuery = nameParser.parse (text);
	        	query.add (nameQuery, Occur.MUST);
	          
	        }catch (ParseException e){
	          e.printStackTrace();
	        }
	    }
	    List<Filter> filters = new ArrayList<>();
		Filter filter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
		filters.add(filter);
		pageable.setFilters(filters);
	    if (nameQuery != null) {
			return motorcadeService.search(nameQuery, pageable, analyzer, luceneFilter);
		}
	    
  		return motorcadeService.findPage(pageable);
  	}
  
  	@RequestMapping(value = "/edit", method = RequestMethod.GET)
  	public String edit(Long id, ModelMap modelMap){
  		Motorcade motorcade = motorcadeService.find(id);
  		modelMap.put("motorcade", motorcade);
  		return "/motorcade/edit";
  	}
  	
  	@RequestMapping(value = "/update", method = RequestMethod.POST)
  	public @ResponseBody Message update(Motorcade motorcade){
  		motorcadeService.update(motorcade, "parent");
  		return SUCCESS_MESSAGE;
  	}
    
  	@RequestMapping(value = "/save", method = RequestMethod.POST)
  	public @ResponseBody Message save(Motorcade motorcade){
  		motorcade.setParent(tenantAccountService.getCurrentTenantInfo());
  		motorcadeService.save(motorcade);
  		return SUCCESS_MESSAGE;
  	}
  	
  @RequestMapping (value = "/findAllMotorcadeUnderTenant", method = RequestMethod.GET)
  public @ResponseBody List<Map<String, Object>> findAllMotorcadeUnderTenant (ModelMap model)
  {
     return motorcadeService.findAllMotorcadeUnderTenant ();
  }
 
}
