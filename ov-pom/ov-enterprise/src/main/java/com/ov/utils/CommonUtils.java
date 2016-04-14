package com.ov.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import com.ov.beans.Setting;


/**
 * 
 * @author tanbiao 公共方法
 */
public class CommonUtils {

  public static String randomPwd() {
    Random r = new Random();
    char[] str1 =
        {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9'};
    char[] str2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char[] str3 = {'!', '@', '#', '$', '%', '^', '&', '*', '.', '~'};
    StringBuffer sBuffer = new StringBuffer();
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    sBuffer.append(str1[Math.abs(r.nextInt(str1.length))]);
    int Intindex = Math.abs(r.nextInt(6));
    sBuffer.replace(Intindex, Intindex, str2[Math.abs(r.nextInt(str2.length))] + "");
    boolean flag = true;
    while (flag) {
      int Strindex = Math.abs(r.nextInt(6));
      if (Intindex != Strindex) {
        sBuffer.replace(Strindex, Strindex, str3[Math.abs(r.nextInt(str3.length))] + "");
        flag = false;
      }
    }
    return sBuffer.toString();
  }

  public static String getOilInfo4BaiDu() {
    BufferedReader reader = null;
    String result = null;
    StringBuffer sbf = new StringBuffer();
    Setting setting = SettingUtils.get();
    String httpUrl = setting.getVehicleOilAPIHttpUrl() ;
    try {
      URL url = new URL(httpUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      // 填入apikey到HTTP header
      connection.setRequestProperty("apikey", setting.getVehicleOilAPIKey());
      //connection.setRequestProperty("apikey", "7bb8ed487692b2c2dd290d477d879236");
      connection.connect();
      InputStream is = connection.getInputStream();
      reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      String strRead = null;
      while ((strRead = reader.readLine()) != null) {
        sbf.append(strRead);
        sbf.append("\r\n");
      }
      reader.close();
      result = sbf.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String getShortPlate(String prov){
    String shortPlate = null;
    switch (prov) {
      case "北京":
        shortPlate = "京";
        break;
      case "天津":
        shortPlate = "津";
        break;
      case "上海":
        shortPlate = "沪";
        break;
      case "重庆":
        shortPlate = "渝";
        break;
      case "内蒙古":
        shortPlate = "蒙";
        break;
      case "新疆":
        shortPlate = "新";
        break;
      case "西藏":
        shortPlate = "藏";
        break;
      case "宁夏":
        shortPlate = "宁";
        break;
      case "广西":
        shortPlate = "桂";
        break;
      case "澳门":
        shortPlate = "澳";
        break;
      case "香港":
        shortPlate = "港";
        break;
      case "黑龙江":
        shortPlate = "黑";
        break;
      case "吉林":
        shortPlate = "吉";
        break;
      case "辽宁":
        shortPlate = "辽";
        break;
      case "山西":
        shortPlate = "晋";
        break;
      case "河北":
        shortPlate = "冀";
        break;
      case "青海":
        shortPlate = "青";
        break;
      case "山东":
        shortPlate = "鲁";
        break;
      case "河南":
        shortPlate = "豫";
        break;
      case "江苏":
        shortPlate = "苏";
        break;
      case "安徽":
        shortPlate = "皖";
        break;
      case "浙江":
        shortPlate = "浙";
        break;
      case "福建":
        shortPlate = "闽";
        break;
      case "江西":
        shortPlate = "赣";
        break;
      case "湖南":
        shortPlate = "湘";
        break;
      case "湖北":
        shortPlate = "鄂";
        break;
      case "广东":
        shortPlate = "粤";
        break;
      case "海南":
        shortPlate = "琼";
        break;
      case "甘肃":
        shortPlate = "甘";
        break;
      case "陕西":
        shortPlate = "陕";
        break;
      case "贵州":
        shortPlate = "黔";
        break;
      case "云南":
        shortPlate = "滇";
        break;
      case "四川":
        shortPlate = "川";
        break;
      default:
        break;
    }
    return shortPlate;
  }
  
  public static BigDecimal getOilPrice(String price,String prov){
    if (prov.equals("北京") && price.contains("京")) {
       String p1 = price.split("京")[0];
       price = p1.substring(0, p1.length() - 1);
       
    }else if(prov.equals("上海") && price.contains("沪")) {
      String p1 = price.split("沪")[0];
      price = p1.substring(0, p1.length() - 1);
    }
    return new BigDecimal(price);
  }
 
}
