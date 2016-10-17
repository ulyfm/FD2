package us.noop.server;

import us.noop.server.config.Vars;

/**
 * Main class, in the future it could be necessary to run multiple instances of the server,
 * this allows that.
 * @author ing_unfootemcnabb
 *
 */
public class Start {
	
	private static Main instance;
	
	/**
	 * Main method, begins an instance of the application.
	 * @param args command-line arguments (none yet, but possible port or logging options in the future)
	 */
	public static void main(String... args){
		System.out.println("Hiking app backend version " + Vars.VERSION);
		instance = new Main();
		instance.start();
	}
	
	/**
	 * The current instance of the application, could be changed in the future to allow for multiple instances.
	 * @return the most recently created instance
	 */
	public static Main getInstance(){
		return instance;
	}
}
