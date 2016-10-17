package us.noop.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.waataja.fooddrop.FoodDonator;
import com.waataja.fooddrop.FoodItem;
import com.waataja.fooddrop.Giveaway;
import com.waataja.fooddrop.Giveaway.GiveawayType;

/**
 * 
 * @author Ulysses
 *
 */
public class Test {
	public static Giveaway getT(){
		//BigData d = new BigData(new File("/Users/BigBear/Ulysses2/work/testfiles"));
		//System.out.println(d.getGiveaways().size());
		FoodItem bread = new FoodItem("Bread", "some yummy bread", 12);
		FoodItem soup = new FoodItem("Soup", "delicious canned soup", 4);
		FoodItem grain = new FoodItem("Grain", "ergot-free rye", 70);
		List<FoodItem> fl = new ArrayList<FoodItem>();
		fl.add(bread);
		fl.add(soup);
		fl.add(grain);
		FoodDonator pcc = new FoodDonator("PCC", "600 N 34th St, Seattle, WA 98103", "Meet me in the back", 47.649861, -122.351056);
		GregorianCalendar start = new GregorianCalendar(2016, 10, 6);
		GregorianCalendar end = new GregorianCalendar(2016, 10, 7);
		Giveaway ng = new Giveaway(pcc, start, end, GiveawayType.PEOPLE, "availability");
		ng.setItems(fl);
		return ng;
	}
}
