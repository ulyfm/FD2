package us.noop.server;

import java.io.File;
import java.io.IOException;

import us.noop.data.BigData;
import us.noop.data.Test;
import us.noop.server.config.Config;
import us.noop.server.log.Level;
import us.noop.server.log.Logger;
import us.noop.server.pages.GiveawayListPage;
import us.noop.server.pages.StaticFilePage;

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
		s.addPage(new StaticFilePage("/index.html", new File("web/index.html"), "text/html"));
		s.addPage(new StaticFilePage("/", new File("web/index.html"), "text/html"));
		s.addPage(new StaticFilePage("/fooddrop.js", new File("web/fooddrop.js"), "text/javascript"));
		s.addPage(new StaticFilePage("/style.css", new File("web/style.css"), "text/css"));
		s.addPage(new StaticFilePage("/FoodDropLogoSmall.png", new File("web/FoodDropLogoSmall.png"), "image/png"));
		BigData b = new BigData(new File("files/"));
		b.getGiveaways().add(Test.getT());
		s.addPage(new GiveawayListPage(b));
		
		s.run();
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
