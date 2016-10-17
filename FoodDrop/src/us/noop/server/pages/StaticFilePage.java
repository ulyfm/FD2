package us.noop.server.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import us.noop.server.Page;
import us.noop.server.Start;
import us.noop.server.log.Level;

/**
 * A page that is just a static file
 * @author ing_unfootemcnabb
 *
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
				response = new String(data, "UTF-8");
			} catch (IOException e) {
				Start.getInstance().getLogger().log(Level.HIGH, "Failed to read file for: " + f.getName() + ": \n" + e.getStackTrace());
			}
		}
		this.address = address;
		this.mimetype = mimetype;
	}
	
	@Override
	public String getResponse(String input) {
		return response;
	}
	
	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String mimeType() {
		return mimetype;
	}
}
