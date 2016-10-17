package com.waataja.fooddrop;

/**
 * 
 * @author Jason
 *
 */
public class FoodReceiver {
	
	public enum ReceiverType {
		PERSON, FOODBANK
	}
	
	private ReceiverType type;
	private double latitude;
	private double longitude;
	
	public FoodReceiver(ReceiverType type, double latitude, double longitude) {
		super();
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public ReceiverType getType() {
		return type;
	}
	public void setType(ReceiverType type) {
		this.type = type;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
