package us.noop.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import us.noop.fd.Start;
import us.noop.fd.data.BigData;
import us.noop.fd.data.Test;
import us.noop.fd.pages.GiveawayListPage;
import us.noop.server.log.*;
import us.noop.server.pages.StaticFilePage;

/**
 * A server that passes clients immediately on to Response Threads.
 * @author Ulysses
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
				Thread t = new Thread(new Response(client.getInetAddress().getHostAddress(), new PrintWriter(client.getOutputStream(), true), new BufferedReader(new InputStreamReader(client.getInputStream()))));
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
	
	/**
	 * Adds a page that responds to a particular URL.
	 * @param p the page
	 */
	public void addPage(Page p){
		r.addPage(p);
	}
}
