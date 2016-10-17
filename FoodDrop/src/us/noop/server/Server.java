package us.noop.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import us.noop.server.log.*;
import us.noop.server.pages.GiveawayListPage;
import us.noop.server.pages.StaticFilePage;
import us.noop.data.BigData;
import us.noop.data.Test;

/**
 * A server that passes clients immediately on to Response Threads.
 * @author ing_unfootemcnabb
 *
 */
public class Server implements Runnable {
	
	private ServerSocket sock;
	private Main instance;
	private ResponseManager r;
	
	/**
	 * Constructor. Instantiates the server socket and response manager.
	 * @param app the main class of the application
	 * @param port the port to listen at
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public Server(Main app, int port) throws IOException, InterruptedException {
		instance = app;
		try {
			sock = new ServerSocket(port);
		} catch (IOException e) {
			instance.getLogger().log(Level.HIGH, "Failed to create port: \n" + e.getStackTrace());
			System.exit(1);
		}
		r = new ResponseManager();
		r.addPage(new StaticFilePage("/index.html", new File("web/index.html"), "text/html"));
		r.addPage(new StaticFilePage("/", new File("web/index.html"), "text/html"));
		r.addPage(new StaticFilePage("/fooddrop.js", new File("web/fooddrop.js"), "text/javascript"));
		r.addPage(new StaticFilePage("/style.css", new File("web/style.css"), "text/css"));
		r.addPage(new StaticFilePage("/FoodDropLogoSmall.png", new File("web/FoodDropLogoSmall.png"), "image/png"));
		instance.getLogger().info("Server probably started successfully.");
	}
	
	/**
	 * Accepts clients and passes them on to new threads, which are stored in the ResponseManager
	 */
	public void run() {
		while(true){
			instance.getLogger().info("Awaiting connection...");
			try {
				Socket client = sock.accept();
				Thread t = new Thread(new Response(new PrintWriter(client.getOutputStream(), true), new BufferedReader(new InputStreamReader(client.getInputStream()))));
				t.start();
				r.register(t);
			} catch (IOException e) {
				Start.getInstance().getLogger().log(Level.HIGH, "Problem creating client socket: \n" + e.getStackTrace());
			}
		}
	}
	
	/**
	 * The response manager of this server instance.
	 * @return response
	 */
	public ResponseManager getResponseManager(){
		return r;
	}
	public void addPage(Page p){
		r.addPage(p);
	}
}
