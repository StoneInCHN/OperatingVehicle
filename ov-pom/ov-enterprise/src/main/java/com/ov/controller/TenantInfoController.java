package com.ov.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
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

import com.ov.beans.CommonAttributes;
import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.ConfigMeta;
import com.ov.entity.Role;
import com.ov.entity.TenantAccount;
import com.ov.entity.TenantInfo;
import com.ov.entity.TenantUser;
import com.ov.entity.commonenum.CommonEnum.AccountStatus;
import com.ov.framework.filter.Filter.Operator;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.ConfigMetaService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;
import com.ov.service.TenantUserService;

@Controller
@RequestMapping("console/tenantInfo")
public class TenantInfoController extends BaseController{
	
	@Autowired
	private TenantInfoService tenantInfoService;
	
	@Autowired
	private TenantAccountService tenantAccountService;
	
	@Resource (name = "tenantUserServiceImpl")
	private TenantUserService tenantUserService;
	
	@Autowired
	private ConfigMetaService configMetaService;  
	
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
	    
	    org.apache.lucene.search.Filter luceneFilter = null;
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
     * 更新公司管理员
     * @param tenantUser
     * @return
     */
      @RequestMapping (value = "/updateAdmin", method = RequestMethod.POST)
      public @ResponseBody Message updateAdmin (String enPassword, TenantAccount tenantAccount)
      {
        if (!enPassword.equals (tenantAccount.getPassword ()))
        {
          tenantAccount.setPassword (DigestUtils.md5Hex(tenantAccount.getPassword ()));
        }
        TenantUser tenantUser = tenantAccount.getTenantUser();
        tenantInfoService.updateAdminTransaction(tenantUser, tenantAccount);
            
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
	 * 添加分公司管理员
	 * @param tenantUser
	 * @return
	 */
	  @RequestMapping (value = "/addAdmin", method = RequestMethod.POST)
	  public @ResponseBody Message addAdmin (TenantUser tenantUser, TenantAccount tenantAccount, Long tenantInfoId)
	  {
	    if (tenantInfoId != null) {
	        TenantInfo tenantInfo = tenantInfoService.find(tenantInfoId);
	        tenantUser.setTenantID(tenantInfoId);
	        tenantUser.setTenantName(tenantInfo.getTenantName());
	        tenantAccount.setTenantID(tenantInfoId);
	        tenantAccount.setPassword (DigestUtils.md5Hex(tenantAccount.getPassword ()));
	        tenantAccount.setIsAdmin(true);
	        //为管理员新创建一个角色
	        Role role = new Role();
	        role.setIsSystem(true);
	        role.setName(CommonAttributes.ADMIN);
	        role.setTenantID(tenantInfoId);
	        
	        //赋予管理员最基本的配置元，即系统管理功能包
	        String[] configKeys = {CommonAttributes.SYSTEM_MANAGE, CommonAttributes.USER_MANAGE, CommonAttributes.ACCOUNT_MANAGE,
	            CommonAttributes.DEPARTMENT_MANAGE, CommonAttributes.AUTHORITY_MANAGE, CommonAttributes.ROLE_MANAGE}; 
	        List<ConfigMeta> configMetaList = configMetaService.findSetByConfigKeys(configKeys);
	        Set<ConfigMeta> configMetaSet = new HashSet<ConfigMeta>();
	        configMetaSet.addAll(configMetaList);
	        role.setConfigMetas (configMetaSet);  
	        
	        tenantInfoService.addAdminTransaction(tenantUser, tenantAccount, role);
	        
	        return SUCCESS_MESSAGE;
        }else {
          return ERROR_MESSAGE;
        }
	    
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
	   /**
       * 管理员详情页面
       * 
       * @param model
       * @param id
       * @return
       */
      @RequestMapping(value = "/detailsAdmin", method = RequestMethod.GET)
      public String detailsAdmin(ModelMap model, Long id, String path) {
        TenantAccount tenantAccount = tenantAccountService.find(id);
        model.addAttribute("tenantAccount", tenantAccount);
        return "tenantInfo/" + path;
      }
	  /**
	   * 企业列表
	   * @return
	   */
	  @RequestMapping(value = "/list", method = RequestMethod.GET)
	  public @ResponseBody List<TenantInfo> list(boolean findListForAdmin) {
	    List<TenantInfo> tenantInfos = tenantInfoService.findRoots();
	    //去除已经有管理员的子公司
	    if (findListForAdmin) {
          for (int i = 0; i < tenantInfos.size(); i++) {
            Set<TenantInfo> children = tenantInfos.get(i).getChildren();
            Set<TenantInfo> childrenWithoutAdmin = new HashSet<TenantInfo>();
            for (TenantInfo tenantInfo : children) {
              //找到还没有分配管理员的子公司
              com.ov.framework.filter.Filter tenantFilter = new com.ov.framework.filter.Filter("tenantID", Operator.eq, tenantInfo.getId());
              com.ov.framework.filter.Filter isAdminFilter = new com.ov.framework.filter.Filter("isAdmin", Operator.eq, true);
              Long count = tenantAccountService.count(tenantFilter,isAdminFilter);
              if (count == 0) {
                childrenWithoutAdmin.add(tenantInfo);
              }
            }
            tenantInfos.get(i).setChildren(childrenWithoutAdmin);
          }
        }
	    return tenantInfos;
	  }
	  /**
	   * 分公司管理员列表
	   * @return
	   */
	  @RequestMapping (value = "/listTenantBranchAdmin", method = RequestMethod.POST)
	  public @ResponseBody Page<TenantAccount> listTenantBranchAdmin (Pageable pageable, String adminNameSearch, AccountStatus adminAccountStatusSearch)
	  {	    
	    List<com.ov.framework.filter.Filter> filters = pageable.getFilters();
	    if (adminNameSearch != null){
	      com.ov.framework.filter.Filter userNameFilter = new com.ov.framework.filter.Filter("userName", Operator.like, "%"+adminNameSearch+"%");
	      filters.add(userNameFilter);
	    }
	    if (adminAccountStatusSearch != null){
	       com.ov.framework.filter.Filter accountStatusFilter = new com.ov.framework.filter.Filter("accoutStatus", Operator.eq, adminAccountStatusSearch);
	       filters.add(accountStatusFilter);
	    }
	    Long currentTenantID = tenantAccountService.getCurrentTenantID();
	    TenantInfo tenantInfo = tenantInfoService.find(currentTenantID);
	    Object[] tenantBranchs = tenantInfo.getChildren().toArray();
	    Long[] tenantIDs = new Long[tenantBranchs.length];
	    for (int i = 0; i < tenantBranchs.length; i++) {
	      TenantInfo tenantInfoChild = (TenantInfo)tenantBranchs[i];
	      tenantIDs[i] = tenantInfoChild.getId();
        }
	    if (tenantIDs.length > 0) {
	      return tenantAccountService.findPage (pageable, tenantIDs);
        }else {
          return null;
        }
	    
	    
	  }
	
}
