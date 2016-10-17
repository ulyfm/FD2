package us.noop.server.config;

import us.noop.server.log.Level;

/**
 * <i>Should</i> only include developer-created variables such as version,
 * but for now also includes things that should be in a config file like port and logging level.
 * 
 * @author ing_unfootemcnabb
 *
 */
public class Vars {
	
	public static final int PORT = 80;
	public static final String VERSION = "0.2a";
	public static final Level MIN_LOG_LEVEL = Level.INFO;
	
}
