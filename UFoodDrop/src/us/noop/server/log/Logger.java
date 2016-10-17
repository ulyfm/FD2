package us.noop.server.log;

import java.io.PrintStream;
import java.sql.Timestamp;

import us.noop.server.config.Vars;

/**
 * Logging methods
 * @author ing_unfootemcnabb
 *
 */
public class Logger {
	
	private PrintStream out;
	
	/**
	 * A util to print stuff into a PrintStream with labels and times.
	 * @param out the printstream to print stuff to
	 */
	public Logger(PrintStream out){
		this.out = out;
	}
	
	/**
	 * Logs text with INFO level. (utility method)
	 * @param text text to print out
	 */
	public void info(String text){
		log(Level.INFO, text);
	}
	
	/**
	 * Logs text with localtime and specified level, will only print if level is higher than the configured minimum.
	 * @param loglevel warning level (INFO, LOW, MEDIUM, HIGH, CRITICAL)
	 * @param text text to log
	 */
	public void log(Level loglevel, String text){
		if(loglevel.getLevel() >= Vars.MIN_LOG_LEVEL.getLevel())
			out.println(new Timestamp(System.currentTimeMillis()) + " [" + loglevel.getTag() + "] " + text);
	}
}
