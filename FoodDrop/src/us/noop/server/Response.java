package us.noop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import us.noop.fd.Start;
import us.noop.server.log.*;

/**
 * A response to a client. 
 * Reads request and responds appropriately, perhaps using another class to manage things.
 * @author Ulysses
 *
 */
public class Response implements Runnable {
	
	private PrintWriter out;
	private Scanner in;
	private int id;
	private String ip;
	
	/**
	 * Constructor. Initializes with input and output.
	 * @param out A writer to the client
	 * @param in The client's shit
	 */
	public Response(String ip, PrintWriter out, BufferedReader in){
		this.out = out;
		this.in = new Scanner(in);
		this.in.useDelimiter("\r\n\r\n");
		this.ip = ip;
		id = Start.getInstance().getResponseManager().nextId();
	}
	
	/**
	 * Starts a response with the given i/o
	 */
	@Override
	public void run() {
		String header = in.next();
		String[] fields = header.split("\r\n");
		String body = fields[0].startsWith("GET") ? "" : in.next();
		Start.getInstance().getLogger().info("R:" + id + " responding to: " + fields[0]);
		out.write(Start.getInstance().getResponseManager().getResponse(new RequestData(ip, fields, body)));
		out.flush();
		in.close();
		out.close();
		Start.getInstance().getLogger().info("R:" + id + " completed.");
	}
}
