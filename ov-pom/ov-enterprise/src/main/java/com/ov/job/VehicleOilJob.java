package com.ov.job;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ov.json.response.OilBean;
import com.ov.service.VehicleOilService;
import com.ov.utils.CommonUtils;
import com.ov.utils.JsonUtil;

@Component("vehicleOilJob")
@Lazy(false)
public class VehicleOilJob {

  @Resource(name="vehicleOilServiceImpl")
  private VehicleOilService vehicleOilService;
  
  /**
   * 定时获取油价信息
   */
  @Scheduled(cron = "${job.ov.vehicleOilJob.cron}")
  public void updateOilInfo() {
    System.out.println("开始扫描........");

    String jsonResult = CommonUtils.getOilInfo4BaiDu();
    Map<String, Object> map = JsonUtil.getMap4Json(jsonResult);
    String[] strings = jsonResult.split("list");
    String[] str = null;
    String objString = null;
    for (String string : strings) {
      if (string.contains("ret_code")) {
        str = string.split("ret_code");
      }
    }
    if (str != null) {
      for (String string : str) {
        if (string.contains("[")) {
          objString = string.substring(2, string.length() - 2);
          break;
        }

      }
    }
    if (objString != null) {
      List<OilBean> oilBeans = JsonUtil.getList4Json(objString, OilBean.class);
      for (OilBean oilBean : oilBeans) {
        System.out.println(oilBean.getProv() + "的油价:p0=" + oilBean.getP0() + " , p90 = "
            + oilBean.getP90() + " , p93 = " + oilBean.getP93() + " , p97 = " + oilBean.getP97());
        vehicleOilService.updateOilInfo(oilBean);
      }

    }
    
    System.out.println("扫描结束........");
  }
  
  
  
  
}
