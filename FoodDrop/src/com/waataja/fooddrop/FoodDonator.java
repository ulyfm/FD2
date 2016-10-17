package com.waataja.fooddrop;

/**
 * 
 * @author Jason
 *
 */
public class FoodDonator {
	
	private String name;
	
	private String address;
	
	public FoodDonator(String name, String address, String description, double lat, double lon) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.lat = lat;
		this.lon = lon;
	}

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	private double lat, lon;
	public double getLatitude(){
		return lat;
	}
	public double getLongitude(){
		return lon;
	}
}
