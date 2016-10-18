package us.noop.server.pages;

import java.io.File;

import us.noop.server.RequestData;

public class StaticImagePage extends StaticFilePage {
	public StaticImagePage(String address, File f, String mimetype) {
		super(address, f, mimetype);
		byte[] header = ("HTTP/1.1 200 OK\r\nContent-Length: " + (response.length) + "\r\nConnection: Closed\r\nContent-Disposition: inline; filename=\"FoodDropLogoSmall.png\"\r\nContent-Type: " + 
				mimetype + "\r\n\r\n").getBytes();
		byte[] nr = new byte[response.length + header.length];
		System.arraycopy(header, 0, nr, 0, header.length);//TODO This shit is fucking stupid
		System.arraycopy(response, 0, nr, header.length, response.length);
	}
	
	@Override
	public byte[] getResponse(RequestData req){
		return response;
	}
}
