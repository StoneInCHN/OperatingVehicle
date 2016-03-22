package com.ov.controller;

import java.util.Date;
import java.util.HashSet;
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
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.Role;
import com.ov.entity.TenantAccount;
import com.ov.entity.TenantUser;
import com.ov.entity.commonenum.CommonEnum.AccountStatus;
import com.ov.framework.paging.Page;
import com.ov.framework.paging.Pageable;
import com.ov.service.RSAService;
import com.ov.service.RoleService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantUserService;
import com.ov.utils.DateTimeUtils;

/**
 * 租户用户
 *
 */
@Controller ("tenantAccountController")
@RequestMapping ("console/tenantAccount")
public class TenantAccountController extends BaseController
{

  @Resource (name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Resource (name = "tenantUserServiceImpl")
  private TenantUserService tenantUserService;
  @Resource(name = "roleServiceImpl")
  private RoleService roleService;
  @Resource(name = "rsaServiceImpl")
  private RSAService rsaService;
  
  @RequestMapping (value = "/tenantAccount", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "tenantAccount/tenantAccount";
  }
  /**
   * 用户账号列表
   * @param pageable
   * @param model
   * @param beginDate
   * @param endDate
   * @param userNameSearch
   * @param accountStatusSearch
   * @return
   */
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<TenantAccount> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,AccountStatus accountStatusSearch)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "userName",
        analyzer);
    Query nameQuery = null;
    TermRangeQuery rangeQuery = null;
    TermQuery statusQuery = null;
    
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (userNameSearch != null)
    {
      String text = QueryParser.escape (userNameSearch);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
    }
    if (accountStatusSearch != null)
    {
      statusQuery = new TermQuery (new Term ("accoutStatus",accountStatusSearch.toString ()));
      query.add (statusQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
    }
    if (nameQuery != null || rangeQuery != null || statusQuery != null)
    {
      return tenantAccountService.search (query, pageable, analyzer,filter,true);
    }
      return tenantAccountService.findPage (pageable, true);
    
  }

  /**
   * 编辑账户页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    TenantAccount tenantAccount = tenantAccountService.find (id);
    Set<Role> roles =tenantAccount.getRoles ();
    model.put ("tenantAccount", tenantAccount);
    if (roles != null && roles.iterator ().hasNext ())
    {
      model.put ("roleInfo", roles.iterator ().next ());
    }
    return "tenantAccount/edit";
  }
  /**
   * 添加账户
   * @param tenantAccount
   * @param tenantUserID
   * @param roleID
   * @return
   */
  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (TenantAccount tenantAccount,Long tenantUserID,Long roleID)
  {
    
    TenantUser tenantUser=tenantUserService.find(tenantUserID);
    Role role =roleService.find (roleID);
    Set<Role> roleSet = new HashSet <Role> ();
    roleSet.add (role);
    tenantAccount.setTenantUser(tenantUser);
    tenantAccount.setIsSystem (false);
    tenantAccount.setRoles (roleSet);
    tenantAccount.setPassword (DigestUtils.md5Hex(tenantAccount.getPassword ()));
    tenantAccountService.save (tenantAccount,true);
    return SUCCESS_MESSAGE;
  }
  /**
   * 更新账户
   * @param enPassword
   * @param tenantAccount
   * @param tenantUserID
   * @param roleID
   * @return
   */
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (String enPassword,TenantAccount tenantAccount,Long tenantUserID,Long roleID)
  {
    TenantUser tenantUser=tenantUserService.find(tenantUserID);
    Role role =roleService.find (roleID);
    Set<Role> roleSet = new HashSet<Role> ();
    roleSet.add (role);
    if (!enPassword.equals (tenantAccount.getPassword ()))
    {
      tenantAccount.setPassword (DigestUtils.md5Hex(tenantAccount.getPassword ()));
    }
    tenantAccount.setTenantUser(tenantUser);
    tenantAccount.setRoles (roleSet);
    tenantAccountService.update (tenantAccount,"createDate","isSystem","tenantID");
    return SUCCESS_MESSAGE;
  }
 

  /**
   * 删除账户
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long[] ids)
  {
    if (ids != null)
    {
      // 是否需要检查是否能被删除？
      tenantAccountService.delete (ids);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 账号详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    TenantAccount tenantAccount = tenantAccountService.find(id);
    model.addAttribute("tenantAccount", tenantAccount);
    return "tenantAccount/details";
  }
}
