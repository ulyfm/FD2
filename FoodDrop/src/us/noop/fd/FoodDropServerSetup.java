package us.noop.fd;

import java.io.File;

import us.noop.fd.data.BigData;
import us.noop.fd.data.Test;
import us.noop.fd.pages.GiveawayListPage;
import us.noop.server.Server;
import us.noop.server.ServerSetup;
import us.noop.server.pages.StaticFilePage;

/**
 * 
 * @author Ulysses
 *
 */
public class FoodDropServerSetup implements ServerSetup {

	@Override
	public void setUpServer(Server s) {
		s.addPage(new StaticFilePage("/index.html", new File("web/index.html"), "text/html"));
		s.addPage(new StaticFilePage("/", new File("web/index.html"), "text/html"));
		s.addPage(new StaticFilePage("/fooddrop.js", new File("web/fooddrop.js"), "text/javascript"));
		s.addPage(new StaticFilePage("/style.css", new File("web/style.css"), "text/css"));
		s.addPage(new StaticFilePage("/FoodDropLogoSmall.png", new File("web/FoodDropLogoSmall.png"), "image/png"));
		BigData b = new BigData(new File("files/"));
		b.getGiveaways().add(Test.getT());
		s.addPage(new GiveawayListPage(b));
	}

}
