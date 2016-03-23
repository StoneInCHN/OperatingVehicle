package com.ov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("vehicleSchedulingController")
@RequestMapping("console/vehicleScheduling")
public class VehicleSchedulingController {

	
	@RequestMapping(value = "/useCarRequest", method = RequestMethod.GET)
	public String useCarRequest(){
		
		return "vehicleScheduling/useCarRequest";
	}
	
	
}
