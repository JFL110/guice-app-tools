package org.jfl110.app;

import com.google.inject.ImplementedBy;

/**
 * Simple logger interface.
 * 
 * @author jim
 */
@ImplementedBy(Logger.ConsoleLogger.class)
public interface Logger {

	/**
	 * Log a message
	 */
	public void log(Object message);
	
	class ConsoleLogger implements Logger {
		@Override
		public void log(Object message) {
			System.out.println(message);
		}
	}
}