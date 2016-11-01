package us.noop.fd.pages;

import us.noop.fd.data.BigData;
import us.noop.server.pages.Page;
import us.noop.server.pages.Page404;
import us.noop.server.response.RequestData;

/**
 * 
 * @author Ulysses
 *
 */
public class GiveawayCreationPage implements Page {

	private BigData data;
	
	public GiveawayCreationPage(BigData data){
		this.data = data;
	}
	
	/**
	 *  /creategiveaway?visibility&lat&lng&
	 */
	@Override
	public byte[] getResponse(RequestData req) {
		String input = req.getAddress();
		input = input.substring(input.indexOf("?") + 1);
		
		
		return new Page404().getResponse(req);
	}

	@Override
	public String getAddress() {
		return "/createdrop?";
	}

	@Override
	public String mimeType() {
		return "text/plain";
	}
	
}
