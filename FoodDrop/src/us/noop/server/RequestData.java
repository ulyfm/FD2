package us.noop.server;

import java.util.HashMap;

public class RequestData {
	
	private String reqtype;
	private String address;
	private String httpver;
	private HashMap<String, String> fields;
	
	public RequestData(String ip, String[] header, String data){
		this(ip, header);
		setData(data);
	}
	
	public RequestData(String ip, String[] header){
		String[] spl = header[0].split(" ");
		reqtype = spl[0];
		address = spl[1];
		httpver = spl[2];
		fields = new HashMap<String, String>();
		for(int i = 1; i < header.length; ++i){
			String[] f = header[i].split(": ");
			fields.put(f[0], f[1]);
		}
	}
	
	public String getValue(String key){
		return fields.get(key);
	}
	
	public void setData(String data){
		
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getRequestType(){
		return reqtype;
	}
	
	public String getHttpVersion(){
		return httpver;
	}
}
