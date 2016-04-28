package com.ov.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.bcel.generic.NEW;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.status.Status;

import com.ov.beans.CommonAttributes;
import com.ov.beans.Message;
import com.ov.beans.Setting;
import com.ov.common.log.LogUtil;
import com.ov.controller.base.BaseController;
import com.ov.entity.TenantAccount;
import com.ov.entity.TenantInfo;
import com.ov.entity.commonenum.CommonEnum.VehicleSchedulingStatus;
import com.ov.framework.filter.Filter;
import com.ov.framework.filter.Filter.Operator;
import com.ov.job.ReportJob;
import com.ov.service.CaptchaService;
import com.ov.service.DeviceInfoService;
import com.ov.service.RSAService;
import com.ov.service.ReportProcedureService;
import com.ov.service.TenantAccountService;
import com.ov.service.TenantInfoService;
import com.ov.service.TenantUserService;
import com.ov.service.VehicleSchedulingService;
import com.ov.service.VehicleService;
import com.ov.utils.ApiUtils;
import com.ov.utils.DateTimeUtils;
import com.ov.utils.SettingUtils;

/**
 * Controller - 共用
 * 
 */
@Controller("commonController")
@RequestMapping("/console/common")
public class CommonController extends BaseController {

  @Resource(name = "rsaServiceImpl")
  private RSAService rsaService;
  @Resource(name = "captchaServiceImpl")
  private CaptchaService captchaService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;
  @Resource(name = "tenantUserServiceImpl")
  private TenantUserService tenantUserService;
  @Resource(name = "vehicleServiceImpl")
  private VehicleService vehicleService;
  @Resource(name = "deviceInfoServiceImpl")
  private DeviceInfoService deviceInfoService;
  @Resource(name = "vehicleSchedulingServiceImpl")
  private VehicleSchedulingService vehicleSchedulingService;
  @Resource(name = "reportProcedureServiceImpl")
  private ReportProcedureService reportProcedureService;
  
//  @Resource(name = "areaServiceImpl")
//  private AreaService areaService;

  private Setting setting = SettingUtils.get();
  /**
   * 验证码
   */
  @RequestMapping(value = "/captcha", method = RequestMethod.GET)
  public void image(String captchaId, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if (StringUtils.isEmpty(captchaId)) {
      captchaId = request.getSession().getId();
    }
    String pragma =
        new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse()
            .toString();
    String value =
        new StringBuffer().append("ten").append(".").append("erot").append("se").reverse()
            .toString();
    response.addHeader(pragma, value);
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/jpeg");

    ServletOutputStream servletOutputStream = null;
    try {
      servletOutputStream = response.getOutputStream();
      BufferedImage bufferedImage = captchaService.buildImage(captchaId);
      ImageIO.write(bufferedImage, "jpg", servletOutputStream);
      servletOutputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(servletOutputStream);
    }
  }  
  /**
   * 主页
   */
@RequestMapping(value = "/main", method = RequestMethod.GET)
public String main(ModelMap model,  HttpSession session) {
    Filter tenantFilter = new Filter("tenantID", Operator.eq, tenantAccountService.getCurrentTenantID());
    Filter parentOrChildFilter = null;
    boolean isParentTenant = isParentTenant();
    model.addAttribute("isParentTenant", isParentTenant);
    if (isParentTenant) {//总公司首页需要呈现的信息
      model.addAttribute("vehicleCount", vehicleService.count(tenantFilter));
      model.addAttribute("deviceCount", deviceInfoService.count(tenantFilter));
      parentOrChildFilter = new Filter("parent", Operator.eq, tenantAccountService.getCurrentTenantInfo());
      
    }else {//子公司首页需要呈现的信息
      TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo();
      TenantInfo parentTenantInfo = tenantInfo.getParent();//所属总公司信息
      model.addAttribute("parentTenantInfo", parentTenantInfo);
      parentOrChildFilter = new Filter("requestBusiness", Operator.eq, tenantAccountService.getCurrentTenantInfo());
    }
    TenantAccount tenantAccount = tenantAccountService.getCurrent();
    model.addAttribute("tenantAccount", tenantAccount);
    model.addAttribute("tenantUserCount", tenantUserService.count(tenantFilter)); 
    model.addAttribute("carRequestCount", vehicleSchedulingService.count(parentOrChildFilter));
    VehicleSchedulingStatus[] status = VehicleSchedulingStatus.values();
    Filter nearlyMonthFilter = new Filter("createDate",Operator.gt,DateTimeUtils.getLastMonth());//近一个月
    for (int i = 0; i < status.length; i++) {
      Filter statusFilter = new Filter("status", Operator.eq, status[0]);
      model.addAttribute("vsCount"+status[0].toString(), vehicleSchedulingService.count(parentOrChildFilter,nearlyMonthFilter,statusFilter));
    }
  return "/common/main";
}
  /**
   * 错误提示
   */
  @RequestMapping("/error")
  public String error() {
    return ERROR_VIEW;
  }

  /**
   * 权限错误
   */
  @RequestMapping("/unauthorized")
  public String unauthorized(HttpServletRequest request, HttpServletResponse response) {
    String requestType = request.getHeader("X-Requested-With");
    if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
      response.addHeader("loginStatus", "unauthorized");
      try {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
    return "/common/unauthorized";
  }

  /**
   * 退出
   */
  @RequestMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    Subject subject = SecurityUtils.getSubject();
    if (subject.isAuthenticated()) {
      subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
    }
    return "redirect:/";
  }


