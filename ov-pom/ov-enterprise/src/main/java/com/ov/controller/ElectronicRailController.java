package com.ov.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ov.beans.Message;
import com.ov.controller.base.BaseController;
import com.ov.entity.ElectronicRail;
import com.ov.entity.Vehicle;
import com.ov.service.ElectronicRailService;
import com.ov.service.VehicleService;

@Controller("electronicRailController")
@RequestMapping("console/electronicRail")
public class ElectronicRailController extends BaseController{

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ElectronicRailService electronicRailService;
	
	@RequestMapping(value = "/electronicRail", method = RequestMethod.GET)
	public String electronicRail(){
		return "/electronicRail/electronicRail";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Message save(ElectronicRail electronicRail, Long vehicleId){
		
		Vehicle vehicle = vehicleService.find(vehicleId);
		ElectronicRail electronicRailGet = vehicle.getElectronicRail();
		if (electronicRailGet == null) {
			electronicRailService.save(electronicRail);
			vehicle.setElectronicRail(electronicRail);
		}else {
			electronicRailGet.setRadius(electronicRail.getRadius());
			electronicRailGet.setCenterLng(electronicRail.getCenterLng());
			electronicRailGet.setCenterLat(electronicRail.getCenterLat());
			vehicle.setElectronicRail(electronicRailGet);
		}
		
		vehicleService.update(vehicle);
		
		return SUCCESS_MESSAGE;
	}
	
	@RequestMapping(value = "/findElectronicRailByVehicle", method = RequestMethod.GET)
	public @ResponseBody ElectronicRail findElectronicRailByVehicle(Long vehicleId){
		Vehicle vehicle = vehicleService.find(vehicleId);
		ElectronicRail electronicRail = vehicle.getElectronicRail();
		return electronicRail;
	}
	
	@RequestMapping(value = "/realTimeVehicleStatus", method = RequestMethod.GET)
	public @ResponseBody Message realTimeVehicleStatus(Long id){
		try {
			String result = "";
			HttpPost httppost=new HttpPost("http://10.50.40.102:8080/ov-obd-data/tenantVehicleData/realTimeVehicleStatusTest.jhtml");
	        List<NameValuePair> params=new ArrayList<NameValuePair>();
//	        params.add(new BasicNameValuePair("deviceId", "8856019607"));
	        params.add(new BasicNameValuePair("id", String.valueOf(id)));
	
	        httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	        HttpResponse response=new DefaultHttpClient().execute(httppost);
	        if(response.getStatusLine().getStatusCode() == 200) {
	            result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
//	            System.out.println(result);
	        }
	        if (!"".equals(result)) {
	        	return new Message(com.ov.beans.Message.Type.success, result);
			}else {
				new Exception("获取车辆实时数据异常");
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new Message(com.ov.beans.Message.Type.error, "");
		}
		
	}
	
}
