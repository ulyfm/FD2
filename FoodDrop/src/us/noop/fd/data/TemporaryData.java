package us.noop.fd.data;

import java.util.ArrayList;

import com.waataja.fooddrop.FoodDonator;
import com.waataja.fooddrop.Giveaway;

public class TemporaryData {
	public static ArrayList<Giveaway> nearby(double lat, double lng, ArrayList<Giveaway> data){
		ArrayList<Giveaway> ret = new ArrayList<Giveaway>();
		for(Giveaway g : data){
			FoodDonator d = g.getDonator();
			double a = distance(lat, lng, d.getLatitude(), d.getLongitude()) / 1000d;
			if(a < 50.0){//TODO fix hardcoding
				ret.add(g);
			}
		}
		return ret;
	}
	public static double distance(double lat1, double lng1, double lat2, double lng2){
		double R = 6371e3;
		double phi1 = Math.toRadians(lat1);
		double phi2 = Math.toRadians(lat2);
		double deltaphi = Math.toRadians(lat2 - lat1);
		double deltalambda = Math.toRadians(lng2 - lng1);
		double a = Math.sin(deltaphi / 2.0) * Math.sin(deltaphi / 2.0) +
				Math.cos(phi1) * Math.cos(phi2) *
				Math.sin(deltalambda / 2.0) * Math.sin(deltalambda / 2.0);
		double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
		return R * c;
	}
}
