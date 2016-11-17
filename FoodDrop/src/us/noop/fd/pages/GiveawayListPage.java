package us.noop.fd.pages;

import java.util.ArrayList;

import com.waataja.fooddrop.FoodItem;
import com.waataja.fooddrop.Giveaway;

import us.noop.fd.data.BigData;
import us.noop.fd.data.TemporaryData;
import us.noop.server.pages.Page;
import us.noop.server.response.RequestData;
import us.noop.server.response.ResponseManager;

/**
 * 
 * @author Ulysses
 *
 */
public class GiveawayListPage implements Page {
		
	private BigData data;
	
	public GiveawayListPage(BigData data){
		this.data = data;
	}
	
	@SuppressWarnings("unused")
	@Override
	public byte[] getResponse(RequestData req) {
		System.out.println(data.getGiveaways().size());
		String input = req.getAddress();
		input = input.substring(input.indexOf("?") + 1);
		String[] spl = input.split("&");
		int type = Integer.parseInt(spl[0]);
		String what = spl[1];
		double lat = Double.parseDouble(spl[2]);
		double lng = Double.parseDouble(spl[3]);
		
		StringBuilder rs = new StringBuilder();
		rs.append("{\"locations\": [");
		ArrayList<Giveaway> giveaways = TemporaryData.nearby(lat, lng, data.getGiveaways());
		int i = 1;
		for(Giveaway g : giveaways){
			rs.append("{\"lat\":");
			rs.append(g.getDonator().getLatitude());
			rs.append(",\"lng\": ");
			rs.append(g.getDonator().getLongitude());
			rs.append(",\"desc\": \"" + g.getDonator().getAddress());
			rs.append("\",\"name\": \"" + g.getDonator().getName());
			rs.append("\",\"html\": \"" + generateDesc(g));
			rs.append("\"}");
			if(i < giveaways.size())
				rs.append(",");
			i++;
		}
		rs.append("]}");
		return ResponseManager.generateHeader(200, "OK", rs.toString(), "text/plain").getBytes();
	}

	private String generateDesc(Giveaway g) {
		StringBuilder sob = new StringBuilder();
		sob.append("<h3>");
		sob.append(g.getDonator().getName());
		sob.append("</h3>");
		sob.append(g.getDonator().getAddress());
		sob.append("<br><br>");
		sob.append(g.getAvailability());
		
		return sob.toString();
	}

	@Override
	public String getAddress() {
		return "/getposition?";
	}

	@Override
	public String mimeType() {
		return "text/plain";
	}
}
