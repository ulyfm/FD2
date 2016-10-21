package us.noop.fd;

import java.io.File;

import us.noop.fd.data.BigData;
import us.noop.fd.pages.GiveawayCreationPage;
import us.noop.fd.pages.GiveawayListPage;
import us.noop.server.Server;
import us.noop.server.ServerSetup;
import us.noop.server.pages.StaticFilePage;
import us.noop.server.pages.StaticImagePage;

/**
 * This sets up the FoodDrop server configuration with the (intended to be universal) server class.
 * @author Ulysses
 *
 */
public class FoodDropServerSetup implements ServerSetup {

	/**
	 * Sets up the server with the required FoodDrop pages.
	 * @param s the server object
	 */
	@Override
	public void setUpServer(Server s) {
		s.addPage(new StaticFilePage("/index.html", new File("web/index.html"), "text/html"));
		s.addPage(new StaticFilePage("/", new File("web/index.html"), "text/html"));
		s.addPage(new StaticFilePage("/fooddrop.js", new File("web/fooddrop.js"), "text/javascript"));
		s.addPage(new StaticFilePage("/style.css", new File("web/style.css"), "text/css"));
		s.addPage(new StaticImagePage("/FoodDropLogoSmall.png", new File("web/FoodDropLogoSmall.png"), "image/png"));
		BigData b = new BigData(new File("files/"));
		s.addPage(new GiveawayListPage(b));
		s.addPage(new GiveawayCreationPage(b));
	}

}
