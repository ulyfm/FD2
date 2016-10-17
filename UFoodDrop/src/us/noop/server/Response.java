package us.noop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import us.noop.server.log.*;

/**
 * A response to a client. 
 * Reads request and responds appropriately, perhaps using another class to manage things.
 * @author ing_unfootemcnabb
 *
 */
public class Response implements Runnable {
	
	private PrintWriter out;
	private BufferedReader in;
	private int id;
	
	
	/**
	 * Constructor. Initializes with input and output.
	 * @param out A writer to the client
	 * @param in The client's shit
	 */
	public Response(PrintWriter out, BufferedReader in){
		this.out = out;
		this.in = in;
		id = Start.getInstance().getResponseManager().nextId();
	}
	
	/**
	 * Starts a response with the given i/o
	 */
	@Override
	public void run() {
		try {
			String header = in.readLine();
			Start.getInstance().getLogger().info("R:" + id + " responding to: " + header);
			if(header == null) 
				Start.getInstance().getLogger().log(Level.HIGH, "Malformed header.");
			else
				out.write(Start.getInstance().getResponseManager().getResponse(header.split(" ")[1]));
			out.flush();
			in.close();
			out.close();
			Start.getInstance().getLogger().info("R:" + id + " completed.");
		} catch (IOException e) {
			Start.getInstance().getLogger().log(Level.HIGH, "Couldn't read request: \n" + e.getStackTrace());
		}
	}
}
