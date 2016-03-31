package com.ov.response;

public class VehicleResponse {

	/**
	 * vehicle entity id
	 */
	private Long id;
	
	/**
	 * 车牌号
	 */
	private String plate;
	
	/**
	 * 车系
	 */
	private String vehicleLine;
	
	/**
	 * 车队
	 */
	private String motorcade;
	
	/**
	 * 百公里油耗
	 */
	private Float oilPerHundred;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getVehicleLine() {
		return vehicleLine;
	}

	public void setVehicleLine(String vehicleLine) {
		this.vehicleLine = vehicleLine;
	}

	public String getMotorcade() {
		return motorcade;
	}

	public void setMotorcade(String motorcade) {
		this.motorcade = motorcade;
	}

	public Float getOilPerHundred() {
		return oilPerHundred;
	}

	public void setOilPerHundred(Float oilPerHundred) {
		this.oilPerHundred = oilPerHundred;
	}
	
	
}
