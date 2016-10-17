package us.noop.server.log;

import java.io.PrintStream;
import java.sql.Timestamp;

/**
 * Logging methods
 * @author ing_unfootemcnabb
 *
 */
public class Logger {
	
	private PrintStream out;
	private Level minLevel;
	/**
	 * A util to print stuff into a PrintStream with labels and times.
	 * @param out the printstream to print stuff to
	 */
	public Logger(Level minLevel, PrintStream out){
		this.out = out;
		this.minLevel = minLevel;
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
		if(loglevel.getLevel() >= minLevel.getLevel())
			out.println(new Timestamp(System.currentTimeMillis()) + " [" + loglevel.getTag() + "] " + text);
	}
}
