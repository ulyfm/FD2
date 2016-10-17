package us.noop.server;

import java.io.File;
import java.io.IOException;

import us.noop.server.config.Config;
import us.noop.server.log.Level;
import us.noop.server.log.Logger;

/**
 * The main class of each instance of the application.
 * The start method must be run to initialize the new instance.
 * @author ing_unfootemcnabb
 *
 */
public class Main {
	
	private Logger log;
	private Config conf;
	private Server s;
	
	/**
	 * Initializes server and all required other stuff, starts receiving connections
	 */
	public void start(){
		conf = new Config(new File("files"));
		Level l = Level.getLevelByInt(conf.getInteger("MIN_LOG_LEVEL"));
		log = new Logger(l, System.out);
		log.info("Logger initialized with minimum level " + l);
		log.info("Server starting on port " + conf.getInteger("PORT"));
		
		try {
			s = new Server(this, conf.getInteger("PORT"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t = new Thread(s);
		t.start();
	}
	
	/**
	 * Each application instance ought to have its own logger
	 * @return the logger of this instance of the application
	 */
	public Logger getLogger(){
		return log;
	}
	
	public Config getConfig(){
		return conf;
	}
	/**
	 * The response manager of the active instance
	 * @return the response manager
	 */
	public ResponseManager getResponseManager(){
		return s.getResponseManager();
	}
}
