package com.waataja.fooddrop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * 
 * @author Daniel
 *
 */
public class LatGetter {
	public static double[] getLatLong(String address) {
		try {
			URL u = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address.replace(" ", "+")
					+ "&key=AIzaSyBHJZR3rqsqZoB7Na1ATfJH9bQEHyARU78");
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			BufferedReader b = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			ArrayList<String> read = new ArrayList<String>();
			while(b.ready()) {
				read.add(b.readLine());
			}
			int line = -1;
			for(String s :read){
				line++;
				if(s.contains("location")) {
					break;
				}
				System.out.println(s);
			}
			double[] latLong = new double[2];
			String s1 = read.get(line + 1);
			s1 = s1.substring(s1.indexOf(":") + 2, s1.indexOf(","));
			String s2 = read.get(line + 2);
			s2 = s2.substring(s2.indexOf(":") + 2);
			
			latLong[0] = Double.parseDouble(s1);
			latLong[1] = Double.parseDouble(s2);
			return latLong;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
