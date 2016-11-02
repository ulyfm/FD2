package us.noop.fd.pages;

import java.util.GregorianCalendar;
import java.util.Scanner;

import com.waataja.fooddrop.FoodDonator;
import com.waataja.fooddrop.Giveaway;
import com.waataja.fooddrop.Giveaway.GiveawayType;

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
	 *  /creategiveaway
	 */
	@Override
	public byte[] getResponse(RequestData req) {
		System.out.println(req.getData());
		String dat = req.getData();
		Scanner sc = new Scanner(dat);
		String address = sc.nextLine();
		String name = sc.nextLine();
		String date = sc.nextLine();
		address += "<br>" + date.split("T")[0] + " @ " + fixtime(date.split("T")[1]);
		double lat = Double.parseDouble(sc.nextLine());
		double lng = Double.parseDouble(sc.nextLine());
		String specialinstructions = sc.nextLine();
		String fbonly = sc.nextLine();
		String items = "";
		while(sc.hasNextLine()){
			items += sc.nextLine() + "<br>";
		}
		FoodDonator fd = new FoodDonator(name, address, specialinstructions, lat, lng);
		Giveaway g = new Giveaway(fd, null, null, GiveawayType.ANY, items);//TODO
		data.getGiveaways().add(g);
		return new Page404().getResponse(req);
	}

	@Override
	public String getAddress() {
		return "/submitgiveaway";
	}

	@Override
	public String mimeType() {
		return "text/plain";
	}
	
	public static String fixtime(String s){
		String[] spl = s.split(":");
		int h = Integer.parseInt(spl[0]);
		if(h > 12){
			h -= 12;
			return h + ":" + spl[1] + " PM";
		}
		return s + " AM";
	}
}
