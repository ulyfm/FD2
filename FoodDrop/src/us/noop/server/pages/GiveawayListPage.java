package us.noop.server.pages;

import java.util.ArrayList;

import com.waataja.fooddrop.FoodItem;
import com.waataja.fooddrop.Giveaway;

import us.noop.data.BigData;
import us.noop.server.Page;

public class GiveawayListPage implements Page {
		
	private BigData data;
	
	public GiveawayListPage(BigData data){
		this.data = data;
	}
	
	@SuppressWarnings("unused")
	@Override
	public String getResponse(String input) {
		input = input.substring(input.indexOf("?") + 1);
		String[] spl = input.split("&");
		int type = Integer.parseInt(spl[0]);
		int usert = Integer.parseInt(spl[1]);
		double lat = Double.parseDouble(spl[2]);
		double lng = Double.parseDouble(spl[3]);
		
		StringBuilder rs = new StringBuilder();
		rs.append("{\"locations\": [");
		ArrayList<Giveaway> giveaways = data.getGiveaways();
		for(Giveaway g : giveaways){
			rs.append("{\"lat\":");
			rs.append(g.getDonator().getLatitude());
			rs.append(",\"lng\": ");
			rs.append(g.getDonator().getLongitude());
			rs.append(",\"desc\": \"" + generateDesc(g));
			rs.append("\"}");
		}
		rs.append("]}");
		return rs.toString();
	}

	private String generateDesc(Giveaway g) {
		StringBuilder sob = new StringBuilder();
		sob.append("<h3>");
		sob.append(g.getDonator().getName());
		sob.append("</h3>");
		sob.append(g.getDonator().getAddress());
		sob.append("<br><br>");
		for(FoodItem f : g.getItems()){
			sob.append(f.getAmount());
			sob.append(" ");
			sob.append(f.getName());
			sob.append("<br>");
		}
		
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
