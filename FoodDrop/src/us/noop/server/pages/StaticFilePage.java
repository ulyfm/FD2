package us.noop.server.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import us.noop.fd.Start;
import us.noop.server.Page;
import us.noop.server.RequestData;
import us.noop.server.ResponseManager;
import us.noop.server.log.Level;

/**
 * A page that is just a static file
 * @author Ulysses
 * 
 * TODO: Update to work with non-text files
 */
public class StaticFilePage implements Page {
	
	private String response;
	private String address;
	private String mimetype;
	
	/**
	 * Constructor. A page that responds to the selected address with a static cached file.
	 * @param address - the address to make the page available at
	 * @param f - the file to cache
	 */
	public StaticFilePage(String address, File f, String mimetype){
		if(!f.exists()){
			Start.getInstance().getLogger().log(Level.HIGH, "Failed to generate static response page for: " + f.getName());
		}else{
			try {
				FileInputStream fs = new FileInputStream(f);
				byte[] data = new byte[(int) f.length()];
				fs.read(data);
				fs.close();
				response = ResponseManager.generateHeader(200, "OK", new String(data, "UTF-8"), mimetype);
			} catch (IOException e) {
				Start.getInstance().getLogger().log(Level.HIGH, "Failed to read file for: " + f.getName() + ": \n" + e.getStackTrace());
			}
		}
		this.address = address;
		this.mimetype = mimetype;
	}
	
	/**
	 * Returns the static content.
	 * @param input literally anything
	 */
	@Override
	public String getResponse(RequestData req) {
		return response;
	}
	
	/**
	 * Returns the address argument it's supposed to respond to
	 */
	@Override
	public String getAddress() {
		return address;
	}

	/**
	 * Returns the MIME-type of the content returned in <code>getResponse()</code>
	 */
	@Override
	public String mimeType() {
		return mimetype;
	}
}
