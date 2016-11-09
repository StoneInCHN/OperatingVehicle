package com.ov.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.ov.beans.Setting;



public class LatLonUtil {
  /**
   * 百度坐标转地址
   * 
   * @param lat
   * @param lng
   * @return
   */
  public static String convertCoorForAddr(String lat, String lng) {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      Setting setting = SettingUtils.get();
      String url =
          setting.getConvertAddressUrl() + "?location=" + lat + "," + lng + "&output=json&ak="
              + setting.getMapAk();
      String res = ApiUtils.get(url);
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> resMap = (Map<String, Object>) mapper.readValue(res, Map.class);
      Map<String, Object> resultMap = (Map<String, Object>) resMap.get("result");
      String addr = (String) resultMap.get("formatted_address");
      return addr;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // 批量转换gps位置到百度地图位置
  @SuppressWarnings("unchecked")
  public static List<Map<String, Object>> convertCoordinates(List<Map<String, Object>> maps) {
    List<Map<String, Object>> resultMapsList = new ArrayList<Map<String, Object>>();
    if (maps == null || maps.size() == 0) {
      return resultMapsList;
    }
    try {

      Setting setting = SettingUtils.get();

      List<List<Map<String, Object>>> newMapsList = new ArrayList<List<Map<String, Object>>>();
      int count = maps.size() / 100;
      for (int i = 1; i <= count; i++) {
        newMapsList.add(maps.subList((i - 1) * 100, i * 100));
      }

      int rem = maps.size() % 100;
      newMapsList.add(maps.subList((maps.size() - rem), maps.size()));

      for (int i = 0; i < newMapsList.size(); i++) {
        StringBuffer corrds = new StringBuffer();
        List<Object> vehicleListInCover = new ArrayList<Object>();
        for (Map<String, Object> gpsMap : newMapsList.get(i)) {
          if (gpsMap.get("lon") != null && gpsMap.get("lat") != null) {
            String x = gpsMap.get("lon").toString();
            String y = gpsMap.get("lat").toString();
            corrds.append(x + ",");
            corrds.append(y + ";");
            vehicleListInCover.add(gpsMap.get("vehicleId"));
          }
        }
        String url =
            setting.getConvertMutipleMapUrl() + "?from=1&to=5&ak=" + setting.getMapAk()
                + "&coords=" + corrds.substring(0, corrds.length() - 1);
        // String url
        // ="http://api.map.baidu.com/geoconv/v1/?coords=114.21892734521,29.575429778924;114.21892734521,29.575429778924&from=1&to=5&ak=ulsOtfMZcNc4D6aQnBwwnOTt6ZKohflO";
        String res = ApiUtils.get(url);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> resMap = (Map<String, Object>) mapper.readValue(res, Map.class);
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) resMap.get("result");
        for (int k = 0; k < listMap.size(); k++) {
          listMap.get(k).put("vehicleId", vehicleListInCover.get(k));
        }
        resultMapsList.addAll(listMap);
      }

      return resultMapsList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
