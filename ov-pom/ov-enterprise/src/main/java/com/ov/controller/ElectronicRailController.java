package com.ov.controller;

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
		electronicRailService.save(electronicRail);
		Vehicle vehicle = vehicleService.find(vehicleId);
		vehicle.setElectronicRail(electronicRail);
		vehicleService.update(vehicle);
		
		return SUCCESS_MESSAGE;
	}
	
}
