package com.waataja.fooddrop;

import java.util.GregorianCalendar;
import java.util.List;


public class Giveaway {
	
	private FoodDonator donator;
	private List<FoodItem> items;
	
	public enum GiveawayType {
		ANY, FOODBANK, PEOPLE
	}

	public Giveaway(FoodDonator donator, GregorianCalendar start, GregorianCalendar end, GiveawayType type, String availability) {
		super();
		this.donator = donator;
		this.start = start;
		this.end = end;
		this.type = type;
		this.availability = availability;
	}
	
	public FoodDonator getDonator() {
		return donator;
	}

	public void setDonator(FoodDonator donator) {
		this.donator = donator;
	}

	public GregorianCalendar getStart() {
		return start;
	}

	public void setStart(GregorianCalendar start) {
		this.start = start;
	}

	public GregorianCalendar getEnd() {
		return end;
	}

	public void setEnd(GregorianCalendar end) {
		this.end = end;
	}

	public GiveawayType getType() {
		return type;
	}

	public void setType(GiveawayType type) {
		this.type = type;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public List<FoodItem> getItems() {
		return items;
	}

	public void setItems(List<FoodItem> items) {
		this.items = items;
	}

	private GregorianCalendar start;
	private GregorianCalendar end;
	
	private GiveawayType type;
	
	private String availability;
}