  /**
   * 公钥
   */
  @RequestMapping(value = "/public_key", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, String> publicKey(HttpServletRequest request) {
    RSAPublicKey publicKey = rsaService.generateKey(request);
    Map<String, String> data = new HashMap<String, String>();
    data.put("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
    data.put("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
    return data;
  }
  @RequestMapping(value ="/changePassword",method = RequestMethod.GET)
  public String changePassword(){
    return "common/changePassword";
  }
  
  @RequestMapping(value ="/savePassword",method = RequestMethod.POST)
  public @ResponseBody Message savePassword(String oldPassword, String newPassword){
    TenantAccount tenantAccount = tenantAccountService.getCurrent();
    String newEnPassword = DigestUtils.md5Hex(newPassword);
    if (!newEnPassword.equals (tenantAccount.getPassword ()))
    {
      return Message.error ("yly.tenantAccount.oldPasswordError");
    }
    tenantAccount.setPassword (newEnPassword);
    tenantAccountService.update (tenantAccount);
    return SUCCESS_MESSAGE;
  }
  /**
   * 公钥
   */
  @RequestMapping(value = "/refreshIndex", method = RequestMethod.GET)
  public @ResponseBody Message
   refreshLuceneIndex(HttpServletRequest request) {
    tenantAccountService.refreshIndex();
    return SUCCESS_MESSAGE;
  }
  /**
   * 手动跑当月报表数据的Job
   * @param request
   * @param currentDateString "yyyy-MM-dd"
   * @return
   */
  @RequestMapping(value = "/runReportJob", method = RequestMethod.GET)
  public @ResponseBody Message runReportJob(HttpServletRequest request, String currentDateString) {
    if (StringUtils.isBlank(currentDateString)) {
      return ERROR_MESSAGE;
    }
    try {
      List<TenantInfo> tenantInfos = tenantInfoService.findAll();
      String[] procedures = ReportJob.procedures;
      for (int i = 0; i < procedures.length; i++) {
        String procedure = procedures[i];
        LogUtil.debug(CommonController.class, "runReportJob", "call " + procedure + " start!");
        for (int j = 0; j < tenantInfos.size(); j++) { //以租户为单位（一个事务）来调用存储过程
          Long tenantId = tenantInfos.get(j).getId();
          LogUtil.debug(CommonController.class, "runReportJob", "tenantId: " + tenantId + " currentDateString:"+currentDateString);
          reportProcedureService.callProcedure(procedure,tenantId,currentDateString);
        }
        LogUtil.debug(CommonController.class, "runReportJob", "call " + procedure + " end!");
      }
    } catch (Exception e) {
      return ERROR_MESSAGE;
    }
    return SUCCESS_MESSAGE;
  }  
  /**
   * @param params 参数：deviceId=8801001667&fromDate=2016-4-1&toDate=2016-4-30
   */
  @RequestMapping(value = "/monthlyVehicleStatus", method = RequestMethod.POST)
  public @ResponseBody String monthlyVehicleStatus(String deviceId, String fromDate, String toDate) {
//    String mileageJson = "{'msg':[{'dailyMileage': 10,'averageFuelConsumption': 17,'fuelConsumption': 16,'cost': null,'averageSpeed': 19,'emergencybrakecount': 2,'suddenturncount': 0,'rapidlyspeedupcount': 4,'createdate': 1459872000000,'day': 6},{'dailyMileage': 23,'averageFuelConsumption': 7,'fuelConsumption': 9,'cost': null,'averageSpeed': 8,'emergencybrakecount': 7,'suddenturncount': 10,'rapidlyspeedupcount': 13,'createdate': 1459958400000,'day': 7}]}";
//    return mileageJson.replaceAll("'", "\"");
    String mileageJson = "";
    if (deviceId != null && fromDate != null && toDate != null) {
      StringBuffer params = new StringBuffer();
      params.append("deviceId=");
      params.append(deviceId);
      params.append("&fromDate=");
      params.append(fromDate);
      params.append("&toDate=");
      params.append(toDate);
      mileageJson = ApiUtils.post(setting.getObdServerUrl() +"/tenantVehicleData/monthlyVehicleStatus.jhtml",params.toString());
    }
    return mileageJson;
  }
  /**
   * 检查是否是总公司，总公司return true， 否者分公司return false
   * @return
   */
  private boolean isParentTenant(){
    Long currentTenantID = tenantAccountService.getCurrentTenantID();
    TenantInfo tenantInfo = tenantInfoService.find(currentTenantID);
    if (tenantInfo.getParent() == null) {
      return true;
    }
    return false;
  }
//  /**
//   * 异步判断验证码是否正确
//   * @param captchaType 验证码类型
//   * @param captchaId 验证码Id
//   * @param captcha 验证码
//   * @return
//   */
//  @RequestMapping(value = "/captchaCheck", method = RequestMethod.GET)
//  public @ResponseBody
//  boolean captchaCheck(CaptchaType captchaType, String captchaId, String captcha) {
//    return captchaService.isValid(captchaType, captchaId, captcha);
//  }
  
//  /**
//   * 地区
//   */
//  @RequestMapping(value = "/area", method = RequestMethod.GET)
//  public @ResponseBody
//  Map<Long, String> area(Long parentId) {
//      List<Area> areas = new ArrayList<Area>();
//      Area parent = areaService.find(parentId);
//      if (parent != null) {
//          areas = new ArrayList<Area>(parent.getChildren());
//      } else {
//          areas = areaService.findRoots();
//      }
//      Map<Long, String> options = new HashMap<Long, String>();
//      for (Area area : areas) {
//          options.put(area.getId(), area.getName());
//      }
//      return options;
//  }

}
