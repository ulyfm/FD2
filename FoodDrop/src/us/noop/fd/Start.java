package us.noop.fd;

import us.noop.server.Main;
import us.noop.server.config.Vars;

/**
 * 
 * @author Ulysses
 *
 */
public class Start {
	
	private static Main instance;
	
	public static void main(String... args){
		System.out.println(" -- UlyServer version " + Vars.VERSION + " --");
		instance = new Main(new FoodDropServerSetup());
		instance.start();
	}
	
	public static Main getInstance(){
		return instance;
	}
}
